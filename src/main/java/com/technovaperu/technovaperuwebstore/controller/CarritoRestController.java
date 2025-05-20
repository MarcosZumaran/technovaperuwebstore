package com.technovaperu.technovaperuwebstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technovaperu.technovaperuwebstore.model.dto.base.CarritoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearCarritoDTO;
import com.technovaperu.technovaperuwebstore.model.response.ApiResponse;
import com.technovaperu.technovaperuwebstore.services.CarritoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "*")
public class CarritoRestController {

    private final CarritoService carritoService;

    @Autowired
    public CarritoRestController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CarritoDTO>> obtenerCarrito(@PathVariable int id) {
        CarritoDTO carrito = carritoService.obtenerCarritoPorId(id);
        return ResponseEntity.ok(ApiResponse.success(carrito, "Carrito obtenido con éxito"));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<ApiResponse<CarritoDTO>> obtenerCarritoPorUsuario(@PathVariable int idUsuario) {
        CarritoDTO carrito = carritoService.obtenerCarritoPorUsuario(idUsuario);
        return ResponseEntity.ok(ApiResponse.success(carrito, "Carrito obtenido con éxito"));
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<CarritoDTO>> crearCarrito(@Valid @RequestBody CrearCarritoDTO carritoDTO) {
        int nuevoId = carritoService.crearCarrito(carritoDTO);
        CarritoDTO nuevoCarrito = carritoService.obtenerCarritoPorId(nuevoId);
        return new ResponseEntity<>(
            ApiResponse.success(nuevoCarrito, "Carrito creado con éxito"),
            HttpStatus.CREATED);
    }
    
    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Integer>> contarCarritos() {
        int cantidad = carritoService.contarCarritos();
        return ResponseEntity.ok(ApiResponse.success(cantidad, "Total de carritos obtenido con éxito"));
    }
    
    @GetMapping("/usuario/{idUsuario}/existe")
    public ResponseEntity<ApiResponse<Boolean>> existeCarritoParaUsuario(@PathVariable int idUsuario) {
        boolean existe = carritoService.existeCarritoParaUsuario(idUsuario);
        return ResponseEntity.ok(ApiResponse.success(existe, "Existe o no el carrito del usuario"));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarCarrito(@PathVariable int id) {
        carritoService.eliminarCarrito(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Carrito eliminado con éxito"));
    }
}