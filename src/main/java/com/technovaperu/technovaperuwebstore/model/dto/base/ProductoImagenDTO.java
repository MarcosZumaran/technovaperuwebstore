package com.technovaperu.technovaperuwebstore.model.dto.base;

import com.technovaperu.technovaperuwebstore.model.ProductolImagenModel.Tipo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoImagenDTO {
    private int id;
    private int idProducto;
    private String url;
    private Tipo tipo;
}
