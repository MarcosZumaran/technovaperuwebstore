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
import com.technovaperu.technovaperuwebstore.model.dto.base.DetallePedidoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearDetallePedidoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarDetallePedidoDTO;
import com.technovaperu.technovaperuwebstore.services.DetallePedidoService;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService{
    
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DetallePedidoServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Mapeador de filas para convertir los resultados de la consulta en DetallePedidoDTO
    private final RowMapper<DetallePedidoDTO> detallePedidoRowMapper = (rs, rowNum) -> DetallePedidoDTO.builder()
            .id(rs.getInt("id"))
            .idPedido(rs.getInt("id_pedido"))
            .idProducto(rs.getInt("id_producto"))
            .cantidad(rs.getInt("cantidad"))
            .precioUnitario(rs.getBigDecimal("precio_unitario"))
            .build();
    
    /**
     * Obtiene los detalles de un pedido por su ID de pedido.
     * 
     * @param idPedido ID del pedido.
     * @param pagina   N mero de p gina a obtener.
     * @return Lista de detalles de pedidos.
     */
    @Override
    @Transactional(readOnly = true)
    public List<DetallePedidoDTO> obtenerDetallesPedidoPorPedido(int idPedido, int pagina){
        if (pagina <= 0) pagina = 1;
        int offset = (pagina - 1) * 10;
        int limit = 10;
        String sql = "SELECT * FROM detalle_pedido WHERE id_pedido = ? ORDER BY id DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, detallePedidoRowMapper, idPedido, limit, offset);
    }

    /**
     * Obtiene el detalle de un pedido por su ID.
     * 
     * @param id ID del detalle de pedido.
     * @return Detalle de pedido.
     */
    @Override 
    @Transactional(readOnly = true)
    public DetallePedidoDTO obtenerDetallePedidoPorId(int id){
        try {
            String sql = "SELECT * FROM detalle_pedido WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, detallePedidoRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new RecursoNoEncontradoException("Detalle Pedido", "id", id);
        }
    }

    /**
     * Crea un nuevo detalle de pedido.
     * 
     * @param detalle Informaci n del detalle de pedido.
     * @return Detalle de pedido creado.
     */
    @Override 
    @Transactional
    public DetallePedidoDTO crearDetallePedido(CrearDetallePedidoDTO detalle){
        String sql = "INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, precio_unitario) VALUES(?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, detalle.getIdPedido());
            ps.setInt(2, detalle.getIdProducto());
            ps.setInt(3, detalle.getCantidad());
            ps.setBigDecimal(4, detalle.getPrecioUnitario());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null) {
            throw new RuntimeException("No se pudo obtener el ID generado para el detalle");
        }

        Integer id = Objects.requireNonNull(keyHolder.getKey()).intValue();

        return DetallePedidoDTO.builder()
                .id(id)
                .idPedido(detalle.getIdPedido())
                .idProducto(detalle.getIdProducto())
                .cantidad(detalle.getCantidad())
                .precioUnitario(detalle.getPrecioUnitario())
                .build();
    }
    
    /**
     * Actualiza un detalle de pedido.
     * 
     * @param id      ID del detalle de pedido.
     * @param detalle Informaci n del detalle de pedido.
     */
    @Override 
    @Transactional
    public void actualizarDetallePedido(int id, ActualizarDetallePedidoDTO detalle){
        if (!existeDetallePedidoPorId(id)) {
            throw new RecursoNoEncontradoException("Detalle Pedido", "id", id);
        }
        String sql = "UPDATE detalle_pedido SET id_pedido = ?, id_producto = ?, cantidad = ?, precio_unitario = ? WHERE id = ?";
        jdbcTemplate.update(sql, detalle.getIdPedido(), detalle.getIdProducto(), detalle.getCantidad(), detalle.getPrecioUnitario(), id);
    }
    
    /**
     * Elimina un detalle de pedido.
     * 
     * @param id ID del detalle de pedido.
     */
    @Override 
    @Transactional
    public void eliminarDetallePedido(int id){
        if (!existeDetallePedidoPorId(id)) {
            throw new RecursoNoEncontradoException("Detalle Pedido", "id", id);
        }
        String sql = "DELETE FROM detalle_pedido WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
    
    /**
     * Cuenta el n mero total de detalles de pedidos.
     * 
     * @return N mero de detalles de pedidos.
     */
    @Override 
    @Transactional(readOnly = true)
    public int contarDetallesPedido(){
        String sql = "SELECT COUNT(id) FROM detalle_pedido";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }

    /**
     * Verifica si existe un detalle de pedido con el ID especificado.
     * 
     * @param id ID del detalle de pedido.
     * @return true si existe, false en caso contrario.
     */
    @Override
    @Transactional(readOnly = true)
    public boolean existeDetallePedidoPorId(int id) {
        String sql = "SELECT COUNT(id) FROM detalle_pedido WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }
}
