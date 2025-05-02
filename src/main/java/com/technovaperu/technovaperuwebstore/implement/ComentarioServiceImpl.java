package com.technovaperu.technovaperuwebstore.implement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.technovaperu.technovaperuwebstore.services.ComentarioService;

@Service
public class ComentarioServiceImpl implements ComentarioService{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override 
    public List<Map<String, Object>> obtenerComentariosPorProducto(int idProducto, int pagina){
        if (pagina <= 0) pagina = 1;
        int limit = 10;
        int offset = (pagina - 1) * limit;
        String sql = "SELECT * FROM comentarios WHERE id_producto = ? ORDER BY fecha_comentario DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.queryForList(sql, idProducto, limit, offset);
    }

    @Override 
    public Map<String, Object> obtenerComentarioPorId(int id){
        String sql = "SELECT * FROM comentarios WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @Override 
    public String crearComentario(Map<String, Object> comentario){
        String sql = "INSERT INTO comentarios (id_producto, id_usuario, contenido) VALUES(?, ?, ?)";
        jdbcTemplate.update(sql, comentario.get("id_producto"), comentario.get("id_usuario"), comentario.get("contenido"));
        return "Comentario creado con éxito.";
    }
    
    @Override 
    public String actualizarComentario(int id, Map<String, Object> comentario){
        String sql = "UPDATE comentarios SET contenido = ? WHERE id = ?";
        jdbcTemplate.update(sql, comentario.get("contenido"), id);
        return "Comentario actualizado con éxito.";
    }
    
    @Override 
    public String eliminarComentario(int id){
        String sql = "DELETE FROM comentarios WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return "Comentario eliminado con éxito.";
    }
    
    @Override 
    public int contarComentariosDeProducto(int idProducto){
        String sql = "SELECT COUNT(id) FROM comentarios WHERE id_producto = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }

    @Override 
    public int contarComentarios(){
        String sql = "SELECT COUNT(id) FROM comentarios";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }
    
}
