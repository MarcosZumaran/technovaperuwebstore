package com.technovaperu.technovaperuwebstore.model.dto.update;


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
public class ActualizarComentarioDTO {

    @Schema(description = "Texto del comentario", example = "Esto es un comentario")
    private String texto;

    @Positive(message = "La calificacion debe ser mayor o igual que 0")
    @Schema(description = "Calificacion del comentario", example = "5")
    private Integer calificacion;
}
