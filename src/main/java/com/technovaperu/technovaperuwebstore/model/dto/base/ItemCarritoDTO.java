package com.technovaperu.technovaperuwebstore.model.dto.base;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemCarritoDTO {
    private int id;
    private int idCarrito;
    private int idProducto;
    private int cantidad;
    private LocalDateTime fechaAgregado;
}
