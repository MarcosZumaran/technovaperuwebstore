package com.technovaperu.technovaperuwebstore.model.dto.base;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UsuarioDTO {
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String direccion;
    private String telefono;
    private String rol;
    private boolean activo;

    //Solo lectura
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaModificacion;
}
