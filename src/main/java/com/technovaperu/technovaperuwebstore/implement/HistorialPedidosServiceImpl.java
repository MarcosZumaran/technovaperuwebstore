
package com.technovaperu.technovaperuwebstore.implement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.technovaperu.technovaperuwebstore.services.HistorialPedidosService;

@Service
public class HistorialPedidosServiceImpl implements HistorialPedidosService{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> obtenerHistorialPorPedido(int idPedido, int pagina){
        if (pagina <= 0) pagina = 1;
        int offset = (pagina - 1) * 10;
        int limit = 10;
        String sql = "SELECT * FROM historial_pedidos WHERE id_pedido = ? ORDER BY fecha_agregado DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.queryForList(sql, idPedido, limit, offset);
    }

    @Override
    public Map<String, Object> obtenerHistorialPorId(int id){
        String sql = "SELECT * FROM historial_pedidos WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @Override
    public String crearHistorialPedido(Map<String, Object> historial){
        String sql = "INSERT INTO historial_pedidos (id_pedido, estado, fecha_estado) VALUES(?, ?, NOW())";
        jdbcTemplate.update(sql, historial.get("id_pedido"), historial.get("estado"));
        return "Historial creado con éxito.";
    }

    @Override
    public String actualizarHistorialPedido(int id, Map<String, Object> historial){
        String sql = "UPDATE historial_pedidos SET estado = ?, fecha_estado = ? WHERE id = ?";
        jdbcTemplate.update(sql, historial.get("estado"), historial.get("fecha_estado"), id);
        return "Historial actualizado con éxito.";
    }

    @Override
    public String eliminarHistorialPedido(int id){
        String sql = "DELETE FROM historial_pedidos WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return "Historial eliminado con éxito.";
    }

    @Override
    public int contarHistorialPedidos(){
        String sql = "SELECT COUNT(id) FROM historial_pedidos";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }

}