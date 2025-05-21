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
import com.technovaperu.technovaperuwebstore.model.dto.base.ProductoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearProductoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarProductoDTO;
import com.technovaperu.technovaperuwebstore.services.ProductoService;

/**
 * Implementación de la interfaz {@link ProductoService} que utiliza JDBC para acceder a la base de datos.
 * 
 * @author Marcos Zumaran
 */
@Service
public class ProductoServiceImpl implements ProductoService {
    
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductoServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * RowMapper para convertir los resultados de la consulta en objetos {@link ProductoDTO}.
     */
    private final RowMapper<ProductoDTO> productoRowMapper = (rs, rowNum) -> ProductoDTO.builder()
            .id(rs.getInt("id"))
            .idProveedor(rs.getInt("id_proveedor"))
            .nombre(rs.getString("nombre"))
            .descripcion(rs.getString("descripcion"))
            .stock(rs.getInt("stock"))
            .build();
    
    /**
     * Obtiene todos los productos de la base de datos.
     * 
     * @param pagina Número de página a obtener.
     * @return Lista de productos.
     */
    @Override 
    @Transactional(readOnly = true)
    public List<ProductoDTO> obtenerTodosLosProductos(int pagina){
        if (pagina <= 0) pagina = 1;
        int offset = (pagina - 1) * 10;
        int limit = 10;
        String sql = "SELECT * FROM producto ORDER BY id_producto DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, productoRowMapper, limit, offset);
    }

    /**
     * Obtiene todos los productos de una categoría.
     * 
     * @param idCategoria ID de la categoría a obtener los productos de.
     * @param pagina Número de página a obtener.
     * @return Lista de productos.
     */
    @Override 
    @Transactional(readOnly = true)
    public List<ProductoDTO> obtenerProductosPorCategoria(int idCategoria, int pagina){
        if (pagina <= 0) pagina = 1;
        int offset = (pagina - 1) * 10;
        int limit = 10;
        String sql = "SELECT * FROM producto_categoria WHERE id_categoria = ? ORDER BY id_producto DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, productoRowMapper, idCategoria, limit, offset);
    }

    /**
     * Obtiene un producto por su ID.
     * 
     * @param id ID del producto a obtener.
     * @return Producto con el ID especificado.
     * @throws RecursoNoEncontradoException Si no se encuentra el producto con el ID especificado.
     */
    @Override 
    @Transactional(readOnly = true)
    public ProductoDTO obtenerProductoPorId(int id){
        try {
            String sql = "SELECT * FROM producto WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, productoRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new RecursoNoEncontradoException("Producto", "id", id);
        }
    }

    /**
     * Crea un nuevo producto.
     * 
     * @param producto Información del producto a crear.
     * @return Producto creado.
     */
    @Override 
    @Transactional
    public ProductoDTO crearProducto(CrearProductoDTO producto){
        String sql = "INSERT INTO producto (id_proveedor, nombre, descripcion, stock) VALUES(?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, producto.getIdProveedor());
            ps.setString(2, producto.getNombre());
            ps.setString(3, producto.getDescripcion());
            ps.setInt(4, producto.getStock());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null) {
            throw new RuntimeException("No se pudo obtener el ID generado para el producto");
        }

        Integer id = Objects.requireNonNull(keyHolder.getKey()).intValue();

        return ProductoDTO.builder()
                .id(id)
                .idProveedor(producto.getIdProveedor())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .stock(producto.getStock())
                .fechaRegistro(LocalDateTime.now())
                .fechaActualizacion(LocalDateTime.now())
                .build();
    }
    
    /**
     * Actualiza un producto existente.
     * 
     * @param id ID del producto a actualizar.
     * @param producto Información del producto actualizado.
     * @throws RecursoNoEncontradoException Si no se encuentra el producto con el ID especificado.
     */
    @Override 
    @Transactional
    public void actualizarProducto(int id, ActualizarProductoDTO producto){
        if (!existeProductoPorId(id)) {
            throw new RecursoNoEncontradoException("Producto", "id", id);
        }
        String sql = "UPDATE producto SET id_proveedor = ?, nombre = ?, descripcion = ?, stock = ? WHERE id = ?";
        jdbcTemplate.update(sql, producto.getIdProveedor(), producto.getNombre(), producto.getDescripcion(), producto.getStock(), id);
    }
    
    /**
     * Elimina un producto existente.
     * 
     * @param id ID del producto a eliminar.
     * @throws RecursoNoEncontradoException Si no se encuentra el producto con el ID especificado.
     */
    @Override 
    @Transactional
    public void eliminarProducto(int id){
        if (!existeProductoPorId(id)) {
            throw new RecursoNoEncontradoException("Producto", "id", id);
        }
        String sql = "DELETE FROM producto WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    /**
     * Obtiene el número de productos por categoría.
     * 
     * @param idCategoria ID de la categoría a obtener el número de productos de.
     * @return Número de productos por categoría.
     */
    @Override 
    @Transactional(readOnly = true)
    public int contarProductosPorCategoria(int idCategoria){
        String sql = "SELECT COUNT(id) FROM producto WHERE id_categoria = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }
    
    /**
     * Obtiene el número total de productos.
     * 
     * @return Número total de productos.
     */
    @Override 
    @Transactional(readOnly = true)
    public int contarProductos(){
        String sql = "SELECT COUNT(id) FROM producto";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }

    /**
     * Verifica si existe un producto con el ID especificado.
     * 
     * @param id ID del producto a verificar.
     * @return Si existe el producto con el ID especificado.
     */
    @Override
    @Transactional(readOnly = true)
    public boolean existeProductoPorId(int id) {
        String sql = "SELECT COUNT(id) FROM producto WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }
}

