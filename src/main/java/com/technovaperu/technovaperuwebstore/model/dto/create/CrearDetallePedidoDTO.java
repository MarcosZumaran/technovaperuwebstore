package com.technovaperu.technovaperuwebstore.model.dto.create;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CrearDetallePedidoDTO {
    private int idPedido;
    private int idProducto;
    private int cantidad;
    private BigDecimal precioUnitario;
}
