package com.technovaperu.technovaperuwebstore.model.dto.base;

import java.time.LocalDateTime;

import com.technovaperu.technovaperuwebstore.model.ComentarioModel.EstadoComentario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComentarioDTO {
    private int id;
    private int idUsuario;
    private int idProducto;
    private String texto;
    private int calificacion;
    private EstadoComentario estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;
}


