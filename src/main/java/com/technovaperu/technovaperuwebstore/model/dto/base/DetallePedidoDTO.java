package com.technovaperu.technovaperuwebstore.model.dto.base;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetallePedidoDTO {
    private int id;

    @NotBlank(message = "El id de pedido no puede estar vacío")
    @Min(value = 1, message = "El id de pedido debe ser mayor que 0")
    private int idPedido;

    @NotBlank(message = "El id de producto no puede estar vacío")
    @Min(value = 1, message = "El id de producto debe ser mayor que 0")
    private int idProducto;

    @NotBlank(message = "La cantidad no puede ser nula")    
    @Min(value = 1, message = "La cantidad debe ser mayor que 0")
    private int cantidad;

    @NotBlank(message = "El precio unitario no puede ser nulo")
    @Min(value = 0, message = "El precio unitario debe ser mayor o igual que 0")
    private BigDecimal precioUnitario;
    
}
