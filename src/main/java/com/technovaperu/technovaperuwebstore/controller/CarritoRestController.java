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
    public ResponseEntity<CarritoDTO> obtenerCarrito(@PathVariable int id) {
        CarritoDTO carrito = carritoService.obtenerCarritoPorId(id);
        return ResponseEntity.ok(carrito);
    }

    @GetMapping("/carrito/usuario/{idUsuario}")
    public ResponseEntity<CarritoDTO> obtenerCarritoPorUsuario(@PathVariable int idUsuario) {
        CarritoDTO carrito = carritoService.obtenerCarritoPorUsuario(idUsuario);
        return ResponseEntity.ok(carrito);
    }
    
    @PostMapping
    public ResponseEntity<CarritoDTO> crearCarrito(@Valid @RequestBody CrearCarritoDTO carritoDTO) {
        int nuevoId = carritoService.crearCarrito(carritoDTO);
        CarritoDTO nuevoCarrito = carritoService.obtenerCarritoPorId(nuevoId);
        return new ResponseEntity<>(nuevoCarrito, HttpStatus.CREATED);
    }
    
    @GetMapping("/count")
    public ResponseEntity<Integer> contarCarritos() {
        int cantidad = carritoService.contarCarritos();
        return ResponseEntity.ok(cantidad);
    }
    
    @GetMapping("/carrito/usuario/{idUsuario}/existe")
    public ResponseEntity<Boolean> existeCarritoParaUsuario(@PathVariable int idUsuario) {
        boolean existe = carritoService.existeCarritoParaUsuario(idUsuario);
        return ResponseEntity.ok(existe);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarrito(@PathVariable int id) {
        carritoService.eliminarCarrito(id);
        return ResponseEntity.noContent().build();
    }
}