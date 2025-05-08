package com.technovaperu.technovaperuwebstore.model.dto.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ActualizarUsuarioDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 100, message = "El apellido no puede exceder 100 caracteres")
    private String apellido;

    @NotBlank(message = "El email no puede estar vacío")
    @Size(max = 255, message = "El email no puede exceder 255 caracteres")
    private String email;

    @NotBlank(message = "La direccion no puede estar vacía")
    @Size(max = 255, message = "La direccion no puede exceder 255 caracteres")
    private String direccion;

    @NotBlank(message = "El telefono no puede estar vacío")
    @Size(max = 20, message = "El telefono no puede exceder 20 caracteres")
    private String telefono;

    private String contrasena; // puede ser null si no se cambia

    private Boolean activo;
    
    private String rol;
}
