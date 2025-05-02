package com.technovaperu.technovaperuwebstore.implement;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.technovaperu.technovaperuwebstore.services.DetallePedidoService;

public class DetallePedidoServiceImpl implements DetallePedidoService{
    
    private JdbcTemplate jdbcTemplate;
    
    @Override 
    public List<Map<String, Object>> obtenerDetallesPedidoPorPedido(int idPedido, int pagina){
        if (pagina <= 0) pagina = 1;
        int offset = (pagina - 1) * 10;
        int limit = 10;
        String sql = "SELECT * FROM detalle_pedido WHERE id_pedido = ? ORDER BY fecha_agregado DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.queryForList(sql, idPedido, limit, offset);
    }

    @Override 
    public Map<String, Object> obtenerDetallePedidoPorId(int id){
        String sql = "SELECT * FROM detalle_pedido WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @Override 
    public String crearDetallePedido(Map<String, Object> detalle){
        String sql = "INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, precio_unitario) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql, detalle.get("id_pedido"), detalle.get("id_producto"), detalle.get("cantidad"), detalle.get("precio_unitario"));
        return "Detalle creado con éxito.";
    }
    
    @Override 
    public String actualizarDetallePedido(int id, Map<String, Object> detalle){
        String sql = "UPDATE detalle_pedido SET id_pedido = ?, id_producto = ?, cantidad = ?, precio_unitario = ? WHERE id = ?";
        jdbcTemplate.update(sql, detalle.get("id_pedido"), detalle.get("id_producto"), detalle.get("cantidad"), detalle.get("precio_unitario"), id);
        return "Detalle actualizado con éxito.";
    }
    
    @Override 
    public String eliminarDetallePedido(int id){
        String sql = "DELETE FROM detalle_pedido WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return "Detalle eliminado con éxito.";
    }
    
    @Override 
    public int contarDetallesPedido(){
        String sql = "SELECT COUNT(id) FROM detalle_pedido";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }
}
