package com.technovaperu.technovaperuwebstore.services;

import com.technovaperu.technovaperuwebstore.model.dto.base.ProductoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearProductoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarProductoDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductoService {
    List<ProductoDTO> listarProductos();
    Page<ProductoDTO> listarProductosPaginados(int pagina, int tamanoPagina); // Paginación añadida
    ProductoDTO obtenerProductoPorId(int id);
    ProductoDTO crearProducto(CrearProductoDTO dto);
    ProductoDTO actualizarProducto(int id, ActualizarProductoDTO dto);
    void eliminarProducto(int id);
    int contarProductosDisponibles();
}
