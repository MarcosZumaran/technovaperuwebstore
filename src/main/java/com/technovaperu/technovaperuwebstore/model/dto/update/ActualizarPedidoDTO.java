package com.technovaperu.technovaperuwebstore.model.dto.update;

import com.technovaperu.technovaperuwebstore.model.PedidoModel.EstadoPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActualizarPedidoDTO {
    private EstadoPedido estado;
    private String direccionEnvio;
}
