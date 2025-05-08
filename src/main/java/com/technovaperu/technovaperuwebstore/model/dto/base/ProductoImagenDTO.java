package com.technovaperu.technovaperuwebstore.model.dto.base;

import com.technovaperu.technovaperuwebstore.model.ProductolImagenModel.Tipo;

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
public class ProductoImagenDTO {
    private int id;

    @NotBlank(message = "El id de producto no puede estar vacío")
    @Min(value = 1, message = "El id de producto debe ser mayor que 0")
    private int idProducto;
    
    @NotBlank(message = "El url no puede estar vacío")
    private String url;
    
    private Tipo tipo;
}
