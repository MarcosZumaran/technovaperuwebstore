package com.technovaperu.technovaperuwebstore.services;

import java.util.List;

import com.technovaperu.technovaperuwebstore.model.dto.base.CarritoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearCarritoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarCarritoDTO;

public interface CarritoService {
    
    List<CarritoDTO> obtenerCarritos();
    List<CarritoDTO> obtenerCarritosPorUsuarioId(long id);
    CarritoDTO obtenerCarritoPorEstadoPorUsuarioId(long id, String estado);
    CarritoDTO obtenerCarritoPorId(long id);
    CarritoDTO crearCarrito(CrearCarritoDTO carritoDTO);
    void actualizarCarrito(long id, ActualizarCarritoDTO carritoDTO);
    void borrarCarrito(long id);
    boolean existeCarrito(long id);

}
