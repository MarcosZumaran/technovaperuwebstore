package com.technovaperu.technovaperuwebstore.services;

import java.util.List;

import com.technovaperu.technovaperuwebstore.model.dto.base.UsuarioDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearUsuarioDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarUsuarioDTO;

public interface UsuarioService {

    List<UsuarioDTO> obtenerUsuarios(int pagina);
    List<UsuarioDTO> obtenerUsuariosPorNombre(int pagina, String nombre);
    List<UsuarioDTO> obtenerUsuariosPorRol(int pagina, String rol);
    List<UsuarioDTO> obtenerTodosLosUsuarios();
    List<UsuarioDTO> obtenerTodosLosUsuarioPorNombre(String nombre);
    List<UsuarioDTO> obtenerTodosLosUsuariosPorRol(String rol);
    UsuarioDTO obtenerUsuarioPorId(long id);
    UsuarioDTO obtenerusuarioPorCredenciales(String correo, String clave);
    UsuarioDTO crearUsuario(CrearUsuarioDTO usuarioDTO);
    void actualizarUsuario(long id, ActualizarUsuarioDTO usuarioDTO);
    void cambiarClave(long id, String nuevaClave);
    void borrarUsuario(long id);
    boolean existeUsuario(long id);
    
}