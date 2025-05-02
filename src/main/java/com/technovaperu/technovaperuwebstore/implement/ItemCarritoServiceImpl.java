package com.technovaperu.technovaperuwebstore.implement;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.technovaperu.technovaperuwebstore.services.ItemCarritoService;

public class ItemCarritoServiceImpl implements ItemCarritoService{
    
    private JdbcTemplate jdbcTemplate;
    
    @Override 
    public List<Map<String, Object>> obtenerItemsCarritoPorCarrito(int idCarrito, int pagina){
        if (pagina <= 0) pagina = 1;
        int offset = (pagina - 1) * 10;
        int limit = 10;
        String sql = "SELECT * FROM item_carrito WHERE id_carrito = ? ORDER BY fecha_agregado DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.queryForList(sql, idCarrito, limit, offset);
    }

    @Override 
    public Map<String, Object> obtenerItemCarritoPorId(int id){
        String sql = "SELECT * FROM item_carrito WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @Override 
    public String crearItemCarrito(Map<String, Object> item){
        String sql = "INSERT INTO item_carrito (id_carrito, id_producto, cantidad) VALUES(?, ?, ?)";
        jdbcTemplate.update(sql, item.get("id_carrito"), item.get("id_producto"), item.get("cantidad"));
        return "Item creado con éxito.";
    }
    
    @Override 
    public String actualizarItemCarrito(int id, Map<String, Object> item){
        String sql = "UPDATE item_carrito SET id_carrito = ?, id_producto = ?, cantidad = ? WHERE id = ?";
        jdbcTemplate.update(sql, item.get("id_carrito"), item.get("id_producto"), item.get("cantidad"), id);
        return "Item actualizado con éxito.";
    }
    
    @Override 
    public String eliminarItemCarrito(int id){
        String sql = "DELETE FROM item_carrito WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return "Item eliminado con éxito.";
    }
    
    @Override 
    public int contarItemsCarrito(){
        String sql = "SELECT COUNT(id) FROM item_carrito";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }
}
