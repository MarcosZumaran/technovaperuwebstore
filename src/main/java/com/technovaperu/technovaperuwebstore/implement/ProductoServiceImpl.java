package com.technovaperu.technovaperuwebstore.implement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.technovaperu.technovaperuwebstore.services.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override 
    public List<Map<String, Object>> obtenerTodosLosProductos(int pagina){
        if (pagina <= 0) pagina = 1;
        int offset = (pagina - 1) * 10;
        int limit = 10;
        String sql = "SELECT * FROM productos ORDER BY id_producto DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.queryForList(sql, limit, offset);
    }

    @Override 
    public List<Map<String, Object>> obtenerProductosPorCategoria(int idCategoria, int pagina){
        if (pagina <= 0) pagina = 1;
        int offset = (pagina - 1) * 10;
        int limit = 10;
        String sql = "SELECT * FROM productos WHERE id_categoria = ? ORDER BY id_producto DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.queryForList(sql, idCategoria, limit, offset);
    }

    @Override 
    public Map<String, Object> obtenerProductoPorId(int id){
        String sql = "SELECT * FROM productos WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @Override 
    public String crearProducto(Map<String, Object> producto){
        String sql = "INSERT INTO productos (id_proveedor, nombre, descripcion, precio, descuento, stock) VALUES(?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, producto.get("id_proveedor"), producto.get("nombre"), producto.get("descripcion"), producto.get("precio"), producto.get("descuento"), producto.get("stock"));
        return "Producto creado con éxito.";
    }
    
    @Override 
    public String actualizarProducto(int id, Map<String, Object> producto){
        String sql = "UPDATE productos SET id_proveedor = ?, nombre = ?, descripcion = ?, precio = ?, descuento = ?, stock = ? WHERE id = ?";
        jdbcTemplate.update(sql, producto.get("id_proveedor"), producto.get("nombre"), producto.get("descripcion"), producto.get("precio"), producto.get("descuento"), producto.get("stock"), id);
        return "Producto actualizado con éxito.";
    }
    
    @Override 
    public String eliminarProducto(int id){
        String sql = "DELETE FROM productos WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return "Producto eliminado con éxito.";
    }

    @Override 
    public int contarProductosPorCategoria(int idCategoria){
        String sql = "SELECT COUNT(id) FROM productos WHERE id_categoria = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }
    
    @Override 
    public int contarProductos(){
        String sql = "SELECT COUNT(id) FROM productos";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }
}
