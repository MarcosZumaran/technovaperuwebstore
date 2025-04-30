package com.technovaperu.technovaperuwebstore.services;

import com.technovaperu.technovaperuwebstore.model.dto.base.CarritoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.base.ItemCarritoDTO;

import java.util.List;

public interface CarritoService {
    CarritoDTO crearCarrito();
    CarritoDTO obtenerCarritoPorId(int id);
    List<ItemCarritoDTO> obtenerItemsDelCarrito(int idCarrito, int pagina);
    String eliminarCarrito(int id);
    int contarCarritos();
}
