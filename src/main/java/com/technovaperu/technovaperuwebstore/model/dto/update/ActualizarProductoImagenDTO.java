package com.technovaperu.technovaperuwebstore.model.dto.update;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ActualizarProductoImagenDTO {

    @NotNull(message = "El id de producto no puede estar vacío")
    @Positive(message = "El id de producto debe ser mayor que 0")
    @Schema(description = "ID del producto al que pertenece la imagen", example = "1")
    private int idProducto;

    @NotBlank(message = "La url no puede estar vacía")
    @Size(max = 255, message = "La url no puede exceder los 255 caracteres")
    @Schema(description = "URL de la imagen", example = "https://example.com/image.jpg")
    private String url;
    
    @Schema(description = "Tipo de la imagen", example = "PORTADA")
    private String tipo;

}
