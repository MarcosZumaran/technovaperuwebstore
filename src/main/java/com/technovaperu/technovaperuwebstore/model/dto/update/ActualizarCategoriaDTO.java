package com.technovaperu.technovaperuwebstore.model.dto.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActualizarCategoriaDTO {
    private String nombre;
    private String descripcion;
}
