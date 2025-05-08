
package com.technovaperu.technovaperuwebstore.model.dto.base;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
@Schema(description = "DTO para operaciones con productos")
public class ProductoDTO {
    
    @Schema(description = "ID único del producto", example = "1")
    private int id;

    @NotNull(message = "El id de proveedor no puede ser nulo")
    @Positive(message = "El id de proveedor debe ser mayor que 0")
    @Schema(description = "ID del proveedor del producto", example = "1")
    private int idProveedor;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres") 
    @Schema(description = "Nombre del producto", example = "Smartphone XYZ")
    private String nombre;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 255, message = "La descripción no puede exceder 255 caracteres") 
    @Schema(description = "Descripción del producto", example = "Smartphone de última generación con 8GB RAM y 128GB almacenamiento")
    private String descripcion;

    @NotNull(message = "El precio no puede ser nulo")
    @PositiveOrZero(message = "El precio debe ser mayor o igual que 0")
    @Schema(description = "Precio del producto", example = "599.99")
    private BigDecimal precio;

    @NotNull(message = "El descuento no puede ser nulo")
    @PositiveOrZero(message = "El descuento debe ser mayor o igual que 0")
    @Schema(description = "Descuento aplicado al producto", example = "50.00")
    private BigDecimal descuento;

    @NotNull(message = "El stock no puede ser nulo")
    @PositiveOrZero(message = "El stock debe ser mayor o igual que 0")
    @Schema(description = "Cantidad disponible en inventario", example = "100")
    private int stock;

    @Schema(description = "Indica si el producto está activo", example = "true")
    private boolean activo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Fecha de registro del producto", example = "2023-01-01 10:00:00")
    private LocalDateTime fechaRegistro;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Fecha de última actualización", example = "2023-01-15 15:30:00")
    private LocalDateTime fechaActualizacion;
}