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
import com.technovaperu.technovaperuwebstore.model.dto.base.ProveedorDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearProveedorDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarProveedorDTO;
import com.technovaperu.technovaperuwebstore.services.ProveedorService;

/**
 * Implementacion de la interfaz {@link ProveedorService} que utiliza JDBC para
 * interactuar con la base de datos.
 *
 * @author Marcos Zumaran
 */
@Service
public class ProveedorServiceImpl implements ProveedorService {
    
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProveedorServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * RowMapper para convertir un registro de la base de datos en un objeto
     * {@link ProveedorDTO}.
     */
    private final RowMapper<ProveedorDTO> proveedorRowMapper = (rs, rowNum) -> ProveedorDTO.builder()
            .id(rs.getInt("id"))
            .nombre(rs.getString("nombre"))
            .direccion(rs.getString("direccion"))
            .telefono(rs.getString("telefono"))
            .email(rs.getString("email"))
            .pais(rs.getString("pais"))
            .build();
    
    @Override 
    @Transactional(readOnly = true)
    public List<ProveedorDTO> obtenerTodosLosProveedores(int pagina){
        // Si la pagina es menor o igual a 0, se establece en 1
        if (pagina <= 0) pagina = 1;
        // Se calcula el offset y el limite para la consulta
        int offset = (pagina - 1) * 10;
        int limit = 10;
        // Se consulta la base de datos con la consulta SQL
        String sql = "SELECT * FROM proveedor ORDER BY id DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, proveedorRowMapper, limit, offset);
    }

    @Override 
    public ProveedorDTO obtenerProveedorPorId(int id) {
        try {
            // Se consulta la base de datos con la consulta SQL
            String sql = "SELECT * FROM proveedor WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, proveedorRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            // Si no se encuentra el proveedor, se lanza una excepcion
            throw new RecursoNoEncontradoException("Proveedor", "id", id);
        }
    }

    @Override 
    @Transactional
    public ProveedorDTO crearProveedor(CrearProveedorDTO proveedor){
        // Se crea la consulta SQL para insertar un nuevo proveedor
        String sql = "INSERT INTO proveedor (nombre, direccion, telefono, email, pais) VALUES(?, ?, ?, ?, ?)";
        // Se utiliza un KeyHolder para obtener el ID generado por la base de datos
        KeyHolder keyHolder = new GeneratedKeyHolder();

        // Se ejecuta la consulta con los datos del proveedor
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getDireccion());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getEmail());
            ps.setString(5, proveedor.getPais());
            return ps;
        }, keyHolder);

        // Se obtiene el ID generado por la base de datos
        Number key = keyHolder.getKey();
        if (key == null) {
            // Si no se puede obtener el ID, se lanza una excepcion
            throw new RuntimeException("No se pudo obtener el ID generado para el proveedor");
        }

        // Se crea el objeto ProveedorDTO con los datos del proveedor y su ID
        Integer id = Objects.requireNonNull(keyHolder.getKey()).intValue();

        return ProveedorDTO.builder()
                .id(id)
                .nombre(proveedor.getNombre())
                .direccion(proveedor.getDireccion())
                .telefono(proveedor.getTelefono())
                .email(proveedor.getEmail())
                .pais(proveedor.getPais())
                .build();
    }
    
    @Override 
    @Transactional
    public void actualizarProveedor(int id, ActualizarProveedorDTO proveedor){
        if (!existeProveedorPorId(id)) {
            throw new RecursoNoEncontradoException("Proveedor", "id", id);
        }
        // Se crea la consulta SQL para actualizar un proveedor
        String sql = "UPDATE proveedor SET nombre = ?, direccion = ?, telefono = ?, email = ?, pais = ? WHERE id = ?";
        // Se ejecuta la consulta con los datos del proveedor
        jdbcTemplate.update(sql, proveedor.getNombre(), proveedor.getDireccion(), proveedor.getTelefono(), proveedor.getEmail(), proveedor.getPais(), id);
    }
    
    @Override
    @Transactional
    public void eliminarProveedor(int id){
        if (!existeProveedorPorId(id)) {
            throw new RecursoNoEncontradoException("Proveedor", "id", id);
        }
        // Se crea la consulta SQL para eliminar un proveedor
        String sql = "DELETE FROM proveedor WHERE id = ?";
        // Se ejecuta la consulta con el ID del proveedor
        jdbcTemplate.update(sql, id);
    }

    @Override 
    @Transactional(readOnly = true)
    public int contarProveedores(){
        // Se crea la consulta SQL para contar los proveedores
        String sql = "SELECT COUNT(id) FROM proveedor";
        // Se ejecuta la consulta y se obtiene el resultado
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        // Se devuelve el resultado o 0 si no se encuentra ningun proveedor
        return (count != null) ? count : 0;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeProveedorPorId(int id) {
        // Se crea la consulta SQL para verificar si existe un proveedor con el ID
        String sql = "SELECT COUNT(id) FROM proveedor WHERE id = ?";
        // Se ejecuta la consulta con el ID del proveedor
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        // Se devuelve true si existe el proveedor o false en caso contrario
        return count != null && count > 0;
    }
}

