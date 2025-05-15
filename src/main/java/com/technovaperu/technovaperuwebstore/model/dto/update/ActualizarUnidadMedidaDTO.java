package com.technovaperu.technovaperuwebstore.model.dto.update;

import java.math.BigDecimal;

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
@NoArgsConstructor
@Data
@Builder
public class ActualizarUnidadMedidaDTO {

    @NotNull(message = "El id de producto no puede estar vacío")
    @Schema(description = "ID del producto al que pertenece la unidad de medida", example = "1")
    @Positive(message = "El id de producto debe ser mayor que 0")
    private int idProducto;

    @NotBlank(message = "La unidad de medida no puede estar vacía")
    @Size(max = 6, message = "La unidad de medida no puede exceder los 6 caracteres")
    @Schema(description = "Nombre de la unidad de medida", example = "Unidad")
    private String unidadMedida;

    @NotNull(message = "El precio no puede ser nulo")
    @Positive(message = "El precio debe ser mayor que 0")
    @Schema(description = "Precio de la unidad de medida", example = "10.99")
    private BigDecimal precio;
    
}
