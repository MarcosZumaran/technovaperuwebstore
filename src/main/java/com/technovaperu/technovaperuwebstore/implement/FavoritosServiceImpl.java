package com.technovaperu.technovaperuwebstore.implement;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.technovaperu.technovaperuwebstore.exception.RecursoNoEncontradoException;
import com.technovaperu.technovaperuwebstore.model.dto.base.FavoritosDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearFavoritoDTO;
import com.technovaperu.technovaperuwebstore.services.FavoritosService;
import com.technovaperu.technovaperuwebstore.util.DynamicSqlBuilder;

@Service
public class FavoritosServiceImpl implements FavoritosService{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger log = LoggerFactory.getLogger(FavoritosServiceImpl.class);

    private final RowMapper<FavoritosDTO> favoritosRowMapper = (rs, rowNum) -> FavoritosDTO.builder()
            .id(rs.getInt("id"))
            .idUsuario(rs.getInt("id_usuario"))
            .idProducto(rs.getInt("id_producto"))
            .build();

    private List<FavoritosDTO> ejecutarConsultaListaFavoritos(String sql, Object... parametros) {
        try{
            log.info("Ejecutando consulta: {}", sql);
            return jdbcTemplate.query(sql, favoritosRowMapper, parametros);
        } catch (EmptyResultDataAccessException e) {
            log.error("Error al ejecutar la consulta: {}", e.getMessage());
            throw new RecursoNoEncontradoException("Presentaciones de Productos", "filtro aplicado", null);
        }
    }

    @Override
    public List<FavoritosDTO> obtenerFavoritosPorUsuario(long idUsuario, int pagina){
        String sql = "SELECT * FROM favoritos WHERE id_usuario = ?";
        return ejecutarConsultaListaFavoritos(sql, idUsuario);
    }

    @Override
    public FavoritosDTO obtenerFavoritoPorId(long id){
        try{
            log.info("Ejecutando consulta: {}", "SELECT * FROM favoritos WHERE id = ?");
            return jdbcTemplate.queryForObject("SELECT * FROM favoritos WHERE id = ?", favoritosRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            log.error("Error al ejecutar la consulta: {}", e.getMessage());
            throw new RecursoNoEncontradoException("Presentaciones de Productos", "filtro aplicado", null);
        }
    }

    @Override
    public FavoritosDTO crearFavorito(CrearFavoritoDTO favorito){

        Map<String, Object> fields = new LinkedHashMap<>();

        fields.put("id_usuario", favorito.getIdUsuario());
        fields.put("id_producto", favorito.getIdProducto());
        fields.put("fecha_creacion", Timestamp.valueOf(LocalDateTime.now()));
        fields.put("fecha_modificacion", Timestamp.valueOf(LocalDateTime.now()));

        String sql = DynamicSqlBuilder.buildInsertSql("favoritos", fields);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        log.info("Creando favorito con datos: {}", fields);

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            for (Object value : fields.values()) {
                ps.setObject(i++, value);
            }
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null) {
            log.error("Error al obtener el ID del favorito creado");
            throw new RecursoNoEncontradoException("Favorito", "no se pudo crear", null);
        }
        return obtenerFavoritoPorId(key.longValue());

    }

    @Override
    public void eliminarFavorito(long id){
        if(!existeFavoritoPorId(id)){
            throw new RecursoNoEncontradoException("Favorito", "filtro aplicado", String.valueOf(id));
        }
        log.info("Eliminando favorito con ID: {}", id);
        String sql = DynamicSqlBuilder.buildDeleteSql("favoritos", "id = ?");
        jdbcTemplate.update(sql, id);
    }

    @Override
    public int contarFavoritos(){
        String sql = DynamicSqlBuilder.buildCountSql("favoritos", "id");
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count != null && count > 0 ? count : 0;
    }

    @Override
    public boolean existeFavoritoPorId(long id){
        String sql = DynamicSqlBuilder.buildCountSql("favoritos", "id = ?");
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

}
