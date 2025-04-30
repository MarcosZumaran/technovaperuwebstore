package com.technovaperu.technovaperuwebstore.model.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CrearProductoImagenPortadaDTO {
    private int idProducto;
    private String url;
}
