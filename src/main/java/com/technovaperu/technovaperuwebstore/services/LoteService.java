package com.technovaperu.technovaperuwebstore.services;

import java.util.List;

import com.technovaperu.technovaperuwebstore.model.dto.base.LoteDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearLoteDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarLoteDTO;

public interface LoteService {

    List<LoteDTO> obtenerTodosLosLotes();
    List<LoteDTO> obtenerLotes(int pagina);
    List<LoteDTO> obtenerLotesPorProducto(int pag, long idProducto);
    List<LoteDTO> obtenerLotesPorProveedor(int pag, long idProveedor);
    LoteDTO obtenerLotePorId(long id);
    LoteDTO crearLote(CrearLoteDTO loteDTO);
    void actualizarLote(long id, ActualizarLoteDTO loteDTO);
    void eliminarLote(long id);
    boolean existeLote(long id);
    
}
