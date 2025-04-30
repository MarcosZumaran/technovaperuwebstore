package com.technovaperu.technovaperuwebstore.model.dto.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaDTO {
    private int id;
    private String nombre;
    private String descripcion;
}
