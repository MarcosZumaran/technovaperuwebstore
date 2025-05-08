package com.technovaperu.technovaperuwebstore.model.dto.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActualizarProveedorDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombre;

    @NotBlank(message = "La direccion no puede estar vacía")
    @Size(max = 255, message = "La direccion no puede exceder 255 caracteres")
    private String direccion;

    @NotBlank(message = "El telefono no puede estar vacío")
    @Size(max = 20, message = "El telefono no puede exceder 20 caracteres")
    private String telefono;

    @NotBlank(message = "El email no puede estar vacío")
    @Size(max = 255, message = "El email no puede exceder 255 caracteres")
    private String email;

    @NotBlank(message = "El pais no puede estar vacío")
    @Size(max = 100, message = "El pais no puede exceder 100 caracteres")
    private String pais;
    
}
