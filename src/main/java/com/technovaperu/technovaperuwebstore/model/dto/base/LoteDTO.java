package com.technovaperu.technovaperuwebstore.model.dto.base;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoteDTO {

    @Schema(description = "ID único del lote", example = "1")
    int id;

    @NotNull(message = "El id de producto no puede estar vacío")
    @Positive(message = "El id de producto debe ser mayor que 0")
    @Schema(description = "ID del producto al que pertenece el lote", example = "1")
    int idProducto;

    @NotBlank(message = "La unidad de medida no puede estar vacía")
    @Schema(description = "Unidad de medida del lote", example = "Pieza")
    @Size(max = 6, message = "La unidad de medida no puede exceder los 6 caracteres")
    String unidadMedida;

    @NotNull(message = "El costo no puede ser nulo")
    @Positive(message = "El costo debe ser mayor que 0")
    @Schema(description = "Costo del lote", example = "10.99")
    BigDecimal costo;

    @NotNull(message = "El precio no puede ser nulo")
    @Positive(message = "El precio debe ser mayor que 0")
    @Schema(description = "Precio del lote", example = "15.99")
    BigDecimal precio;

    @NotNull(message = "El cantidad no puede ser nula")
    @Positive(message = "El cantidad debe ser mayor que 0")
    @Schema(description = "Cantidad del lote", example = "100")
    BigDecimal cantidad;
    
}
