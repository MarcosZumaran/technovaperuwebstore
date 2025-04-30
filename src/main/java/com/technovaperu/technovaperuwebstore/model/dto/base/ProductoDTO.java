package com.technovaperu.technovaperuwebstore.model.dto.base;

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
public class ProductoDTO {
    private int id;
    private int idProveedor;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private BigDecimal descuento;
    private int stock;
    private boolean activo;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaActualizacion;
}
