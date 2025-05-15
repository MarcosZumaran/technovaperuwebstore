package com.technovaperu.technovaperuwebstore.model.dto.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.technovaperu.technovaperuwebstore.model.HistorialPedidoModel.EstadoHistorialPedido;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistorialPedidosDTO {

    @Schema(description = "ID único del historial del pedido", example = "1")
    private int id;

    @NotNull(message = "El id de pedido no puede estar vacío")
    @Positive(message = "El id de pedido debe ser mayor que 0")
    @Schema(description = "ID del pedido al que pertenece el historial", example = "1")
    private int idPedido;

    @Schema(description = "Estado del historial del pedido", example = "PENDIENTE")
    private EstadoHistorialPedido estado;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Fecha y hora en que se cambió el estado del pedido", example = "2023-01-01 10:00:00")
    private LocalDateTime fechaEstado;
}
