package com.technovaperu.technovaperuwebstore.model.dto.update;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ActualizarCategoriaDTO {

    @Length(min = 3, max = 50, message = "El nombre de la categoria debe tener entre 3 y 50 caracteres")
    @Schema(description = "Nombre de la categoria")
    private String nombre;

    @Length(min = 3, max = 160, message = "La descripción de la categoria debe tener entre 3 y 160 caracteres")
    @Schema(description = "Descripción de la categoria")
    private String descripcion;
    
}
