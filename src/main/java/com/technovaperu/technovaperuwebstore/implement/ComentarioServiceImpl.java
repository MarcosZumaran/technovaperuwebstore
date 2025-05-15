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

import com.technovaperu.technovaperuwebstore.services.ComentarioService;


import com.technovaperu.technovaperuwebstore.exception.RecursoNoEncontradoException;
import com.technovaperu.technovaperuwebstore.model.ComentarioModel.EstadoComentario;
import com.technovaperu.technovaperuwebstore.model.dto.base.ComentarioDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearComentarioDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarComentarioDTO;

@Service
public class ComentarioServiceImpl implements ComentarioService{
    
    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<ComentarioDTO> comentarioMapper = (rs, rowNum) -> ComentarioDTO.builder()
            .id(rs.getInt("id"))
            .idProducto(rs.getInt("id_producto"))
            .idUsuario(rs.getInt("id_usuario"))
            .texto(rs.getString("texto"))
            .calificacion(rs.getInt("calificacion"))
            .estado(EstadoComentario.valueOf(rs.getString("estado")))
            .fechaCreacion(rs.getTimestamp("fecha_creacion").toLocalDateTime())
            .fechaModificacion(rs.getTimestamp("fecha_modificacion").toLocalDateTime())
            .build();

    /**
     * Constructor que recibe el JdbcTemplate para poder realizar operaciones con la base de datos
     * @param jdbcTemplate JdbcTemplate para realizar operaciones con la base de datos
     */
    @Autowired
    public ComentarioServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Obtiene los comentarios de un producto por pagina
     * @param idProducto id del producto que se va a obtener los comentarios
     * @param pagina pagina que se va a obtener
     * @return lista de comentarios de un producto por pagina
     */
    @Override 
    @Transactional(readOnly = true)
    public List<ComentarioDTO> obtenerComentariosPorProducto(int idProducto, int pagina){
        if (pagina <= 0) pagina = 1;
        int limit = 10;
        int offset = (pagina - 1) * limit;
        String sql = "SELECT * FROM comentario WHERE id_producto = ? ORDER BY fecha_comentario DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, comentarioMapper, idProducto, limit, offset);
    }

    /**
     * Obtiene un comentario por su id
     * @param id id del comentario que se va a obtener
     * @return objeto ComentarioDTO con los datos del comentario
     * @throws RecursoNoEncontradoException si el comentario no existe
     */
    @Override
    @Transactional(readOnly = true)
    public ComentarioDTO obtenerComentarioPorId(int id){
        try {
            String sql = "SELECT * FROM comentario WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, comentarioMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new RecursoNoEncontradoException("Comentario", "id", id);
        }
    }

    /**
     * Crea un nuevo comentario
     * @param comentario objeto CrearComentarioDTO con los datos del comentario
     * @return objeto ComentarioDTO con los datos del comentario
     */
    @Override 
    @Transactional
    public ComentarioDTO crearComentario(CrearComentarioDTO comentario){
        String sql = "INSERT INTO comentario (id_producto, id_usuario, contenido) VALUES(?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, comentario.getIdProducto());
            ps.setInt(2, comentario.getIdUsuario());
            ps.setString(3, comentario.getTexto());
            return ps;
        }, keyHolder);

        Integer id = Objects.requireNonNull(keyHolder.getKey()).intValue();

        return ComentarioDTO.builder()
                .id(id)
                .idProducto(comentario.getIdProducto())
                .idUsuario(comentario.getIdUsuario())
                .texto(comentario.getTexto())
                .calificacion(comentario.getCalificacion())
                .estado(EstadoComentario.VISIBLE)
                .fechaCreacion(LocalDateTime.now())
                .fechaModificacion(LocalDateTime.now())
                .build();
    }
    
    /**
     * Actualiza un comentario
     * @param id id del comentario que se va a actualizar
     * @param comentario objeto ActualizarComentarioDTO con los datos del comentario
     * @throws RecursoNoEncontradoException si el comentario no existe
     */
    @Override
    @Transactional
    public void actualizarComentario(int id, ActualizarComentarioDTO comentario){
        if (!existeComentarioPorId(id)) {
            throw new RecursoNoEncontradoException("Comentario", "id", id);
        }
        String sql = "UPDATE comentario SET contenido = ? WHERE id = ?";
        jdbcTemplate.update(sql, comentario.getTexto(), id);
    }
    
    /**
     * Elimina un comentario
     * @param id id del comentario que se va a eliminar
     * @throws RecursoNoEncontradoException si el comentario no existe
     */
    @Override
    @Transactional
    public void eliminarComentario(int id){
        if (!existeComentarioPorId(id)) {
            throw new RecursoNoEncontradoException("Comentario", "id", id);
        }
        String sql = "DELETE FROM comentario WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
    
    /**
     * Obtiene el numero de comentarios de un producto
     * @param idProducto id del producto que se va a obtener los comentarios
     * @return numero de comentarios de un producto
     */
    @Override 
    @Transactional(readOnly = true)
    public int contarComentariosDeProducto(int idProducto){
        String sql = "SELECT COUNT(id) FROM comentario WHERE id_producto = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }

    /**
     * Obtiene el numero total de comentarios
     * @return numero total de comentarios
     */
    @Override 
    @Transactional(readOnly = true)
    public int contarComentarios(){
        String sql = "SELECT COUNT(id) FROM comentario";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }

    /**
     * Verifica si existe un comentario por su id
     * @param id id del comentario que se va a verificar
     * @return true si existe, false en caso contrario
     */
    @Override
    @Transactional(readOnly = true)
    public boolean existeComentarioPorId(int id) {
        String sql = "SELECT COUNT(id) FROM comentario WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }
    
}
