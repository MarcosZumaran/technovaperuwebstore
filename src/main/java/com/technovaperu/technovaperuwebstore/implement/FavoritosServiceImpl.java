package com.technovaperu.technovaperuwebstore.implement;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.technovaperu.technovaperuwebstore.exception.RecursoNoEncontradoException;
import com.technovaperu.technovaperuwebstore.model.dto.base.FavoritosDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearFavoritoDTO;
import com.technovaperu.technovaperuwebstore.services.FavoritosService;

@Service
public class FavoritosServiceImpl implements FavoritosService{
    
    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor para injectar la dependencia del {@link JdbcTemplate}.
     * @param jdbcTemplate el objeto {@link JdbcTemplate} que se va a utilizar para hacer operaciones en la base de datos.
     */
    @Autowired
    public FavoritosServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<FavoritosDTO> favoritosRowMapper = (rs, rowNum) -> FavoritosDTO.builder()
            .id(rs.getInt("id"))
            .idUsuario(rs.getInt("id_usuario"))
            .idProducto(rs.getInt("id_producto"))
            .build();

    /**
     * Obtiene una lista de favoritos de un usuario paginados.
     * @param idUsuario el ID del usuario a obtener los favoritos.
     * @param pagina el n mero de p gina a obtener.
     * @return una lista de {@link FavoritosDTO} con la informaci n de los favoritos del usuario.
     */
    @Override
    @Transactional(readOnly = true)
    public List<FavoritosDTO> obtenerFavoritosPorUsuario(int idUsuario, int pagina){
        if (pagina <= 0) pagina = 1;
        int offset = (pagina - 1) * 10;
        int limit = 10;
        String sql = "SELECT * FROM favorito WHERE id_usuario = ? ORDER BY fecha_agregado DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, favoritosRowMapper, idUsuario, limit, offset);
    }

    /**
     * Obtiene un favorito por su ID.
     * @param id el ID del favorito a obtener.
     * @return un objeto {@link FavoritosDTO} con la informaci n del favorito. Si no existe un favorito con ese ID se lanza una excepci n {@link RecursoNoEncontradoException}.
     */
    @Override
    @Transactional(readOnly = true)
    public FavoritosDTO obtenerFavoritoPorId(int id){
        try {
            String sql = "SELECT * FROM favorito WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, favoritosRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new RecursoNoEncontradoException("Favorito", "id", id);
        }
    }

    /**
     * Crea un nuevo favorito.
     * @param favorito el objeto {@link CrearFavoritoDTO} con la informaci n del favorito a crear.
     * @return un objeto {@link FavoritosDTO} con la informaci n del nuevo favorito.
     */
    @Override
    @Transactional
    public FavoritosDTO crearFavorito(CrearFavoritoDTO favorito){
        String sql = "INSERT INTO favorito (id_usuario, id_producto) VALUES(?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, favorito.getIdUsuario());
            ps.setInt(2, favorito.getIdProducto());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null) {
            throw new RuntimeException("No se pudo obtener el ID generado para el favorito");
        }

        Integer id = Objects.requireNonNull(keyHolder.getKey()).intValue();

        return FavoritosDTO.builder()
                .id(id)
                .idUsuario(favorito.getIdUsuario())
                .idProducto(favorito.getIdProducto())
                .build();
    }

    /**
     * Elimina un favorito por su ID.
     * @param id el ID del favorito a eliminar. Si no existe un favorito con ese ID se lanza una excepci n {@link RecursoNoEncontradoException}.
     */
    @Override
    @Transactional
    public void eliminarFavorito(int id){
        if (!existeFavoritoPorId(id)) {
            throw new RecursoNoEncontradoException("Favorito", "id", id);
        }
        String sql = "DELETE FROM favorito WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    /**
     * Obtiene el n mero total de favoritos en la base de datos.
     * @return el n mero total de favoritos.
     */
    @Override
    @Transactional(readOnly = true)
    public int contarFavoritos(){
        String sql = "SELECT COUNT(id) FROM favorito";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }

    /**
     * Verifica si existe un favorito con el ID proporcionado.
     * @param id el ID del favorito a verificar.
     * @return true si existe un favorito con ese ID, false en caso contrario.
     */
    @Override
    @Transactional(readOnly = true)
    public boolean existeFavoritoPorId(int id) {
        String sql = "SELECT COUNT(id) FROM favorito WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

}
