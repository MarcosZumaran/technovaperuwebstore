package com.technovaperu.technovaperuwebstore.services;

import java.util.List;

import com.technovaperu.technovaperuwebstore.model.dto.base.LoteDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearLoteDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarLoteDTO;

public interface LoteService {
    
    /*
    * Obtiene una lista de todos los lotes
    * @return Lista de lotes
    */
    List<LoteDTO> obtenerTodosLosLotes();

    /*
    * Obtiene una lista de los lotes de un producto
    * @param idProducto ID del producto
    * @return Lista de lotes
    */
    List<LoteDTO> obtenerLotesPorProducto(int idProducto);

    /*
    * Obtiene un lote por su ID
    * @param id ID del lote a buscar
    * @return LoteDTO con la información del lote
    * @throws RecursoNoEncontradoException si no existe un lote con ese ID
    */
    LoteDTO obtenerLotePorId(int id);

    /*
    * Crea un nuevo lote
    * @param lote Datos del lote a crear
    * @return ID del lote creado
    * @throws RecursoDuplicadoException si el producto ya tiene un lote
    */
    int crearLote(CrearLoteDTO lote);

    /*
    * Actualiza un lote
    * @param id ID del lote a actualizar
    * @param lote Datos del lote a actualizar
    * @throws RecursoNoEncontradoException si no existe un lote con ese ID
    */
    void actualizarLote(int id, ActualizarLoteDTO lote);

    /*
    * Elimina un lote por su ID
    * @param id ID del lote a eliminar
    * @throws RecursoNoEncontradoException si no existe un lote con ese ID
    */
    void eliminarLote(int id);

    /*
    * Cuenta el número total de lotes
    * @return Cantidad de lotes existentes
    */
    int contarLotes();

}
