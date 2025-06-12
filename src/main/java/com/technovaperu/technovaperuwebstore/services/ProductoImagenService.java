package com.technovaperu.technovaperuwebstore.services;

import java.util.List;

import com.technovaperu.technovaperuwebstore.model.dto.base.ProductoImagenDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearProductoImagenDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarProductoImagenDTO;

public interface ProductoImagenService {

    List<ProductoImagenDTO> obtenerImagenesPorProducto(long idProducto);
    List<ProductoImagenDTO> obtenerImagenesPorProductoYTipo(long idProducto, String tipo);
    List<ProductoImagenDTO> obtenerTodasLasImagenes();
    List<ProductoImagenDTO> obtenerImagenes(int pagina);
    List<ProductoImagenDTO> obtenerTodasLasImagenesPorTipo(String tipo);
    List<ProductoImagenDTO> obtenerImagenesPorTipo(int pagina, String tipo);
    ProductoImagenDTO obtenerImagenPorId(long id);
    ProductoImagenDTO crearImagen(CrearProductoImagenDTO imagenDTO);
    void actualizarImagen(long id, ActualizarProductoImagenDTO imagenDTO);
    void borrarImagen(long id);
    boolean existeImagen(long id);
    long contarImagenes();
    long contarImagenesPorTipo(String tipo);
    long contarImagenesPorProducto(long idProducto);    

}