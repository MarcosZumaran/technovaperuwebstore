package com.technovaperu.technovaperuwebstore.model.dto.create;

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
public class CrearUsuarioDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 100, message = "El apellido no puede exceder 100 caracteres")
    private String apellido;

    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Size(max = 255, message = "El correo electrónico no puede exceder 255 caracteres")
    private String email;

    @NotBlank(message = "La direccion no puede estar vacía")
    @Size(max = 255, message = "La direccion no puede exceder 255 caracteres")
    private String direccion;

    @NotBlank(message = "El telefono no puede estar vacío")
    @Size(max = 20, message = "El telefono no puede exceder 20 caracteres")
    private String telefono;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8,  max = 255, message = "La contraseña debe tener minimo 8 caracteres y no puede exceder 255")
    private String contrasena;

    private String rol;
}
