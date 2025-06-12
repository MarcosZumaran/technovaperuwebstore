package com.technovaperu.technovaperuwebstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technovaperu.technovaperuwebstore.model.dto.base.ProductoPresentacionDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearProductoPresentacionDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarProductoPresentacionDTO;
import com.technovaperu.technovaperuwebstore.model.response.ApiResponse;
import com.technovaperu.technovaperuwebstore.services.ProductoPresentacionService;

@RestController
@RequestMapping("/api/producto-presentacion")
public class ProductoPresentacionRestController {

    private final ProductoPresentacionService productoPresentacionService;

    @Autowired
    public ProductoPresentacionRestController(ProductoPresentacionService productoPresentacionService) {
        this.productoPresentacionService = productoPresentacionService;
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<ApiResponse<List<ProductoPresentacionDTO>>> obtenerPresentacionesPorProducto(@PathVariable long id) {
        List<ProductoPresentacionDTO> presentaciones = productoPresentacionService.obtenerPresentacionesPorProducto(id);
        return ResponseEntity.ok(ApiResponse.success(presentaciones, "Presentaciones obtenidas correctamente"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductoPresentacionDTO>> obtenerPresentacionPorId(@PathVariable long id) {
        ProductoPresentacionDTO presentacion = productoPresentacionService.obtenerPresentacionPorId(id);
        return ResponseEntity.ok(ApiResponse.success(presentacion, "Presentacion obtenida correctamente"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductoPresentacionDTO>> crearPresentacion(@RequestBody CrearProductoPresentacionDTO presentacion) {
        ProductoPresentacionDTO presentacionCreada = productoPresentacionService.crearPresentacion(presentacion);
        return new ResponseEntity<>(ApiResponse.success(presentacionCreada, "Presentacion creada correctamente"), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> actualizarPresentacion(@PathVariable long id, @RequestBody ActualizarProductoPresentacionDTO presentacion) {
        productoPresentacionService.actualizarPresentacion(id, presentacion);
        return ResponseEntity.ok(ApiResponse.success(null, "Presentacion actualizada correctamente"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> borrarPresentacion(@PathVariable long id) {
        productoPresentacionService.borrarPresentacion(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Presentacion borrada correctamente"));
    }

    @GetMapping("/existe/{id}")
    public ResponseEntity<ApiResponse<Boolean>> existePresentacion(@PathVariable long id) {
        boolean existe = productoPresentacionService.existePresentacion(id);
        return ResponseEntity.ok(ApiResponse.success(existe, "Presentacion obtenida correctamente"));
    }
    
}
