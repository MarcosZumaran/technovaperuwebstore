package com.technovaperu.technovaperuwebstore.implement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.technovaperu.technovaperuwebstore.services.FavoritosService;

@Service
public class FavoritosServiceImpl implements FavoritosService{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> obtenerFavoritosPorUsuario(int idUsuario, int pagina){
        if (pagina <= 0) pagina = 1;
        int offset = (pagina - 1) * 10;
        int limit = 10;
        String sql = "SELECT * FROM favoritos WHERE id_usuario = ? ORDER BY fecha_agregado DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.queryForList(sql, idUsuario, limit, offset);
    }

    @Override
    public Map<String, Object> obtenerFavoritoPorId(int id){
        String sql = "SELECT * FROM favoritos WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @Override
    public String crearFavorito(Map<String, Object> favorito){
        String sql = "INSERT INTO favoritos (id_usuario, id_producto) VALUES(?, ?)";
        jdbcTemplate.update(sql, favorito.get("id_usuario"), favorito.get("id_producto"));
        return "Favorito creado con éxito.";
    }

    @Override
    public String actualizarFavorito(int id, Map<String, Object> favorito){
        String sql = "UPDATE favoritos SET id_usuario = ?, id_producto = ? WHERE id = ?";
        jdbcTemplate.update(sql, favorito.get("id_usuario"), favorito.get("id_producto"), id);
        return "Favorito actualizado con éxito.";
    }

    @Override
    public String eliminarFavorito(int id){
        String sql = "DELETE FROM favoritos WHERE id = ?";
        jdbcTemplate.update(sql, id);
        return "Favorito eliminado con éxito.";
    }

    @Override
    public int contarFavoritos(){
        String sql = "SELECT COUNT(id) FROM favoritos";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }

}
