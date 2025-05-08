package com.technovaperu.technovaperuwebstore.model.dto.update;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActualizarProductoDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no debe exceder los 100 caracteres")
    private String nombre;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 255, message = "La descripción no debe exceder los 255 caracteres")
    private String descripcion;

    @NotBlank(message = "El precio no puede estar vacío")
    private BigDecimal precio;

    @NotBlank(message = "El descuento no puede estar vacío")
    private BigDecimal descuento;

    @NotBlank(message = "El stock no puede estar vacío")
    private int stock;
    
    private Boolean activo;
}
