package com.technovaperu.technovaperuwebstore.model.dto.base;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoImagenDTO {

    @Schema(description = "ID único de la imagen", example = "1")
    private int id;

    @NotNull(message = "El id de producto no puede estar vacío")
    @Positive(message = "El id de producto debe ser mayor que 0")
    @Schema(description = "ID del producto al que pertenece la imagen", example = "1")
    private int idProducto;
    
    @NotBlank(message = "El url no puede estar vacío")
    @Schema(description = "URL de la imagen", example = "https://example.com/imagen.jpg")
    private String url;
    
    @Schema(description = "Tipo de la imagen", example = "PORTADA")
    private String tipo;
}
