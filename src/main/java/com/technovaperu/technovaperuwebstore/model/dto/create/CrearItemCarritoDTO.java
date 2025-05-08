package com.technovaperu.technovaperuwebstore.model.dto.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CrearItemCarritoDTO {

    @NotBlank(message = "El id de carrito no puede estar vacío")
    @Min(value = 1, message = "El id de carrito debe ser mayor que 0")
    private int idCarrito;

    @NotBlank(message = "El id de producto no puede estar vacío")
    @Min(value = 1, message = "El id de producto debe ser mayor que 0")
    private int idProducto;

    @NotBlank(message = "La cantidad no puede estar vacía")
    @Min(value = 1, message = "La cantidad debe ser mayor que 0")
    private int cantidad;
}
