package com.technovaperu.technovaperuwebstore.model.dto.base;

import com.technovaperu.technovaperuwebstore.model.HistorialPedidosModel.EstadoHistorialPedidos;
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
    private int idPedido;
    private EstadoHistorialPedidos estado;
    private LocalDateTime fechaEstado;
}
