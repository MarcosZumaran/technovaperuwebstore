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
public class CrearHistorialPedidosDTO {

    @NotNull(message = "El id de pedido no puede estar vacío")
    @Positive(message = "El id de pedido debe ser mayor que 0")
    @Schema(description = "ID del pedido al que pertenece el historial", example = "1")
    private int idPedido;

    @Schema(description = "Estado del historial del pedido", example = "PENDIENTE")
    private String estado;

}
