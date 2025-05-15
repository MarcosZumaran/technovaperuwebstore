package com.technovaperu.technovaperuwebstore.model.dto.base;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.technovaperu.technovaperuwebstore.model.UsuarioModel.Rol;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "DTO para operaciones con usuarios")
public class UsuarioDTO {
    
    @Schema(description = "ID único del usuario", example = "1")
    private int id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    @Schema(description = "Nombre del usuario", example = "Juan")
    private String nombre;
    
    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 100, message = "El apellido no puede exceder 100 caracteres")
    @Schema(description = "Apellido del usuario", example = "Pérez")
    private String apellido;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El formato del email no es válido")
    @Size(max = 255, message = "El email no puede exceder 255 caracteres")
    @Schema(description = "Correo electrónico del usuario", example = "juan.perez@ejemplo.com")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(max = 255, message = "La contraseña no puede exceder 255 caracteres")
    @Schema(description = "Contraseña del usuario", example = "123456")
    private String password;

    @NotBlank(message = "La dirección no puede estar vacía")
    @Size(max = 255, message = "La dirección no puede exceder 255 caracteres")
    @Schema(description = "Dirección del usuario", example = "Av. Principal 123")
    private String direccion;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(regexp = "^[0-9]{9,20}$", message = "El teléfono debe tener entre 9 y 20 dígitos")
    @Size(max = 20, message = "El teléfono no puede exceder 20 caracteres")
    @Schema(description = "Teléfono del usuario", example = "999888777")
    private String telefono;

    @Schema(description = "Rol del usuario en el sistema", example = "CLIENTE")
    private Rol rol;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Fecha de registro del usuario", example = "2023-01-01 10:00:00")
    private LocalDateTime fechaRegistro;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Fecha de última modificación", example = "2023-01-15 15:30:00")
    private LocalDateTime fechaModificacion;
}