
package com.technovaperu.technovaperuwebstore.implement;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.technovaperu.technovaperuwebstore.exception.RecursoNoEncontradoException;
import com.technovaperu.technovaperuwebstore.model.dto.base.HistorialPedidosDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearHistorialPedidosDTO;
import com.technovaperu.technovaperuwebstore.services.HistorialPedidosService;

@Service
public class HistorialPedidosServiceImpl implements HistorialPedidosService{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HistorialPedidosServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper para mapear resultados de la base de datos a objetos HistorialPedidosDTO
    private final RowMapper<HistorialPedidosDTO> historialPedidosRowMapper = (rs, rowNum) -> HistorialPedidosDTO.builder()
            .id(rs.getInt("id"))
            .idPedido(rs.getInt("id_pedido"))
            .estado(rs.getString("estado"))
            .fechaEstado(rs.getTimestamp("fecha_estado").toLocalDateTime())
            .build();

    /**
     * Obtiene una lista de historial de pedidos por ID de pedido.
     * 
     * @param idPedido ID del pedido al que pertenece el historial.
     * @param pagina Número de página para la paginación.
     * @return Lista de objetos HistorialPedidosDTO.
     */
    @Override
    @Transactional(readOnly = true)
    public List<HistorialPedidosDTO> obtenerHistorialPorPedido(int idPedido, int pagina){
        if (pagina <= 0) pagina = 1; // Ajusta la página si es menor o igual a 0
        int offset = (pagina - 1) * 10;
        int limit = 10;
        String sql = "SELECT * FROM historial_pedido WHERE id_pedido = ? ORDER BY fecha_estado DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, historialPedidosRowMapper, idPedido, limit, offset);
    }

    /**
     * Obtiene un historial de pedido por su ID.
     * 
     * @param id ID del historial de pedido.
     * @return Objeto HistorialPedidosDTO.
     * @throws RecursoNoEncontradoException si no se encuentra el historial.
     */
    @Override
    @Transactional(readOnly = true)
    public HistorialPedidosDTO obtenerHistorialPorId(int id){
        try {
            String sql = "SELECT * FROM historial_pedido WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, historialPedidosRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new RecursoNoEncontradoException("Historial de Pedido", "id", id);
        }
    }

    /**
     * Crea un nuevo historial de pedido.
     * 
     * @param historial Datos para crear el historial de pedido.
     * @return Objeto HistorialPedidosDTO creado.
     */
    @Override
    @Transactional
    public HistorialPedidosDTO crearHistorialPedido(CrearHistorialPedidosDTO historial){
        String sql = "INSERT INTO historial_pedido (id_pedido, estado, fecha_estado) VALUES(?, ?, NOW())";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, historial.getIdPedido());
            ps.setString(2, historial.getEstado());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null) {
            throw new RuntimeException("No se pudo obtener el ID generado para el historial");
        }

        Integer id = Objects.requireNonNull(keyHolder.getKey()).intValue();

        return HistorialPedidosDTO.builder()
                .id(id)
                .idPedido(historial.getIdPedido())
                .estado(historial.getEstado())
                .fechaEstado(LocalDateTime.now())
                .build();
    }

    /**
     * Elimina un historial de pedido por su ID.
     * 
     * @param id ID del historial de pedido a eliminar.
     * @throws RecursoNoEncontradoException si no se encuentra el historial.
     */
    @Override
    @Transactional
    public void eliminarHistorialPedido(int id){
        if (!existeHistorialPedidoPorId(id)) {
            throw new RecursoNoEncontradoException("Historial de Pedido", "id", id);
        }
        String sql = "DELETE FROM historial_pedido WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    /**
     * Cuenta el número total de historiales de pedidos.
     * 
     * @return Número total de historiales.
     */
    @Override
    @Transactional(readOnly = true)
    public int contarHistorialPedidos(){
        String sql = "SELECT COUNT(id) FROM historial_pedido";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }

    /**
     * Verifica si existe un historial de pedido por su ID.
     * 
     * @param id ID del historial de pedido.
     * @return true si existe, false en caso contrario.
     */
    @Override
    @Transactional(readOnly = true)
    public boolean existeHistorialPedidoPorId(int id) {
        String sql = "SELECT COUNT(id) FROM historial_pedido WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

}