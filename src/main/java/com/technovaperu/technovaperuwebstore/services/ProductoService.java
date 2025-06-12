package com.technovaperu.technovaperuwebstore.services;

import java.util.List;

import com.technovaperu.technovaperuwebstore.model.dto.base.ProductoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearProductoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarProductoDTO;

public interface ProductoService {

    List<ProductoDTO> obtenerTodosLosProductos();
    List<ProductoDTO> obtenerProductos(int pagina);
    List<ProductoDTO> obtenerTodosLosProductosPorCategoria(long idCategoria);
    List<ProductoDTO> obtenerTodosLosProductosPorEstado(String estado);
    List<ProductoDTO> obtenerTodosLosProductosPorNombre(String nombre);
    List<ProductoDTO> obtenerTodosLosProductosPorMarca(String marca);
    List<ProductoDTO> obetnerTodosLosProductosPorNombreParcial(String nombre);
    List<ProductoDTO> obtenerProductosPorCategoria(int pagina, long idCategoria);
    List<ProductoDTO> obtenerProductosPorEstado(int pagina, String estado);
    List<ProductoDTO> obtenerProductosPorNombre(int pagina, String nombre);
    List<ProductoDTO> obtenerProductosPorMarca(int pagina, String marca);
    List<ProductoDTO> obtenerProductosPorNombreParcial(int pagina, String nombre);
    ProductoDTO obtenerProductoPorId(long id);
    ProductoDTO crearProducto(CrearProductoDTO productoDTO);
    void actualizarProducto(long id, ActualizarProductoDTO productoDTO);
    void borrarProducto(long id);
    boolean existeProducto(long id);
}