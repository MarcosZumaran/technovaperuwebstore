package com.technovaperu.technovaperuwebstore.model.dto.create;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class CrearItemCarritoDTO {

    @NotNull(message = "El id de carrito no puede estar vacío")
    @Positive(message = "El id de carrito debe ser mayor que 0")
    @Schema(description = "ID del carrito al que pertenece el item", example = "1")
    private int idCarrito;

    @NotNull(message = "El id de producto no puede estar vacío")
    @Positive(message = "El id de producto debe ser mayor que 0")
    @Schema(description = "ID del producto al que pertenece el item", example = "1")
    private int idProducto;

    @NotNull(message = "El id de unidad de medida no puede estar vacío")
    @Positive(message = "El id de unidad de medida debe ser mayor que 0")
    @Schema(description = "ID de la unidad de medida del producto", example = "1")
    private int cantidad;
}
