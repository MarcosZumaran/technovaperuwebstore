package com.technovaperu.technovaperuwebstore.services;

import java.util.List;

import com.technovaperu.technovaperuwebstore.model.dto.base.UsuarioDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearUsuarioDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarUsuarioDTO;

public interface UsuarioService {
    List<UsuarioDTO> obtenerTodosLosUsuarios(int pagina);
    UsuarioDTO obtenerUsuarioPorId(int id);
    UsuarioDTO crearUsuario(CrearUsuarioDTO usuario);
    void actualizarUsuario(int id, ActualizarUsuarioDTO usuario);
    void eliminarUsuario(int id);
    int contarUsuarios();
    boolean existeUsuarioPorId(int id);
}
