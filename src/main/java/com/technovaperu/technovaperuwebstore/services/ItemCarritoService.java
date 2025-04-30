package com.technovaperu.technovaperuwebstore.services;

import com.technovaperu.technovaperuwebstore.model.dto.base.ItemCarritoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearItemCarritoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarItemCarritoDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ItemCarritoService {
    List<ItemCarritoDTO> obtenerItemsPorCarrito(int idCarrito);
    Page<ItemCarritoDTO> obtenerItemsPorCarritoPaginados(int idCarrito, int pagina, int tamanoPagina); // Paginación añadida
    ItemCarritoDTO crearItemCarrito(CrearItemCarritoDTO dto);
    void eliminarItemCarrito(int idCarrito, int idItem);
    void actualizarItemCarrito(int idCarrito, int idItem, ActualizarItemCarritoDTO dto);
}
