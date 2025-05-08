package com.technovaperu.technovaperuwebstore.model.dto.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.technovaperu.technovaperuwebstore.model.PedidoModel.EstadoPedido;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "DTO para operaciones con pedidos")
public class PedidoDTO {
    
    @Schema(description = "ID único del pedido", example = "1")
    private int id;

    @NotNull(message = "El id de usuario no puede ser nulo")
    @Positive(message = "El id de usuario debe ser mayor que 0")
    @Schema(description = "ID del usuario que realiza el pedido", example = "1")
    private int idUsuario;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Fecha y hora del pedido", example = "2023-01-01 10:00:00")
    private LocalDateTime fechaPedido;

    @Schema(description = "Estado actual del pedido", example = "PENDIENTE")
    private EstadoPedido estado;

    @Schema(description = "Monto total del pedido", example = "1299.99")
    private BigDecimal total;

    @NotBlank(message = "La dirección de envío no puede estar vacía")
    @Size(max = 255, message = "La dirección de envío no puede exceder 255 caracteres")
    @Schema(description = "Dirección de entrega del pedido", example = "Av. Principal 123, Lima")
    private String direccionEnvio;
}