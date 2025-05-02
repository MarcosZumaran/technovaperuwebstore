
package com.technovaperu.technovaperuwebstore.implement;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.technovaperu.technovaperuwebstore.services.ProductoImagenPortadaService;

@Service
public class ProductoImagenPortadaServiceImpl implements ProductoImagenPortadaService{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override 
    public Map<String, Object> obtenerImagenPortadaPorProducto(int idProducto){
        String sql = "SELECT * FROM producto_imagen_portada WHERE id_producto = ?";
        return jdbcTemplate.queryForMap(sql, idProducto);
    }

    @Override 
    public Map<String, Object> obtenerImagenPortadaPorId(int id){
        String sql = "SELECT * FROM producto_imagen_portada WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @Override 
    public String crearImagenPortada(Map<String, Object> imagen){
        String sql = "INSERT INTO producto_imagen_portada (id_producto, url) VALUES(?, ?)";
        jdbcTemplate.update(sql, imagen.get("id_producto"), imagen.get("url"));
        return "Imagen creada con éxito.";
    }
    
    @Override 
    public String actualizarImagenPortada(int id, Map<String, Object> imagen){
        String sql = "UPDATE producto_imagen_portada SET url = ? WHERE id = ?";
        jdbcTemplate.update(sql, imagen.get("url"), id);
        return "Imagen actualizada con éxito.";
    }
    
    @Override 
    public String eliminarImagenPortada(int id){
        String sql = "DELETE FROM producto_imagen_portada WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return "Imagen eliminada con éxito.";
    }
    
    @Override 
    public int contarImagenesPortada(){
        String sql = "SELECT COUNT(id) FROM producto_imagen_portada";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }
}
