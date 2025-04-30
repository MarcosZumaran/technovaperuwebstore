package com.technovaperu.technovaperuwebstore.model.dto.base;

import com.technovaperu.technovaperuwebstore.model.PedidoModel.EstadoPedido;
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
public class PedidoDTO {
    private int id;
    private int idUsuario;
    private LocalDateTime fechaPedido;
    private EstadoPedido estado;
    private BigDecimal total;
    private String direccionEnvio;
}
