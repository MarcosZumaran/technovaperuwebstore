package com.technovaperu.technovaperuwebstore.model.dto.create;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
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
    @Schema(description = "Nombre del usuario", example = "Juan")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 100, message = "El apellido no puede exceder 100 caracteres")
    @Schema(description = "Apellido del usuario", example = "Pérez")
    private String apellido;

    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Size(max = 255, message = "El correo electrónico no puede exceder 255 caracteres")
    @Email(message = "El formato del email no es válido")
    @Schema(description = "Correo electrónico del usuario", example = "juan.perez@ejemplo.com")
    private String email;

    @NotBlank(message = "La direccion no puede estar vacía")
    @Size(max = 255, message = "La direccion no puede exceder 255 caracteres")
    @Schema(description = "Dirección del usuario", example = "Av. Principal 123")
    private String direccion;

    @NotBlank(message = "El telefono no puede estar vacío")
    @Size(max = 20, message = "El telefono no puede exceder 20 caracteres")
    @Schema(description = "Teléfono del usuario", example = "999888777")
    private String telefono;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8,  max = 255, message = "La contraseña debe tener minimo 8 caracteres y no puede exceder 255")
    @Schema(description = "Contraseña del usuario", example = "12345678")
    private String password;
}
