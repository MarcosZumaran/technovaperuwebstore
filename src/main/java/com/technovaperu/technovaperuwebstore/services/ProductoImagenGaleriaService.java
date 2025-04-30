package com.technovaperu.technovaperuwebstore.services;

import com.technovaperu.technovaperuwebstore.model.dto.base.ProductoImagenGaleriaDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearProductoImagenGaleriaDTO;

import java.util.List;

public interface ProductoImagenGaleriaService {
    List<ProductoImagenGaleriaDTO> obtenerImagenesPorProducto(int idProducto);
    ProductoImagenGaleriaDTO agregarImagenGaleria(int idProducto, CrearProductoImagenGaleriaDTO dto);
    void eliminarImagenGaleria(int idProducto, int idImagen);
}
