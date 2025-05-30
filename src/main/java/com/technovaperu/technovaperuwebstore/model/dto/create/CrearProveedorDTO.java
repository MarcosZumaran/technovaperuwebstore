package com.technovaperu.technovaperuwebstore.model.dto.create;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class CrearProveedorDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    @Schema(description = "Nombre del proveedor", example = "Empresa XYZ")
    private String nombre;

    @NotBlank(message = "La direccion no puede estar vacía")
    @Size(max = 255, message = "La direccion no puede exceder 255 caracteres")
    @Schema(description = "Direccion del proveedor", example = "Calle 123, Ciudad de Los Santos, San Andreas")
    private String direccion;

    @NotBlank(message = "El telefono no puede estar vacío")
    @Size(max = 20, message = "El telefono no puede exceder 20 caracteres")
    @Schema(description = "Telefono del proveedor", example = "999888777")
    private String telefono;

    @NotBlank(message = "El email no puede estar vacío")
    @Size(max = 255, message = "El email no puede exceder 255 caracteres")
    @Schema(description = "Email del proveedor", example = "empresa@xyz.com")
    private String email;
    
    @NotBlank(message = "El pais no puede estar vacío")
    @Size(max = 100, message = "El pais no puede exceder 100 caracteres")
    @Schema(description = "Pais del proveedor", example = "EEUU")
    private String pais;
}
