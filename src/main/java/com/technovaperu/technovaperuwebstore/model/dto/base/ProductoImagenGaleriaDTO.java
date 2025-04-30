package com.technovaperu.technovaperuwebstore.model.dto.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoImagenGaleriaDTO {
    private int id;
    private int idProducto;
    private String url;
}
