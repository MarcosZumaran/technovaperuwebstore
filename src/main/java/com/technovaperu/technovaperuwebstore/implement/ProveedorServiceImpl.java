package com.technovaperu.technovaperuwebstore.implement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.technovaperu.technovaperuwebstore.services.ProveedorService;

@Service
public class ProveedorServiceImpl implements ProveedorService {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override 
    public List<Map<String, Object>> obtenerTodosLosProveedores(){
        String sql = "SELECT * FROM proveedor";
        return jdbcTemplate.queryForList(sql);
    }

    @Override 
    public Map<String, Object> obtenerProveedorPorId(int id){
        String sql = "SELECT * FROM proveedor WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @Override 
    public String crearProveedor(Map<String, Object> proveedor){
        String sql = "INSERT INTO proveedor (nombre, direccion, telefono, email, pais) VALUES(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, proveedor.get("nombre"), proveedor.get("direccion"), proveedor.get("telefono"), proveedor.get("email"), proveedor.get("pais"));
        return "Proveedor creado con éxito.";
    }
    
    @Override 
    public String actualizarProveedor(int id, Map<String, Object> proveedor){
        String sql = "UPDATE proveedor SET nombre = ?, direccion = ?, telefono = ?, email = ?, pais = ? WHERE id = ?";
        jdbcTemplate.update(sql, proveedor.get("nombre"), proveedor.get("direccion"), proveedor.get("telefono"), proveedor.get("email"), proveedor.get("pais"), id);
        return "Proveedor actualizado con éxito.";
    }
    
    @Override 
    public String eliminarProveedor(int id){
        String sql = "DELETE FROM proveedor WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return "Proveedor eliminado con éxito.";
    }

    @Override 
    public int contarProveedores(){
        String sql = "SELECT COUNT(id) FROM proveedor";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }
}
