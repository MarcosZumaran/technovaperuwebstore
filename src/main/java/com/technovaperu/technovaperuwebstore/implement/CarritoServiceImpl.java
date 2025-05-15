package com.technovaperu.technovaperuwebstore.implement;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.technovaperu.technovaperuwebstore.exception.RecursoDuplicadoException;
import com.technovaperu.technovaperuwebstore.exception.RecursoNoEncontradoException;
import com.technovaperu.technovaperuwebstore.model.dto.base.CarritoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearCarritoDTO;
import com.technovaperu.technovaperuwebstore.services.CarritoService;

@Service
public class CarritoServiceImpl implements CarritoService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper reutilizable para CarritoDTO, es un conversion de filas SQL a Objetos, en este caso a CarritoDTO
    private final RowMapper<CarritoDTO> carritoRowMapper = (rs, rowNum) -> CarritoDTO.builder()
            .id(rs.getInt("id"))
            .idUsuario(rs.getInt("id_usuario"))
            .fechaCreacion(rs.getTimestamp("fecha_creacion").toLocalDateTime())
            .build();

    /**
     * Obtiene un carrito por su ID
     * @param id ID del carrito a buscar
     * @return CarritoDTO con la información del carrito
     * @throws RecursoNoEncontradoException si no existe un carrito con ese ID
     */
    @Override
    public CarritoDTO obtenerCarritoPorId(int id) {
        String sql = "SELECT * FROM carrito WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, carritoRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new RecursoNoEncontradoException("No se encontró carrito con ID: " + id, sql, e);
        }
    }

    /**
     * Obtiene un carrito por su usuario
     * @param idUsuario ID del usuario a buscar
     * @return CarritoDTO con la información del carrito
     * @throws RecursoNoEncontradoException si no existe un carrito para ese usuario
     */
    @Override
    public CarritoDTO obtenerCarritoPorUsuario(int idUsuario) {
        String sql = "SELECT * FROM carrito WHERE id_usuario = ?";
        try {
            return jdbcTemplate.queryForObject(sql, carritoRowMapper, idUsuario); 
        } catch (EmptyResultDataAccessException e) {
            throw new RecursoNoEncontradoException("No se encontró carrito con ID de usuario: " + idUsuario, sql, e);
        }
    }

    /**
     * Crea un nuevo carrito
     * @param carrito datos del carrito a crear
     * @return ID del carrito creado
     * @throws RecursoDuplicadoException si el usuario ya tiene un carrito
     */
    @Override
    @Transactional
    public int crearCarrito(CrearCarritoDTO carrito) {
        // Verificar que no exista ya un carrito para este usuario
        if (existeCarritoParaUsuario(carrito.getIdUsuario())) {
            throw new RecursoDuplicadoException(
                    "El usuario con ID " + carrito.getIdUsuario() + " ya tiene un carrito asignado");
        }

        // Si no se proporciona fecha de creación, usar la fecha actual
        final LocalDateTime fechaCreacionFinal = carrito.getFechaCreacion() != null ? carrito.getFechaCreacion()
                : LocalDateTime.now();

        // Insertar el carrito y obtener el ID generado
        String sql = "INSERT INTO carrito (id_usuario, fecha_creacion) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, carrito.getIdUsuario());
            ps.setTimestamp(2, Timestamp.valueOf(fechaCreacionFinal));
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null) {
            throw new RuntimeException("No se pudo obtener el ID generado para el carrito");
        }
        return key.intValue();
    }

    /**
     * Cuenta el número total de carritos
     * @return Cantidad de carritos existentes
     */
    @Override
    public int contarCarritos() {
        String sql = "SELECT COUNT(id) FROM carrito";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }

    /**
     * Verifica si existe un carrito para un usuario específico
     * @param idUsuario ID del usuario a verificar
     * @return true si existe un carrito para ese usuario, false en caso contrario
     */
    @Override
    public boolean existeCarritoParaUsuario(int idUsuario) {
        String sql = "SELECT COUNT(id) FROM carrito WHERE id_usuario = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, idUsuario);
        return (count != null && count > 0);
    }

    /**
     * Elimina un carrito por su ID
     * @param id ID del carrito a eliminar
     * @throws RecursoNoEncontradoException si no existe un carrito con ese ID
     */
    @Override
    @Transactional
    public void eliminarCarrito(int id) {
        String sql = "DELETE FROM carrito WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        if (rowsAffected == 0) {
            throw new RecursoNoEncontradoException("No se encontró carrito con ID: " + id, sql, rowsAffected);
        }
    }
}
