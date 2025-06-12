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

import com.technovaperu.technovaperuwebstore.services.ComentarioService;
import com.technovaperu.technovaperuwebstore.util.DynamicSqlBuilder;
import com.technovaperu.technovaperuwebstore.exception.RecursoNoEncontradoException;
import com.technovaperu.technovaperuwebstore.model.dto.base.ComentarioDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearComentarioDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarComentarioDTO;

@Service
public class ComentarioServiceImpl implements ComentarioService{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger log = LoggerFactory.getLogger(ComentarioServiceImpl.class);

    private final RowMapper<ComentarioDTO> comentarioMapper = (rs, rowNum) -> ComentarioDTO.builder()
            .id(rs.getInt("id"))
            .idProducto(rs.getInt("id_producto"))
            .idUsuario(rs.getInt("id_usuario"))
            .texto(rs.getString("texto"))
            .calificacion(rs.getInt("calificacion"))
            .fechaCreacion(rs.getTimestamp("fecha_creacion").toLocalDateTime())
            .fechaModificacion(rs.getTimestamp("fecha_modificacion").toLocalDateTime())
            .build();
    
    private List<ComentarioDTO> ejecutarConsultaListaComentarios(String sql, Object... parametros) {
        try{
            log.info("Ejecutando consulta: {}", sql);
            return jdbcTemplate.query(sql, comentarioMapper, parametros);
        } catch (EmptyResultDataAccessException e) {
            log.error("Error al ejecutar la consulta: {}", e.getMessage());
            throw new RecursoNoEncontradoException("Presentaciones de Productos", "filtro aplicado", null);
        }
    }

    @Override
    public List<ComentarioDTO> obtenerComentariosPorProducto(long idProducto, int pagina){
        String sql = "SELECT * FROM comentario WHERE id_producto = ?";
        return ejecutarConsultaListaComentarios(sql, idProducto);
    }

    @Override
    public ComentarioDTO obtenerComentarioPorId(long id){
        try{
            log.info("Obteniendo comentario por ID: {}", id);
            return jdbcTemplate.queryForObject("SELECT * FROM comentario WHERE id = ?", comentarioMapper, id);
        } catch (EmptyResultDataAccessException e) {
            log.error("Error al ejecutar la consulta: {}", e.getMessage());
            throw new RecursoNoEncontradoException("Comentarios", "filtro aplicado", String.valueOf(id));
        }
    }

    @Override
    public ComentarioDTO crearComentario(CrearComentarioDTO comentario){

        Map<String, Object> fields = new LinkedHashMap<>();

        fields.put("id_producto", comentario.getIdProducto());
        fields.put("id_usuario", comentario.getIdUsuario());
        fields.put("texto", comentario.getTexto());
        fields.put("calificacion", comentario.getCalificacion());
        fields.put("fecha_creacion", Timestamp.valueOf(LocalDateTime.now()));
        fields.put("fecha_modificacion", Timestamp.valueOf(LocalDateTime.now()));

        String sql = DynamicSqlBuilder.buildInsertSql("comentario", fields);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        log.info("Creando comentario con datos: {}", fields);

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
            log.error("Error al obtener el ID del comentario creado");
            throw new RecursoNoEncontradoException("Comentarios", "filtro aplicado", null);
        }

        return obtenerComentarioPorId(key.longValue());

    }

    @Override
    public void actualizarComentario(long id, ActualizarComentarioDTO comentario){
        Map<String, Object> fields = new LinkedHashMap<>();

        if(comentario.getTexto() != null){
            fields.put("texto", comentario.getTexto());
        }
        if(comentario.getCalificacion() == null){
            fields.put("calificacion", comentario.getCalificacion());
        }

        if (fields.isEmpty()) {
            log.error("No se proporcionaron campos para actualizar el comentario");
            return;
        }

        fields.put("fecha_modificacion", Timestamp.valueOf(LocalDateTime.now()));

        String sql = DynamicSqlBuilder.buildUpdateSql("comentario", fields, "id");

        Object[] params = Stream.concat(fields.values().stream(), Stream.of(id)).toArray();

        log.info("Actualizando comentario con datos: {}", fields);

        jdbcTemplate.update(sql, params);

    }

    @Override
    public void eliminarComentario(long id){
        if(!existeComentarioPorId(id)){
            throw new RecursoNoEncontradoException("Comentarios", "filtro aplicado", String.valueOf(id));
        }
        log.info("Eliminando comentario con ID: {}", id);
        String sql = DynamicSqlBuilder.buildDeleteSql("comentario", "id = ?");
        jdbcTemplate.update(sql, id);
    }

    @Override
    public int contarComentariosDeProducto(long idProducto){
        String sql = DynamicSqlBuilder.buildCountSql("comentario", "id_producto");
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, idProducto);
        return count != null && count > 0 ? count : 0;
    }

    @Override
    public int contarComentarios(){
        String sql = DynamicSqlBuilder.buildCountSql("comentario", "id");
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count != null && count > 0 ? count : 0;
    }

    @Override
    public boolean existeComentarioPorId(long id){
        String sql = DynamicSqlBuilder.buildCountSql("comentario", "id = ?");
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }



}
