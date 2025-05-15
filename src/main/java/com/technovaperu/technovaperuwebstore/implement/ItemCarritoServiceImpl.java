package com.technovaperu.technovaperuwebstore.implement;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
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
import com.technovaperu.technovaperuwebstore.model.dto.base.ItemCarritoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearItemCarritoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarItemCarritoDTO;
import com.technovaperu.technovaperuwebstore.services.ItemCarritoService;

@Service
public class ItemCarritoServiceImpl implements ItemCarritoService{
    
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ItemCarritoServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Mapeador de filas para convertir ResultSet en objetos ItemCarritoDTO
    private final RowMapper<ItemCarritoDTO> itemCarritoRowMapper = (rs, rowNum) -> ItemCarritoDTO.builder()
            .id(rs.getInt("id"))
            .idProducto(rs.getInt("id_producto"))
            .cantidad(rs.getInt("cantidad"))
            .fechaAgregado(rs.getTimestamp("fecha_agregado").toLocalDateTime())
            .build();
    
    /**
     * Obtiene una lista de items de carrito por el ID del carrito.
     * @param idCarrito ID del carrito al que pertenecen los items.
     * @param pagina Número de página para la paginación.
     * @return Lista de ItemCarritoDTO.
     */
    @Override 
    @Transactional(readOnly = true)
    public List<ItemCarritoDTO> obtenerItemsCarritoPorCarrito(int idCarrito, int pagina){
        if (pagina <= 0) pagina = 1;
        int offset = (pagina - 1) * 10;
        int limit = 10;
        String sql = "SELECT * FROM item_carrito WHERE id_carrito = ? ORDER BY fecha_agregado DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, itemCarritoRowMapper, idCarrito, limit, offset);
    }

    /**
     * Obtiene un item de carrito por su ID.
     * @param id ID del item de carrito.
     * @return ItemCarritoDTO correspondiente al ID proporcionado.
     * @throws RecursoNoEncontradoException si no se encuentra el item.
     */
    @Override 
    @Transactional(readOnly = true)
    public ItemCarritoDTO obtenerItemCarritoPorId(int id){
        try {
            String sql = "SELECT * FROM item_carrito WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, itemCarritoRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new RecursoNoEncontradoException("Item Carrito", "id", id);
        }
    }

    /**
     * Crea un nuevo item de carrito en la base de datos.
     * @param item Datos del item a crear.
     * @return ItemCarritoDTO creado.
     */
    @Override 
    @Transactional
    public ItemCarritoDTO crearItemCarrito(CrearItemCarritoDTO item){
        String sql = "INSERT INTO item_carrito (id_carrito, id_producto, cantidad) VALUES(?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, item.getIdCarrito());
            ps.setInt(2, item.getIdProducto());
            ps.setInt(3, item.getCantidad());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null) {
            throw new RuntimeException("No se pudo obtener el ID generado para el item");
        }

        Integer id = Objects.requireNonNull(keyHolder.getKey()).intValue();

        return ItemCarritoDTO.builder()
                .id(id)
                .idCarrito(item.getIdCarrito())
                .idProducto(item.getIdProducto())
                .cantidad(item.getCantidad())
                .fechaAgregado(LocalDateTime.now())
                .build();
    }
    
    /**
     * Actualiza la cantidad de un item de carrito existente.
     * @param id ID del item a actualizar.
     * @param item Datos de actualización.
     * @throws RecursoNoEncontradoException si el item no existe.
     */
    @Override 
    @Transactional
    public void actualizarItemCarrito(int id, ActualizarItemCarritoDTO item){
        if (!existeItemCarritoPorId(id)) {
            throw new RecursoNoEncontradoException("Item Carrito", "id", id);
        }
        String sql = "UPDATE item_carrito SET cantidad = ? WHERE id = ?";
        jdbcTemplate.update(sql, item.getCantidad(), id);
    }
    
    /**
     * Elimina un item de carrito por su ID.
     * @param id ID del item a eliminar.
     * @throws RecursoNoEncontradoException si el item no existe.
     */
    @Override 
    @Transactional
    public void eliminarItemCarrito(int id){
        if (!existeItemCarritoPorId(id)) {
            throw new RecursoNoEncontradoException("Item Carrito", "id", id);
        }
        String sql = "DELETE FROM item_carrito WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
    
    /**
     * Cuenta el número total de items de carrito.
     * @return Cantidad de items de carrito.
     */
    @Override 
    @Transactional(readOnly = true)
    public int contarItemsCarrito(){
        String sql = "SELECT COUNT(id) FROM item_carrito";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }

    /**
     * Verifica si un item de carrito existe por su ID.
     * @param id ID del item de carrito.
     * @return true si el item existe, false de lo contrario.
     */
    @Override
    @Transactional(readOnly = true)
    public boolean existeItemCarritoPorId(int id) {
        String sql = "SELECT COUNT(id) FROM item_carrito WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }
}
