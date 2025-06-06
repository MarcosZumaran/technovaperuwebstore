package com.technovaperu.technovaperuwebstore.model.dto.create;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotNull(message = "El id de proveedor no puede estar vacío")
    @Positive(message = "El id de proveedor debe ser mayor que 0")
    @Schema(description = "ID del proveedor del producto", example = "1")
    private int idProveedor;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    @Schema(description = "Nombre del producto", example = "Smartphone XYZ")
    private String nombre;

    @NotBlank(message = "La descripcion no puede estar vacía")
    @Size(max = 255, message = "La descripcion no puede exceder 255 caracteres")
    @Schema(description = "Descripción del producto", example = "Este es un smartphone de alta calidad")
    private String descripcion;

    @NotNull(message = "El precio no puede estar vacío")
    @Positive(message = "El precio debe ser mayor que 0")
    @Schema(description = "Precio del producto", example = "100.99")
    private BigDecimal precio;

    @Positive(message = "El descuento debe ser mayor o igual que 0")
    @Schema(description = "Descuento del producto", example = "10")
    private BigDecimal descuento;

    @NotNull(message = "El stock no puede estar vacío")
    @Positive(message = "El stock debe ser mayor que 0")
    @Schema(description = "Cantidad disponible en inventario", example = "100")
    private int stock;
}
