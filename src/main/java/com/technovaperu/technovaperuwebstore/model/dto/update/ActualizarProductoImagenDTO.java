package com.technovaperu.technovaperuwebstore.model.dto.update;

import com.technovaperu.technovaperuwebstore.model.ProductolImagenModel.Tipo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ActualizarProductoImagenDTO {

    @NotBlank(message = "El id de producto no puede estar vacío")
    @Min(value = 1, message = "El id de producto debe ser mayor que 0")
    private int idProducto;

    @NotBlank(message = "La url no puede estar vacía")
    @Size(max = 255, message = "La url no puede exceder los 255 caracteres")
    private String url;
    
    private Tipo tipo;

}
