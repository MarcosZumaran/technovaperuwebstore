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
import com.technovaperu.technovaperuwebstore.model.dto.base.UsuarioDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearUsuarioDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarUsuarioDTO;
import com.technovaperu.technovaperuwebstore.services.UsuarioService;

/**
 * Implementación de la interfaz {@link UsuarioService} que utiliza JDBC para acceder a la base de datos.
 * 
 * @author Marcos Zumaran
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UsuarioServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<UsuarioDTO> usuarioRowMapper = (rs, rowNum) -> UsuarioDTO.builder()
            .id(rs.getInt("id"))
            .nombre(rs.getString("nombre"))
            .apellido(rs.getString("apellido"))
            .email(rs.getString("email"))
            .password(rs.getString("password"))
            .direccion(rs.getString("direccion"))
            .telefono(rs.getString("telefono"))
            .build();
    

    @Override 
    @Transactional(readOnly = true)
    public List<UsuarioDTO> obtenerTodosLosUsuarios(int pagina){
        if (pagina <= 0) pagina = 1;
        int offset = (pagina - 1) * 10;
        int limit = 10;
        String sql = "SELECT * FROM usuario ORDER BY id DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, usuarioRowMapper, limit, offset);
    }

    @Override 
    @Transactional(readOnly = true)
    public UsuarioDTO obtenerUsuarioPorId(int id){
        try {
            String sql = "SELECT * FROM usuario WHERE id = ?";
            return jdbcTemplate.queryForObject(sql, usuarioRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new RecursoNoEncontradoException("Usuario", "id", id);
        }
    }

    @Override 
    @Transactional
    public UsuarioDTO crearUsuario(CrearUsuarioDTO usuario){
        String sql = "INSERT INTO usuario (nombre, apellido, email, direccion, telefono, contrasena, rol) VALUES(?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getDireccion());
            ps.setString(5, usuario.getTelefono());
            ps.setString(6, usuario.getPassword());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null) {
            throw new RuntimeException("No se pudo obtener el ID generado para el usuario");
        }

        Integer id = Objects.requireNonNull(keyHolder.getKey()).intValue();

        return UsuarioDTO.builder()
                .id(id)
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .email(usuario.getEmail())
                .password(usuario.getPassword())
                .direccion(usuario.getDireccion())
                .telefono(usuario.getTelefono())
                .fechaRegistro(LocalDateTime.now())
                .fechaModificacion(LocalDateTime.now())
                .build();
    }
    
    @Override 
    @Transactional
    public void actualizarUsuario(int id, ActualizarUsuarioDTO usuario){
        if (!existeUsuarioPorId(id)) {
            throw new RecursoNoEncontradoException("Usuario", "id", id);
        }
        String sql = "UPDATE usuario SET nombre = ?, apellido = ?, email = ?, direccion = ?, telefono = ?, contrasena = ?, rol = ? WHERE id = ?";
        jdbcTemplate.update(sql, usuario.getNombre(), usuario.getApellido(), usuario.getEmail(), usuario.getDireccion(), usuario.getTelefono(), usuario.getContrasena(), id);
    }

    @Override 
    @Transactional
    public void eliminarUsuario(int id){
        if (!existeUsuarioPorId(id)) {
            throw new RecursoNoEncontradoException("Usuario", "id", id);
        }
        String sql = "DELETE FROM usuario WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override 
    @Transactional(readOnly = true)
    public int contarUsuarios(){
        String sql = "SELECT COUNT(id) FROM usuario";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return (count != null) ? count : 0;
    }
    @Override
    @Transactional(readOnly = true)
    public boolean existeUsuarioPorId(int id) {
        String sql = "SELECT COUNT(id) FROM usuario WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }
}

