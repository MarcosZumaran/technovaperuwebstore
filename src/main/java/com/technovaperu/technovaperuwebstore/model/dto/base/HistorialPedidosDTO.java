package com.technovaperu.technovaperuwebstore.model.dto.base;

import com.technovaperu.technovaperuwebstore.model.HistorialPedidosModel.EstadoHistorialPedidos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

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
    private int id;

    @NotBlank(message = "El id de pedido no puede estar vacío")
    @Min(value = 1, message = "El id de pedido debe ser mayor que 0")
    private int idPedido;

    private EstadoHistorialPedidos estado;
    
    private LocalDateTime fechaEstado;
}
