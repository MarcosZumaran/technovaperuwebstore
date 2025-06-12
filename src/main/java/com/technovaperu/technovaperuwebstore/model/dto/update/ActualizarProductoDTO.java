package com.technovaperu.technovaperuwebstore.model.dto.update;


import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActualizarProductoDTO {

    @Length(min = 3, max = 50, message = "El nombre del producto debe tener entre 3 y 50 caracteres")
    @Schema(description = "Nombre del producto", example = "Mesa")
    private String nombre;

    @Length(min = 3, max = 255, message = "La descripción del producto debe tener entre 3 y 255 caracteres") 
    @Schema(description = "Descripción del producto", example = "Una mesa de madera")
    private String descripcion;

    @Positive(message = "El id de la categoria no puede ser negativo")
    @Schema(description = "Identificador de la categoria", example = "1")
    private long idCategoria;

    @Length(min = 3, max = 20, message = "El estado del producto debe tener entre 3 y 20 caracteres")
    @Schema(description = "Estado del producto", example = "1")
    private String estado;

    @Schema(description = "Detalles del producto", example = "Detalles del producto xD")
    private String detalles_producto;

}
