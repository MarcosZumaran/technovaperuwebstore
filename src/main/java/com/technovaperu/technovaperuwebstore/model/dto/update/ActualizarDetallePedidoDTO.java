package com.technovaperu.technovaperuwebstore.model.dto.update;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActualizarDetallePedidoDTO {
    @NotNull(message = "El id de pedido no puede estar vacío")
    @Positive(message = "El id de pedido debe ser mayor que 0")
    @Schema(description = "ID del pedido al que pertenece el detalle", example = "1")
    private int idPedido;

    @NotNull(message = "El id de producto no puede estar vacío")
    @Positive(message = "El id de producto debe ser mayor que 0")
    @Schema(description = "ID del producto al que pertenece el detalle", example = "1")
    private int idProducto;

    @NotNull(message = "El id de unidad de medida no puede estar vacío")
    @Positive(message = "El id de unidad de medida debe ser mayor que 0")
    @Schema(description = "ID de la unidad de medida al que pertenece el detalle", example = "1")
    private int cantidad;

    @NotNull(message = "El precio unitario no puede ser nulo")
    @Positive(message = "El precio unitario debe ser mayor o igual que 0")
    @Schema(description = "Precio unitario del detalle", example = "3.99")
    private BigDecimal precioUnitario;
}
