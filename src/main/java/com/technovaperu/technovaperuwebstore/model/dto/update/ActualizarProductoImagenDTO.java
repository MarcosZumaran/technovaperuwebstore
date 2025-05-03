package com.technovaperu.technovaperuwebstore.model.dto.update;

import com.technovaperu.technovaperuwebstore.model.ProductolImagenModel.Tipo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ActualizarProductoImagenDTO {

    private int idProducto;
    private String url;
    private Tipo tipo;

}
