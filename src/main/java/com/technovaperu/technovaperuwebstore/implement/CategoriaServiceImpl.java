package com.technovaperu.technovaperuwebstore.implement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.technovaperu.technovaperuwebstore.services.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Map<String, Object>> obtenerTodasLasCategorias(){
        String sql = "SELECT * FROM categoria";
        return jdbcTemplate.queryForList(sql);
    }

    @Override 
    public Map<String, Object> obtenerCategoriaPorId(int id){
        String sql = "SELECT * FROM categoria WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }
    
    @Override 
    public String crearCategoria(Map<String, Object> categoria){
        String sql = "INSERT INTO categoria (nombre, descripcion) VALUES(?, ?)";
        jdbcTemplate.update(sql, categoria.get("nombre"), categoria.get("descripcion"));
        return "Categoria creada con éxito.";
    }
    
    @Override 
    public String actualizarCategoria(int id, Map<String, Object> categoria){
        String sql = "UPDATE categoria SET nombre = ?, descripcion = ? WHERE id = ?";
        jdbcTemplate.update(sql, categoria.get("nombre"), categoria.get("descripcion"), id);
        return "Categoria actualizada con éxito.";
    }
    
    @Override
    public String eliminarCategoria(int id){
        String sql = "DELETE FROM categoria WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return "Categoria eliminada con éxito.";
    }

    @Override 
    public int contarCategorias(){
        String sql = "SELECT COUNT(id) FROM categoria";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }

}
