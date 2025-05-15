package com.technovaperu.technovaperuwebstore.services;

import java.util.List;

import com.technovaperu.technovaperuwebstore.model.dto.base.ProductoImagenDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearProductoImagenDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarProductoImagenDTO;

public interface ProductoImagenService {

    List<ProductoImagenDTO> obtenerImagenesPorProducto(int id);
    ProductoImagenDTO obtenerImagenPorId(int id);
    ProductoImagenDTO crearImagen(CrearProductoImagenDTO imagen);
    void actualizarImagen(int id, ActualizarProductoImagenDTO imagen);
    void eliminarImagen(int id);
    int contarImagenesPorProducto(int id);
    int contarImagenes();
    boolean existeImagenPorId(int id);
}
