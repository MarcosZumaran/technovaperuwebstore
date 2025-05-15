package com.technovaperu.technovaperuwebstore.model.dto.update;

import com.technovaperu.technovaperuwebstore.model.ComentarioModel.EstadoComentario;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "El comentario no puede estar vacío")
    @Schema(description = "Texto del comentario", example = "Esto es un comentario")
    private String texto;

    @NotNull(message = "La calificacion no puede estar vacía")
    @Positive(message = "La calificacion debe ser mayor o igual que 0")
    @Schema(description = "Calificacion del comentario", example = "5")
    private int calificacion;

    @Schema(description = "Estado del comentario", example = "OCULTO")
    private EstadoComentario estado;
}
