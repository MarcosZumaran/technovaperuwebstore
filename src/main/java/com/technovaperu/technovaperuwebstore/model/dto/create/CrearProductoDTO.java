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
public class CrearProductoDTO {
    private int idProveedor;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private BigDecimal descuento;
    private int stock;
}
