package com.technovaperu.technovaperuwebstore.model.dto.create;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
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
public class CrearProductoDTO {

    @NotBlank(message = "El id de proveedor no puede estar vacío")
    @Min(value = 1, message = "El id de proveedor debe ser mayor que 0")
    private int idProveedor;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombre;

    @NotBlank(message = "La descripcion no puede estar vacía")
    @Size(max = 255, message = "La descripcion no puede exceder 255 caracteres")
    private String descripcion;

    @NotBlank(message = "El precio no puede estar vacío")
    @Min(value = 0, message = "El precio debe ser mayor o igual que 0")
    private BigDecimal precio;

    @NotBlank(message = "El descuento no puede estar vacío")
    @Min(value = 0, message = "El descuento debe ser mayor o igual que 0")
    private BigDecimal descuento;

    @NotBlank(message = "El stock no puede estar vacío")
    @Min(value = 0, message = "El stock debe ser mayor o igual que 0")
    private int stock;
}
