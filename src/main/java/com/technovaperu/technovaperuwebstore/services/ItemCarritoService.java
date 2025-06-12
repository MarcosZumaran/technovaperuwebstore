package com.technovaperu.technovaperuwebstore.services;

import java.util.List;

import com.technovaperu.technovaperuwebstore.model.dto.base.ItemCarritoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearItemCarritoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarItemCarritoDTO;

public interface ItemCarritoService {
    List<ItemCarritoDTO> obtenerTodosLosItemsCarrito();
    List<ItemCarritoDTO> obtenerItemsCarrito(int pagina);
    List<ItemCarritoDTO> obtenerItemsCarritoPorCarrito(int pag, long idCarrito);
    List<ItemCarritoDTO> obtenerItemsCarritoPorCarritoSiEsActivo(int pagina, long idCarrito);
    List<ItemCarritoDTO> obtenerItemsCarritoPorCarritoSiNoEsActivo(int pagina, long idCarrito);
    ItemCarritoDTO obtenerItemCarritoPorId(long id);
    ItemCarritoDTO crearItemCarrito(CrearItemCarritoDTO carritoDTO);
    void actualizarItemCarrito(long id, ActualizarItemCarritoDTO carritoDTO);
    void borrarItemCarrito(long id);
    boolean existeItemCarrito(long id);
}
