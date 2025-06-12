package com.technovaperu.technovaperuwebstore.services;

import java.util.List;

import com.technovaperu.technovaperuwebstore.model.dto.base.ProductoPresentacionDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearProductoPresentacionDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarProductoPresentacionDTO;

public interface ProductoPresentacionService {

    List<ProductoPresentacionDTO> obtenerPresentacionesPorProducto(long idProducto);
    ProductoPresentacionDTO obtenerPresentacionPorId(long id);
    ProductoPresentacionDTO crearPresentacion(CrearProductoPresentacionDTO presentacionDTO);
    void actualizarPresentacion(long id, ActualizarProductoPresentacionDTO presentacionDTO);
    void borrarPresentacion(long id);
    boolean existePresentacion(long id);
    
}