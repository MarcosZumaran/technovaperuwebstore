
package com.technovaperu.technovaperuwebstore.implement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.technovaperu.technovaperuwebstore.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override 
    public List<Map<String, Object>> obtenerTodosLosUsuarios(int pagina){
        if (pagina <= 0) pagina = 1;
        int offset = (pagina - 1) * 10;
        int limit = 10;
        String sql = "SELECT * FROM usuario ORDER BY id DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.queryForList(sql, limit, offset);
    }

    @Override 
    public Map<String, Object> obtenerUsuarioPorId(int id){
        String sql = "SELECT * FROM usuario WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @SuppressWarnings("null")
    @Override 
    public int obtenerCarritoDeUsuario(int idUsuario){
        String sql = "SELECT id_carrito FROM usuario WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override 
    public String crearUsuario(Map<String, Object> usuario){
        String sql = "INSERT INTO usuario (nombre, apellido, email, direccion, telefono, contrasena, rol) VALUES(?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, usuario.get("nombre"), usuario.get("apellido"), usuario.get("email"), usuario.get("direccion"), usuario.get("telefono"), usuario.get("contrasena"), usuario.get("rol"));
        return "Usuario creado con éxito.";
    }
    
    @Override 
    public String actualizarUsuario(int id, Map<String, Object> usuario){
        String sql = "UPDATE usuario SET nombre = ?, apellido = ?, email = ?, direccion = ?, telefono = ?, contrasena = ?, rol = ? WHERE id = ?";
        jdbcTemplate.update(sql, usuario.get("nombre"), usuario.get("apellido"), usuario.get("email"), usuario.get("direccion"), usuario.get("telefono"), usuario.get("contrasena"), usuario.get("rol"), id);
        return "Usuario actualizado con éxito.";
    }
    
    @Override 
    public String eliminarUsuario(int id){
        String sql = "DELETE FROM usuario WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return "Usuario eliminado con éxito.";
    }

    @Override 
    public int contarUsuarios(){
        String sql = "SELECT COUNT(id) FROM usuario";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }
}
