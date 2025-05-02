package com.technovaperu.technovaperuwebstore.implement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.technovaperu.technovaperuwebstore.services.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override 
    public List<Map<String, Object>> obtenerPedidosPorUsuario(int idUsuario, int pagina){
        if (pagina <= 0) pagina = 1;
        int offset = (pagina - 1) * 10;
        int limit = 10;
        String sql = "SELECT * FROM pedidos WHERE id_usuario = ? ORDER BY fecha_agregado DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.queryForList(sql, idUsuario, limit, offset);
    }

    @Override 
    public Map<String, Object> obtenerPedidoPorId(int id){
        String sql = "SELECT * FROM pedidos WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @Override 
    public String crearPedido(Map<String, Object> pedido){
        String sql = "INSERT INTO pedidos (id_usuario, direccion_envio) VALUES(?, ?)";
        jdbcTemplate.update(sql, pedido.get("id_usuario"), pedido.get("direccion_envio"));
        return "Pedido creado con éxito.";
    }
    
    @Override 
    public String actualizarPedido(int id, Map<String, Object> pedido){
        String sql = "UPDATE pedidos SET direccion_envio = ? WHERE id = ?";
        jdbcTemplate.update(sql, pedido.get("direccion_envio"), id);
        return "Pedido actualizado con éxito.";
    }
    
    @Override 
    public String eliminarPedido(int id){
        String sql = "DELETE FROM pedidos WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return "Pedido eliminado con éxito.";
    }

    @Override 
    public int contarPedidosPorUsuario(int idUsuario){
        String sql = "SELECT COUNT(id) FROM pedidos WHERE id_usuario = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }
    
    @Override 
    public int contarPedidos(){
        String sql = "SELECT COUNT(id) FROM pedidos";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }

}
