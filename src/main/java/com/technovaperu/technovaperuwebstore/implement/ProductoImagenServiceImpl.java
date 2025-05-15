package com.technovaperu.technovaperuwebstore.implement;

import java.sql.PreparedStatement;
import java.sql.Statement;
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
import com.technovaperu.technovaperuwebstore.model.ProductolImagenModel.Tipo;
import com.technovaperu.technovaperuwebstore.model.dto.base.ProductoImagenDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearProductoImagenDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarProductoImagenDTO;
import com.technovaperu.technovaperuwebstore.services.ProductoImagenService;

/**
 * Implementación de la interfaz {@link ProductoImagenService} que utiliza JDBC para acceder a la base de datos.
 * 
 * @author Carlos Montell
 */
@Service
public class ProductoImagenServiceImpl implements ProductoImagenService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductoImagenServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * RowMapper para convertir un registro de la base de datos en un objeto {@link ProductoImagenDTO}.
     */
    private final RowMapper<ProductoImagenDTO> productoImagenRowMapper = (rs, rowNum) -> ProductoImagenDTO.builder()
            .id(rs.getInt("id"))
            .idProducto(rs.getInt("id_producto"))
            .url(rs.getString("url"))
            .tipo(Tipo.valueOf(rs.getString("tipo")))
            .build();

    @Override
    @Transactional(readOnly = true)
    public List<ProductoImagenDTO> obtenerImagenesPorProducto(int id) {
        // Obtiene todas las imágenes asociadas a un producto
        String sql = "SELECT * FROM producto_imagen WHERE id_producto = ?";
        return jdbcTemplate.query(sql, productoImagenRowMapper, id);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoImagenDTO obtenerImagenPorId(int id) {
        // Obtiene una imagen por su ID
        try {
            String sql = "SELECT * FROM producto_imagen WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, productoImagenRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new RecursoNoEncontradoException("Imagen", "id", id);
        }
    }

    @Override
    @Transactional
    public ProductoImagenDTO crearImagen(CrearProductoImagenDTO imagen) {
        // Crea una nueva imagen para un producto
        String sql = "INSERT INTO producto_imagen (id_producto, url, tipo) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, imagen.getIdProducto());
            ps.setString(2, imagen.getUrl());
            ps.setString(3, imagen.getTipo().name());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null) {
            throw new RuntimeException("No se pudo obtener el ID generado para la imagen");
        }

        Integer id = Objects.requireNonNull(keyHolder.getKey()).intValue();

        return ProductoImagenDTO.builder()
                .id(id)
                .idProducto(imagen.getIdProducto())
                .url(imagen.getUrl())
                .tipo(imagen.getTipo())
                .build();
    }

    @Override
    @Transactional
    public void actualizarImagen(int id, ActualizarProductoImagenDTO imagen) {
        // Actualiza una imagen existente
        if (!existeImagenPorId(id)) {
            throw new RecursoNoEncontradoException("Imagen", "id", id);
        }
        String sql = "UPDATE producto_imagen SET url = ?, tipo = ? WHERE id = ?";
        jdbcTemplate.update(sql, imagen.getUrl(), imagen.getTipo().name(), id);
    }

    @Override
    @Transactional
    public void eliminarImagen(int id) {
        // Elimina una imagen por su ID
        if (!existeImagenPorId(id)) {
            throw new RecursoNoEncontradoException("Imagen", "id", id);
        }
        String sql = "DELETE FROM producto_imagen WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    @Transactional(readOnly = true)
    public int contarImagenesPorProducto(int id) {
        // Cuenta el número de imágenes asociadas a un producto
        String sql = "SELECT COUNT(id) FROM producto_imagen WHERE id_producto = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return (count != null) ? count : 0;
    }

    @Override
    @Transactional(readOnly = true)
    public int contarImagenes() {
        // Cuenta el número total de imágenes
        String sql = "SELECT COUNT(id) FROM producto_imagen";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeImagenPorId(int id) {
        // Verifica si existe una imagen con el ID especificado
        String sql = "SELECT COUNT(id) FROM producto_imagen WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }
}

