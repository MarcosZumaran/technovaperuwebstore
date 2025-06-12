
package com.technovaperu.technovaperuwebstore.model.dto.base;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductoDTO {

    @Positive(message = "El id del producto no puede ser negativo")
    @Schema(description = "Identificador del producto", example = "1")
    private long id;

    @NotBlank(message = "El nombre del producto no puede estar en blanco")
    @Length(min = 3, max = 50, message = "El nombre del producto debe tener entre 3 y 50 caracteres")
    @Schema(description = "Nombre del producto", example = "Mesa")
    private String nombre;

    @NotBlank(message = "La descripción del producto no puede estar en blanco")
    @Length(min = 3, max = 255, message = "La descripción del producto debe tener entre 3 y 255 caracteres") 
    @Schema(description = "Descripción del producto", example = "Una mesa de madera")
    private String descripcion;

    @Positive(message = "El id de la categoria no puede ser negativo")
    @Schema(description = "Identificador de la categoria", example = "1")
    private long idCategoria;

    @NotBlank(message = "El material del producto no puede estar en blanco")
    @Length(min = 3, max = 20, message = "El estado del producto debe tener entre 3 y 20 caracteres")
    @Schema(description = "Estado del producto", example = "1")
    private String estado;

    @NotBlank(message = "El detalles del producto no puede estar en blanco")
    @Schema(description = "Detalles del producto", example = "Detalles del producto xD")
    private String detalles_producto;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Fecha de creación del producto", example = "2023-05-01T00:00:00")
    private LocalDateTime fechaRegistro;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Fecha de actualización del producto", example = "2023-05-01T00:00:00")
    private LocalDateTime fechaActualizacion;
    
}
