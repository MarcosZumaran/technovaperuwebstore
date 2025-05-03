package com.technovaperu.technovaperuwebstore.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technovaperu.technovaperuwebstore.services.CarritoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/carrito")
public class CarritoRestController {
    
    @Autowired
    private CarritoService carritoService;

    @GetMapping("/getCarrito/{id}")
    public Map<String, Object> getCarrito(@PathVariable int id) {
        return carritoService.obtenerCarritoPorId(id);
    }

    @PostMapping
    public String create(@RequestBody Map<String, Object> carrito) {
        return carritoService.crearCarrito(carrito);
    }

    @PostMapping("/updateCarrito/{id}")
    public String update(@PathVariable int id, @RequestBody Map<String, Object> carrito) {
        return carritoService.actualizarCarrito(id, carrito);
    }

    @DeleteMapping("/deleteCarrito/{id}")
    public String delete(@PathVariable int id) {
        return carritoService.eliminarCarrito(id);
    }

    @GetMapping("/getTotalCarritos")
    public int getTotal() {
        return carritoService.contarCarritos();
    }
    
}
