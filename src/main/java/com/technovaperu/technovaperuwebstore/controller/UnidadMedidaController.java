package com.technovaperu.technovaperuwebstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.technovaperu.technovaperuwebstore.model.dto.base.UnidadMedidaDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearUnidadMedidaDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarUnidadMedidaDTO;
import com.technovaperu.technovaperuwebstore.model.response.ApiResponse;
import com.technovaperu.technovaperuwebstore.services.UnidadMedidaService;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/unidades-medida")
@CrossOrigin(origins = "*")
public class UnidadMedidaController {

    private final UnidadMedidaService unidadMedidaService;

    @Autowired
    public UnidadMedidaController(UnidadMedidaService unidadMedidaService) {
        this.unidadMedidaService = unidadMedidaService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UnidadMedidaDTO>>> obtenerTodasLasUnidadesMedida() {
        List<UnidadMedidaDTO> unidadesMedida = unidadMedidaService.obtenerTodasLasUnidadesMedida();
        return ResponseEntity.ok(ApiResponse.success(unidadesMedida, "Unidades de medida obtenidas con éxito"));
    }
    
    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<ApiResponse<List<UnidadMedidaDTO>>> obtenerUnidadesMedidaPorProducto(@PathVariable int idProducto) {
        List<UnidadMedidaDTO> unidadesMedida = unidadMedidaService.obtenerUnidadesMedidaPorProducto(idProducto);
        return ResponseEntity.ok(ApiResponse.success(unidadesMedida, "Unidades de medida del producto obtenidas con éxito"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UnidadMedidaDTO>> obtenerUnidadMedidaPorId(@PathVariable int id) {
        UnidadMedidaDTO unidadMedida = unidadMedidaService.obtenerUnidadMedidaPorId(id);
        return ResponseEntity.ok(ApiResponse.success(unidadMedida, "Unidad de medida obtenida con éxito"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UnidadMedidaDTO>> crearUnidadMedida(@Valid @RequestBody CrearUnidadMedidaDTO unidadMedidaDTO) {
        UnidadMedidaDTO nuevaUnidadMedida = unidadMedidaService.crearUnidadMedida(unidadMedidaDTO);
        return new ResponseEntity<>(
                ApiResponse.success(nuevaUnidadMedida, "Unidad de medida creada con éxito"),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> actualizarUnidadMedida(
            @PathVariable int id,
            @Valid @RequestBody ActualizarUnidadMedidaDTO unidadMedidaDTO) {
        unidadMedidaService.actualizarUnidadMedida(id, unidadMedidaDTO);
        return ResponseEntity.ok(ApiResponse.success(null, "Unidad de medida actualizada con éxito"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarUnidadMedida(@PathVariable int id) {
        unidadMedidaService.eliminarUnidadMedida(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Unidad de medida eliminada con éxito"));
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Integer>> contarUnidadesMedida() {
        int count = unidadMedidaService.contarUnidadesMedida();
        return ResponseEntity.ok(ApiResponse.success(count, "Total de unidades de medida obtenido con éxito"));
    }
    
    @GetMapping("/count/producto/{idProducto}")
    public ResponseEntity<ApiResponse<Integer>> contarUnidadesMedidaPorProducto(@PathVariable int idProducto) {
        int count = unidadMedidaService.contarUnidadesMedidaPorProducto(idProducto);
        return ResponseEntity.ok(ApiResponse.success(count, "Total de unidades de medida del producto obtenido con éxito"));
    }
}