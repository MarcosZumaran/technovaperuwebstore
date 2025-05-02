package com.technovaperu.technovaperuwebstore.implement;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.technovaperu.technovaperuwebstore.services.CarritoService;

@Service
public class CarritoServiceImpl implements CarritoService{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Map<String, Object> obtenerCarritoPorId(int id) {
        String sql = "SELECT * FROM carrito WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @Override
    public String crearCarrito(Map<String, Object> carrito) {
        String sql = "INSERT INTO carrito (fecha_creacion) VALUES (?)";
        jdbcTemplate.update(sql, carrito.get("fecha_creacion"));
        return "Carrito creado con éxito.";
    }

    @Override 
    public String actualizarCarrito(int id, Map<String, Object> carrito) {
        String sql = "UPDATE carrito SET fecha_creacion = ? WHERE id = ?";
        jdbcTemplate.update(sql, carrito.get("fecha_creacion"), id);
        return "Carrito actualizado con éxito.";
    }

    @Override 
    public String eliminarCarrito(int id) {
        String sql = "DELETE FROM carrito WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return "Carrito eliminado con éxito.";
    }

    @Override 
    public int contarCarritos(){
        String sql = "SELECT COUNT(id) FROM carrito";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }

}
