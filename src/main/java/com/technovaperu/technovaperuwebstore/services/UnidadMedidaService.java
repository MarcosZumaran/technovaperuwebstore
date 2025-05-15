package com.technovaperu.technovaperuwebstore.services;

import java.util.List;

import com.technovaperu.technovaperuwebstore.model.dto.base.UnidadMedidaDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearUnidadMedidaDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarUnidadMedidaDTO;

public interface UnidadMedidaService {
    List<UnidadMedidaDTO> obtenerTodasLasUnidadesMedida();
    List<UnidadMedidaDTO> obtenerUnidadesMedidaPorProducto(int idProducto);
    UnidadMedidaDTO obtenerUnidadMedidaPorId(int id);
    UnidadMedidaDTO crearUnidadMedida(CrearUnidadMedidaDTO unidadMedida);
    void actualizarUnidadMedida(int id, ActualizarUnidadMedidaDTO unidadMedida);
    void eliminarUnidadMedida(int id);
    int contarUnidadesMedida();
    int contarUnidadesMedidaPorProducto(int idProducto);
    boolean existeUnidadMedidaPorId(int id);
}