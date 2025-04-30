package com.technovaperu.technovaperuwebstore.model.dto.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ActualizarUsuarioDTO {
    private String nombre;
    private String apellido;
    private String email;
    private String direccion;
    private String telefono;
    private String contrasena; // puede ser null si no se cambia
    private Boolean activo;
    private String rol;
}
