package com.technovaperu.technovaperuwebstore.implement;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.technovaperu.technovaperuwebstore.exception.RecursoNoEncontradoException;
import com.technovaperu.technovaperuwebstore.model.dto.base.LoteDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearLoteDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarLoteDTO;
import com.technovaperu.technovaperuwebstore.services.LoteService;

@Service
public class LoteServiceImpl implements LoteService {
    /**
     * Implementación de {@link LoteService} utilizando JDBC para operaciones contra
     * la base de datos.
     */
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LoteServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<LoteDTO> loteRowMapper = (rs, rowNum) -> LoteDTO.builder()
            .id(rs.getInt("id"))
            .idProducto(rs.getInt("id_producto"))
            .unidadMedida(rs.getString("unidad_medida"))
            .costo(rs.getBigDecimal("costo"))
            .precio(rs.getBigDecimal("precio"))
            .cantidad(rs.getBigDecimal("cantidad"))
            .build();

    /**
     * Obtiene una lista de todos los lotes.
     * 
     * @return lista de lotes
     */
    @Override
    public List<LoteDTO> obtenerTodosLosLotes() {
        String sql = "SELECT * FROM lote";
        return jdbcTemplate.query(sql, loteRowMapper);
    }

    /**
     * Obtiene una lista de los lotes de un producto.
     * 
     * @param idProducto ID del producto
     * @return lista de lotes
     */
    @Override
    public List<LoteDTO> obtenerLotesPorProducto(int idProducto) {
        String sql = "SELECT * FROM lote WHERE id_producto = ?";
        return jdbcTemplate.query(sql, loteRowMapper, idProducto);
    }

    /**
     * Obtiene un lote por su ID.
     * 
     * @param id ID del lote a buscar
     * @return LoteDTO con la información del lote
     * @throws RecursoNoEncontradoException si no existe un lote con ese ID
     */
    @Override
    public LoteDTO obtenerLotePorId(int id) {
        String sql = "SELECT * FROM lote WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, loteRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new RecursoNoEncontradoException("No se encontró lote con ID: " + id, sql, e);
        }
    }

    /**
     * Crea un nuevo lote.
     * 
     * @param lote datos del lote a crear
     * @return ID del lote creado
     * @throws RecursoDuplicadoException si el producto ya tiene un lote
     */
    @Override
    @Transactional
    public int crearLote(CrearLoteDTO lote) {
        String sql = "INSERT INTO lote (id_producto, unidad_medida, costo, precio, cantidad) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, lote.getIdProducto());
            ps.setString(2, lote.getUnidadMedida());
            ps.setBigDecimal(3, lote.getCosto());
            ps.setBigDecimal(4, lote.getPrecio());
            ps.setBigDecimal(5, lote.getCantidad());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null) {
            throw new RuntimeException("No se pudo obtener el ID generado para el lote");
        }
        return key.intValue();
    }

    /**
     * Actualiza un lote existente.
     * 
     * @param id ID del lote a actualizar
     * @param lote datos del lote a actualizar
     * @throws RecursoNoEncontradoException si no existe un lote con ese ID
     */
    @Override
    @Transactional
    public void actualizarLote(int id, ActualizarLoteDTO lote) {
        String sql = "UPDATE lote SET id_producto = ?, unidad_medida = ?, costo = ?, precio = ?, cantidad = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql,
                lote.getIdProducto(),
                lote.getUnidadMedida(),
                lote.getCosto(),
                lote.getPrecio(),
                lote.getCantidad(),
                id);

        if (rowsAffected == 0) {
            throw new RecursoNoEncontradoException("No se encontró lote con ID: " + id, sql, rowsAffected);
        }
    }

    /**
     * Elimina un lote existente.
     * 
     * @param id ID del lote a eliminar
     * @throws RecursoNoEncontradoException si no existe un lote con ese ID
     */
    @Override
    @Transactional
    public void eliminarLote(int id) {
        String sql = "DELETE FROM lote WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        if (rowsAffected == 0) {
            throw new RecursoNoEncontradoException("No se encontró lote con ID: " + id, sql, rowsAffected);
        }
    }

    /**
     * Cuenta el número total de lotes.
     * 
     * @return cantidad de lotes existentes
     */
    @Override
    public int contarLotes() {
        String sql = "SELECT COUNT(id) FROM lote";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }
}
