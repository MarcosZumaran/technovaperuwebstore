package com.technovaperu.technovaperuwebstore.services;

import com.technovaperu.technovaperuwebstore.model.dto.base.ProductoImagenPortadaDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearProductoImagenPortadaDTO;

public interface ProductoImagenPortadaService {
    ProductoImagenPortadaDTO obtenerImagenPortadaPorProducto(int idProducto);
    ProductoImagenPortadaDTO agregarImagenPortada(int idProducto, CrearProductoImagenPortadaDTO dto);
    void eliminarImagenPortada(int idProducto);
}
