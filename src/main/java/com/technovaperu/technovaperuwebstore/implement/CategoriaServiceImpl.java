package com.technovaperu.technovaperuwebstore.implement;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;


import com.technovaperu.technovaperuwebstore.exception.RecursoNoEncontradoException;
import com.technovaperu.technovaperuwebstore.model.dto.base.CategoriaDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearCategoriaDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarCategoriaDTO;
import com.technovaperu.technovaperuwebstore.services.CategoriaService;
import com.technovaperu.technovaperuwebstore.util.DynamicSqlBuilder;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);

    private final RowMapper<CategoriaDTO> categoriaRowMapper = (rs, rowNum) -> CategoriaDTO.builder()
            .id(rs.getLong("id"))
            .nombre(rs.getString("nombre"))
            .descripcion(rs.getString("descripcion"))
            .fechaRegistro(rs.getTimestamp("fecha_registro").toLocalDateTime())
            .fechaActualizacion(rs.getTimestamp("fecha_actualizacion").toLocalDateTime())
            .activa(rs.getBoolean("activa"))
            .build();

    private List<CategoriaDTO> ejecutarConsultaListaCategorias(String sql, Object... parametros) {
        try {
            log.info("Ejecutando consulta: {}", sql);
            return jdbcTemplate.query(sql, parametros, categoriaRowMapper);
        } catch (EmptyResultDataAccessException e) {
            log.error("No se encontraron categorias");
            throw new RecursoNoEncontradoException("Categorias", "filtro aplicado", null);
        }
    }

    @Override
    public List<CategoriaDTO> obtenerTodasLasCategorias() {
        return ejecutarConsultaListaCategorias("SELECT * FROM categoria");
    }

    @Override
    public List<CategoriaDTO> obtenerCategorias(int pagina) {
        return ejecutarConsultaListaCategorias("SELECT * FROM categoria LIMIT 10 OFFSET ?", pagina);
    }

    @Override
    public CategoriaDTO obtenerCategoriaPorId(long id) {
        try {
            log.info("Ejecutando consulta: SELECT * FROM categoria WHERE id = {}", id);
            return jdbcTemplate.queryForObject("SELECT * FROM categoria WHERE id = ?", categoriaRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            log.error("No se encontraron categorias");
            throw new RecursoNoEncontradoException("Categorias", "filtro aplicado", null);
        }
    }

    @Override
    public CategoriaDTO obtenerCategoriaPorProducto(long idProducto) {
        try {
            log.info("Obteniendo la categoría del producto con id {}", idProducto);
            String sql = "SELECT id_categoria FROM producto WHERE id_producto = ?";
            Long idCategoria = jdbcTemplate.queryForObject(sql, Long.class, idProducto);

            String sqlCategoria = "SELECT * FROM categoria WHERE id = ?";
            log.info("Obteniendo la categoria con id {}", idCategoria);
            return jdbcTemplate.queryForObject(sqlCategoria, categoriaRowMapper, idCategoria);
        } catch (EmptyResultDataAccessException e) {
            log.error("No se ha encontrado la categoría del producto con id {}", idProducto);
            throw new RecursoNoEncontradoException("Producto", "id", idProducto);
        }
    }

    @Override
    public CategoriaDTO crearCategoria(CrearCategoriaDTO categoriaDTO) {

        Map<String, Object> fields = new LinkedHashMap<>();

        fields.put("nombre", categoriaDTO.getNombre());
        fields.put("descripcion", categoriaDTO.getDescripcion());
        fields.put("fecha_registro", Timestamp.valueOf(LocalDateTime.now()));
        fields.put("fecha_actualizacion", Timestamp.valueOf(LocalDateTime.now()));
        fields.put("activa", true);

        String sql = DynamicSqlBuilder.buildInsertSql("categoria", fields);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        log.info("Creando categoria con datos: {}", fields);

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            for (Object value : fields.values()) {
                ps.setObject(i++, value);
            }
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null) {
            log.error("Error al crear la categoria, clave generada es nula");
            throw new RecursoNoEncontradoException("Categorias", "filtro aplicado", null);
        }

        return obtenerCategoriaPorId(key.longValue());

    }

    @Override
    public void actualizarCategoria(long id, ActualizarCategoriaDTO categoriaDTO) {

        Map<String, Object> fields = new LinkedHashMap<>();

        if (categoriaDTO.getNombre() != null) {
            fields.put("nombre", categoriaDTO.getNombre());
        }
        if (categoriaDTO.getDescripcion() != null) {
            fields.put("descripcion", categoriaDTO.getDescripcion());
        }

        if (fields.isEmpty()) {
            log.error("No se proporcionaron campos para actualizar la categoria");
            return;
        }

        fields.put("fecha_actualizacion", Timestamp.valueOf(LocalDateTime.now()));

        String sql = DynamicSqlBuilder.buildUpdateSql("categoria", fields, "id = ?");

        Object[] params = Stream.concat(fields.values().stream(), Stream.of(id)).toArray();

        jdbcTemplate.update(sql, params);

    }

    @Override
    public void borrarCategoria(long id) {
        if (!existeCategoria(id)) {
            log.warn("No se encontró la categoria con ID: {}", id);
            throw new RecursoNoEncontradoException("Categorias", "ID", id);
        }
        log.info("Borrando categoria con ID: {}", id);
        String sql = DynamicSqlBuilder.buildDeleteSql("categoria", "id = ?");
        jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existeCategoria(long id) {
        String sql = DynamicSqlBuilder.buildCountSql("categoria", "id = ?");
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

}
