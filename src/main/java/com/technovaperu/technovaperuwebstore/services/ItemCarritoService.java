package com.technovaperu.technovaperuwebstore.services;

import java.util.List;

import com.technovaperu.technovaperuwebstore.model.dto.base.ItemCarritoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearItemCarritoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarItemCarritoDTO;

public interface ItemCarritoService {
    List<ItemCarritoDTO> obtenerItemsCarritoPorCarrito(int idCarrito, int pagina);
    ItemCarritoDTO obtenerItemCarritoPorId(int id);
    ItemCarritoDTO crearItemCarrito(CrearItemCarritoDTO item);
    void actualizarItemCarrito(int id, ActualizarItemCarritoDTO item);
    void eliminarItemCarrito(int id);
    int contarItemsCarrito();
    boolean existeItemCarritoPorId(int id);
}
