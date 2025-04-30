package com.technovaperu.technovaperuwebstore.services;

import com.technovaperu.technovaperuwebstore.model.dto.base.UsuarioDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearUsuarioDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarUsuarioDTO;
import org.springframework.data.domain.Page;

public interface UsuarioService {
    UsuarioDTO obtenerUsuarioPorId(int id);
    UsuarioDTO crearUsuario(CrearUsuarioDTO dto);
    UsuarioDTO actualizarUsuario(int id, ActualizarUsuarioDTO dto);
    void eliminarUsuario(int id);
    UsuarioDTO obtenerUsuarioPorEmail(String email);
    Page<UsuarioDTO> listarUsuariosPaginados(int pagina, int tamanoPagina); // Paginación añadida
}
