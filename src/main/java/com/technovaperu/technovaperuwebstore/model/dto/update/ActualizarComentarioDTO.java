package com.technovaperu.technovaperuwebstore.model.dto.update;

import com.technovaperu.technovaperuwebstore.model.ComentarioModel.EstadoComentario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActualizarComentarioDTO {
    private String texto;
    private int calificacion;
    private EstadoComentario estado;
}
