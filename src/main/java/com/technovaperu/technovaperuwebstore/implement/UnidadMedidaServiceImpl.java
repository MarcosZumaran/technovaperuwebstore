package com.technovaperu.technovaperuwebstore.implement;

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
import com.technovaperu.technovaperuwebstore.model.dto.base.UnidadMedidaDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearUnidadMedidaDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarUnidadMedidaDTO;
import com.technovaperu.technovaperuwebstore.services.UnidadMedidaService;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

/**
 * Implementación de la interfaz {@link UnidadMedidaService} que utiliza JDBC para acceder a la base de datos.
 * 
 * @author Marcos Zumaran
 */
@Service
public class UnidadMedidaServiceImpl implements UnidadMedidaService {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<UnidadMedidaDTO> unidadMedidaMapper = (rs, rowNum) -> UnidadMedidaDTO.builder()
            .id(rs.getInt("id"))
            .idProducto(rs.getInt("id_producto"))
            .unidadMedida(rs.getString("unidad_medida"))
            .precio(rs.getBigDecimal("precio"))
            .build();

    @Autowired
    public UnidadMedidaServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Obtiene todas las unidades de medida.
     * 
     * @return lista de unidades de medida
     */
    @Override
    @Transactional(readOnly = true)
    public List<UnidadMedidaDTO> obtenerTodasLasUnidadesMedida() {
        String sql = "SELECT * FROM unidad_medida ORDER BY id_producto, unidad_medida";
        return jdbcTemplate.query(sql, unidadMedidaMapper);
    }

    /**
     * Obtiene las unidades de medida por producto.
     * 
     * @param idProducto identificador del producto
     * @return lista de unidades de medida
     */
    @Override
    @Transactional(readOnly = true)
    public List<UnidadMedidaDTO> obtenerUnidadesMedidaPorProducto(int idProducto) {
        String sql = "SELECT * FROM unidad_medida WHERE id_producto = ? ORDER BY unidad_medida";
        return jdbcTemplate.query(sql, unidadMedidaMapper, idProducto);
    }

    /**
     * Obtiene una unidad de medida por su identificador.
     * 
     * @param id identificador de la unidad de medida
     * @return unidad de medida
     * @throws RecursoNoEncontradoException si no existe la unidad de medida
     */
    @Override
    @Transactional(readOnly = true)
    public UnidadMedidaDTO obtenerUnidadMedidaPorId(int id) {
        try {
            String sql = "SELECT * FROM unidad_medida WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, unidadMedidaMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new RecursoNoEncontradoException("Unidad de Medida", "id", id);
        }
    }

    /**
     * Crea una unidad de medida.
     * 
     * @param unidadMedida datos de la unidad de medida
     * @return unidad de medida creada
     */
    @Override
    @Transactional
    public UnidadMedidaDTO crearUnidadMedida(CrearUnidadMedidaDTO unidadMedida) {
        String sql = "INSERT INTO unidad_medida (id_producto, unidad_medida, precio) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, unidadMedida.getIdProducto());
            ps.setString(2, unidadMedida.getUnidadMedida());
            ps.setBigDecimal(3, unidadMedida.getPrecio());
            return ps;
        }, keyHolder);
        
        Integer id = Objects.requireNonNull(keyHolder.getKey()).intValue();
        
        return UnidadMedidaDTO.builder()
                .id(id)
                .idProducto(unidadMedida.getIdProducto())
                .unidadMedida(unidadMedida.getUnidadMedida())
                .precio(unidadMedida.getPrecio())
                .build();
    }

    /**
     * Actualiza una unidad de medida.
     * 
     * @param id identificador de la unidad de medida
     * @param unidadMedida datos de la unidad de medida
     * @throws RecursoNoEncontradoException si no existe la unidad de medida
     */
    @Override
    @Transactional
    public void actualizarUnidadMedida(int id, ActualizarUnidadMedidaDTO unidadMedida) {
        if (!existeUnidadMedidaPorId(id)) {
            throw new RecursoNoEncontradoException("Unidad de Medida", "id", id);
        }
        
        String sql = "UPDATE unidad_medida SET id_producto = ?, unidad_medida = ?, precio = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, unidadMedida.getIdProducto(), unidadMedida.getUnidadMedida(), 
                unidadMedida.getPrecio(), id);
                
        if (rowsAffected == 0) {
            throw new RecursoNoEncontradoException("Unidad de Medida", "id", id);
        }
    }

    /**
     * Elimina una unidad de medida.
     * 
     * @param id identificador de la unidad de medida
     * @throws RecursoNoEncontradoException si no existe la unidad de medida
     */
    @Override
    @Transactional
    public void eliminarUnidadMedida(int id) {
        if (!existeUnidadMedidaPorId(id)) {
            throw new RecursoNoEncontradoException("Unidad de Medida", "id", id);
        }
        
        String sql = "DELETE FROM unidad_medida WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    /**
     * Cuenta el número de unidades de medida.
     * 
     * @return número de unidades de medida
     */
    @Override
    @Transactional(readOnly = true)
    public int contarUnidadesMedida() {
        String sql = "SELECT COUNT(id) FROM unidad_medida";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }
    
    /**
     * Cuenta el número de unidades de medida por producto.
     * 
     * @param idProducto identificador del producto
     * @return número de unidades de medida
     */
    @Override
    @Transactional(readOnly = true)
    public int contarUnidadesMedidaPorProducto(int idProducto) {
        String sql = "SELECT COUNT(id) FROM unidad_medida WHERE id_producto = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, idProducto);
        return (count != null) ? count : 0;
    }
    
    /**
     * Verifica si existe una unidad de medida por su identificador.
     * 
     * @param id identificador de la unidad de medida
     * @return true si existe, false en caso contrario
     */
    @Override
    @Transactional(readOnly = true)
    public boolean existeUnidadMedidaPorId(int id) {
        String sql = "SELECT COUNT(id) FROM unidad_medida WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }
}
