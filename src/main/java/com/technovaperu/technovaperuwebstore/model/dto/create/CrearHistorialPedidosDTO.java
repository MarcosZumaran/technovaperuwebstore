package com.technovaperu.technovaperuwebstore.model.dto.create;

import com.technovaperu.technovaperuwebstore.model.HistorialPedidosModel.EstadoHistorialPedidos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CrearHistorialPedidosDTO {
    private int idPedido;
    private EstadoHistorialPedidos estado;
}
