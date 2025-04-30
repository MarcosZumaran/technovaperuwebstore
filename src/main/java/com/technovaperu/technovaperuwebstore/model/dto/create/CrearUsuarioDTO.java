package com.technovaperu.technovaperuwebstore.model.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CrearUsuarioDTO {
    private String nombre;
    private String apellido;
    private String email;
    private String direccion;
    private String telefono;
    private String contrasena;
    private String rol;
}
