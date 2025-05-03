package com.technovaperu.technovaperuwebstore.implement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.technovaperu.technovaperuwebstore.services.ProductoImagenService;

@Service
public class ProductoImagenServiceImpl implements ProductoImagenService{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> obtenerImagenesPorProducto(int id) {
        String sql = "SELECT * FROM producto_imagen WHERE id_producto = ?";
        return jdbcTemplate.queryForList(sql, id);
    }

    @Override
    public Map<String, Object> obtenerImagenPorId(int id) {
        String sql = "SELECT * FROM producto_imagen WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @Override
    public String crearImagen(Map<String, Object> imagen) {
        String sql = "INSERT INTO producto_imagen (id_producto, url, tipo) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, imagen.get("idProducto"), imagen.get("url"), imagen.get("tipo"));
        return "Imagen creada con éxito";
    }

    @Override
    public String actualizarImagen(int id, Map<String, Object> imagen) {
        String sql = "UPDATE producto_imagen SET url = ?, tipo = ? WHERE id = ?";
        jdbcTemplate.update(sql, imagen.get("url"), imagen.get("tipo"), id);
        return "Imagen actualizada con éxito";
    }

    @Override
    public String eliminarImagen(int id) {
        String sql = "DELETE FROM producto_imagen WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return "Imagen eliminada con éxito";
    }

    @Override
    public int contarImagenesPorProducto(int id) {
        String sql = "SELECT COUNT(id) FROM producto_imagen WHERE id_producto = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return (count != null) ? count : 0;
    }

    @Override
    public int contarImagenes() {
        String sql = "SELECT COUNT(id) FROM producto_imagen";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }
}
