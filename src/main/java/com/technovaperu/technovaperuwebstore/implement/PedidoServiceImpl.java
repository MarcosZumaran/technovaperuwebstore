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
import com.technovaperu.technovaperuwebstore.model.PedidoModel.EstadoPedido;
import com.technovaperu.technovaperuwebstore.model.dto.base.PedidoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearPedidoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarPedidoDTO;
import com.technovaperu.technovaperuwebstore.services.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService{
    
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PedidoServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper for mapping ResultSet rows to PedidoDTO objects
    private final RowMapper<PedidoDTO> pedidoRowMapper = (rs, rowNum) -> PedidoDTO.builder()
            .id(rs.getInt("id"))
            .idUsuario(rs.getInt("id_usuario"))
            .estado(EstadoPedido.valueOf(rs.getString("estado")))
            .total(rs.getBigDecimal("total"))
            .direccionEnvio(rs.getString("direccion_envio"))
            .build();
    
    /**
     * Obtiene una lista de pedidos por ID de usuario.
     * 
     * @param idUsuario ID del usuario.
     * @param pagina Número de página para la paginación.
     * @return Lista de objetos PedidoDTO.
     */
    @Override 
    @Transactional(readOnly = true)
    public List<PedidoDTO> obtenerPedidosPorUsuario(int idUsuario, int pagina){
        if (pagina <= 0) pagina = 1;
        int offset = (pagina - 1) * 10;
        int limit = 10;
        String sql = "SELECT * FROM pedido WHERE id_usuario = ? ORDER BY fecha_pedido DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, pedidoRowMapper, idUsuario, limit, offset);
    }

    /**
     * Obtiene un pedido por su ID.
     * 
     * @param id ID del pedido.
     * @return Objeto PedidoDTO.
     * @throws RecursoNoEncontradoException si no se encuentra el pedido.
     */
    @Override 
    @Transactional(readOnly = true)
    public PedidoDTO obtenerPedidoPorId(int id){
        try {
            String sql = "SELECT * FROM pedido WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, pedidoRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new RecursoNoEncontradoException("Pedido", "id", id);
        }
    }

    /**
     * Crea un nuevo pedido.
     * 
     * @param pedido Datos para crear el pedido.
     * @return Objeto PedidoDTO creado.
     */
    @Override 
    @Transactional
    public PedidoDTO crearPedido(CrearPedidoDTO pedido){
        String sql = "INSERT INTO pedido (id_usuario, direccion_envio, total, estado) VALUES(?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, pedido.getIdUsuario());
            ps.setString(2, pedido.getDireccionEnvio());
            ps.setBigDecimal(3, pedido.getTotal());
            ps.setString(4, pedido.getEstado().name());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null) {
            throw new RuntimeException("No se pudo obtener el ID generado para el pedido");
        }

        Integer id = Objects.requireNonNull(keyHolder.getKey()).intValue();

        return PedidoDTO.builder()
                .id(id)
                .idUsuario(pedido.getIdUsuario())
                .estado(pedido.getEstado())
                .total(pedido.getTotal())
                .direccionEnvio(pedido.getDireccionEnvio())
                .fechaPedido(LocalDateTime.now())
                .build();
    }
    
    /**
     * Actualiza un pedido existente.
     * 
     * @param id ID del pedido.
     * @param pedido Datos para actualizar el pedido.
     * @throws RecursoNoEncontradoException si no se encuentra el pedido.
     */
    @Override 
    @Transactional
    public void actualizarPedido(int id, ActualizarPedidoDTO pedido){
        if (!existePedidoPorId(id)) {
            throw new RecursoNoEncontradoException("Pedido", "id", id);
        }
        String sql = "UPDATE pedido SET direccion_envio = ? WHERE id = ?";
        jdbcTemplate.update(sql, pedido.getDireccionEnvio(), id);
    }
    
    /**
     * Elimina un pedido por su ID.
     * 
     * @param id ID del pedido.
     * @throws RecursoNoEncontradoException si no se encuentra el pedido.
     */
    @Override 
    @Transactional
    public void eliminarPedido(int id){
        if (!existePedidoPorId(id)) {
            throw new RecursoNoEncontradoException("Pedido", "id", id);
        }
        String sql = "DELETE FROM pedido WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    /**
     * Cuenta el número total de pedidos por usuario.
     * 
     * @param idUsuario ID del usuario.
     * @return Número de pedidos.
     */
    @Override 
    @Transactional(readOnly = true)
    public int contarPedidosPorUsuario(int idUsuario){
        String sql = "SELECT COUNT(id) FROM pedido WHERE id_usuario = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, idUsuario);
        return (count != null) ? count : 0;
    }
    
    /**
     * Cuenta el número total de pedidos.
     * 
     * @return Número total de pedidos.
     */
    @Override 
    @Transactional(readOnly = true)
    public int contarPedidos(){
        String sql = "SELECT COUNT(id) FROM pedido";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }

    /**
     * Verifica si existe un pedido por su ID.
     * 
     * @param id ID del pedido.
     * @return true si existe, false en caso contrario.
     */
    @Override
    @Transactional(readOnly = true)
    public boolean existePedidoPorId(int id) {
        String sql = "SELECT COUNT(id) FROM pedido WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

}
