package com.technovaperu.technovaperuwebstore.model.dto.update;

import com.technovaperu.technovaperuwebstore.model.ComentarioModel.EstadoComentario;

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
public class ActualizarComentarioDTO {

    @NotBlank(message = "El comentario no puede estar vacío")
    private String texto;

    @NotBlank(message = "La calificacion no puede estar vacía")
    @Min(value = 0, message = "La calificacion debe ser mayor o igual que 0")
    private int calificacion;

    private EstadoComentario estado;
}
