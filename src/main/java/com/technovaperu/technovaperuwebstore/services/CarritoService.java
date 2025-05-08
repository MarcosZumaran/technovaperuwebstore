package com.technovaperu.technovaperuwebstore.services;

import com.technovaperu.technovaperuwebstore.model.dto.base.CarritoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearCarritoDTO;

public interface CarritoService {
    /**
     * Obtiene un carrito por su ID
     * @param id ID del carrito a buscar
     * @return CarritoDTO con la información del carrito
     * @throws RecursoNoEncontradoException si no existe un carrito con ese ID
     */
    CarritoDTO obtenerCarritoPorId(int id);

    CarritoDTO obtenerCarritoPorUsuario(int idUsuario);
    
    /**
     * Crea un nuevo carrito
     * @param carrito Datos del carrito a crear
     * @return ID del carrito creado
     * @throws RecursoDuplicadoException si el usuario ya tiene un carrito
     */
    int crearCarrito(CrearCarritoDTO carrito);
    
    /**
     * Cuenta el número total de carritos
     * @return Cantidad de carritos existentes
     */
    int contarCarritos();
    
    /**
     * Verifica si existe un carrito para un usuario específico
     * @param idUsuario ID del usuario a verificar
     * @return true si existe un carrito para ese usuario, false en caso contrario
     */
    boolean existeCarritoParaUsuario(int idUsuario);
    
    /**
     * Elimina un carrito por su ID
     * @param id ID del carrito a eliminar
     * @throws RecursoNoEncontradoException si no existe un carrito con ese ID
     */
    void eliminarCarrito(int id);
}