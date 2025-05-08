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
import com.technovaperu.technovaperuwebstore.model.dto.base.CategoriaDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearCategoriaDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarCategoriaDTO;
import com.technovaperu.technovaperuwebstore.services.CategoriaService;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<CategoriaDTO> categoriaMapper = (rs, rowNum) -> CategoriaDTO.builder()
            .id(rs.getInt("id"))
            .nombre(rs.getString("nombre"))
            .descripcion(rs.getString("descripcion"))
            .build();

    @Autowired
    public CategoriaServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaDTO> obtenerTodasLasCategorias() {
        String sql = "SELECT * FROM categoria ORDER BY nombre";
        return jdbcTemplate.query(sql, categoriaMapper);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoriaDTO obtenerCategoriaPorId(int id) {
        try {
            String sql = "SELECT * FROM categoria WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, categoriaMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new RecursoNoEncontradoException("Categoría", "id", id);
        }
    }

    @Override
    @Transactional
    public CategoriaDTO crearCategoria(CrearCategoriaDTO categoria) {
        String sql = "INSERT INTO categoria (nombre, descripcion) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getDescripcion());
            return ps;
        }, keyHolder);
        
        Integer id = Objects.requireNonNull(keyHolder.getKey()).intValue();
        
        return CategoriaDTO.builder()
                .id(id)
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .build();
    }

    @Override
    @Transactional
    public CategoriaDTO actualizarCategoria(int id, ActualizarCategoriaDTO categoria) {
        if (!existeCategoriaPorId(id)) {
            throw new RecursoNoEncontradoException("Categoría", "id", id);
        }
        
        String sql = "UPDATE categoria SET nombre = ?, descripcion = ? WHERE id = ?";
        jdbcTemplate.update(sql, categoria.getNombre(), categoria.getDescripcion(), id);
        
        return CategoriaDTO.builder()
                .id(id)
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .build();
    }

    @Override
    @Transactional
    public void eliminarCategoria(int id) {
        if (!existeCategoriaPorId(id)) {
            throw new RecursoNoEncontradoException("Categoría", "id", id);
        }
        
        String sql = "DELETE FROM categoria WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    @Transactional(readOnly = true)
    public int contarCategorias() {
        String sql = "SELECT COUNT(id) FROM categoria";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existeCategoriaPorId(int id) {
        String sql = "SELECT COUNT(id) FROM categoria WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }
}