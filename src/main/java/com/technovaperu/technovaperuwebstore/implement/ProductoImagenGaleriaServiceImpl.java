package com.technovaperu.technovaperuwebstore.implement;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.technovaperu.technovaperuwebstore.services.ProductoImagenGaleriaService;

public class ProductoImagenGaleriaServiceImpl implements ProductoImagenGaleriaService{
    
    private JdbcTemplate jdbcTemplate;
    
    @Override 
    public List<Map<String, Object>> obtenerImagenesGaleriaPorProducto(int id){
        String sql = "SELECT * FROM producto_imagen_galeria WHERE id_producto = ?";
        return jdbcTemplate.queryForList(sql, id);
    }

    @Override 
    public Map<String, Object> obtenerImagenGaleriaPorId(int id){
        String sql = "SELECT * FROM producto_imagen_galeria WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @Override 
    public String crearImagenGaleria(Map<String, Object> imagen){
        String sql = "INSERT INTO producto_imagen_galeria (id_producto, url) VALUES(?, ?)";
        jdbcTemplate.update(sql, imagen.get("id_producto"), imagen.get("url"));
        return "Imagen creada con éxito.";
    }
    
    @Override 
    public String actualizarImagenGaleria(int id, Map<String, Object> imagen){
        String sql = "UPDATE producto_imagen_galeria SET url = ? WHERE id = ?";
        jdbcTemplate.update(sql, imagen.get("url"), id);
        return "Imagen actualizada con éxito.";
    }
    
    @Override 
    public String eliminarImagenGaleria(int id){
        String sql = "DELETE FROM producto_imagen_galeria WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return "Imagen eliminada con éxito.";
    }
    
    @Override 
    public int contarImagenesGaleriaPorProducto(int id){
        String sql = "SELECT COUNT(id) FROM producto_imagen_galeria WHERE id_producto = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }
    
    @Override 
    public int contarImagenesGaleria(){
        String sql = "SELECT COUNT(id) FROM producto_imagen_galeria";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }
}
