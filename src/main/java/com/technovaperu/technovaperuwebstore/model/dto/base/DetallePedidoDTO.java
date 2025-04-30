package com.technovaperu.technovaperuwebstore.model.dto.base;

import java.math.BigDecimal;

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
    private int idPedido;
    private int idProducto;
    private int cantidad;
    private BigDecimal precioUnitario;
}
