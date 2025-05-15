package com.technovaperu.technovaperuwebstore.services;

import java.util.List;

import com.technovaperu.technovaperuwebstore.model.dto.base.ProductoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearProductoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarProductoDTO;

public interface ProductoService {
    List<ProductoDTO> obtenerTodosLosProductos(int pagina);
    List<ProductoDTO> obtenerProductosPorCategoria(int idCategoria, int pagina);
    ProductoDTO obtenerProductoPorId(int id);
    ProductoDTO crearProducto(CrearProductoDTO producto);
    void actualizarProducto(int id, ActualizarProductoDTO producto);
    void eliminarProducto(int id);
    int contarProductosPorCategoria(int idCategoria);
    int contarProductos();
    boolean existeProductoPorId(int id);
}
