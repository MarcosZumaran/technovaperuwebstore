package com.technovaperu.technovaperuwebstore.model.dto.update;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActualizarProductoDTO {
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private BigDecimal descuento;
    private int stock;
    private Boolean activo;
}
