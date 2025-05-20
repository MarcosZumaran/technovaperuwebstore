package com.technovaperu.technovaperuwebstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technovaperu.technovaperuwebstore.model.dto.base.LoteDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearLoteDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarLoteDTO;
import com.technovaperu.technovaperuwebstore.model.response.ApiResponse;
import com.technovaperu.technovaperuwebstore.services.LoteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/lote")
@CrossOrigin(origins = "*")
public class LoteRestController {

    private final LoteService loteService;

    @Autowired
    public LoteRestController(LoteService loteService) {
        this.loteService = loteService;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LoteDTO>> obtenerLote(@PathVariable int id) {
        LoteDTO lote = loteService.obtenerLotePorId(id);
        return ResponseEntity.ok(ApiResponse.success(lote, "Lote obtenido con éxito"));
    }
    
    @GetMapping
    public ResponseEntity<ApiResponse<List<LoteDTO>>> obtenerTodosLosLotes() {
        List<LoteDTO> lotes = loteService.obtenerTodosLosLotes();
        return ResponseEntity.ok(ApiResponse.success(lotes, "Lotes obtenidos con éxito"));
    }
    
    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<ApiResponse<List<LoteDTO>>> obtenerLotesPorProducto(@PathVariable int idProducto) {
        List<LoteDTO> lotes = loteService.obtenerLotesPorProducto(idProducto);
        return ResponseEntity.ok(ApiResponse.success(lotes, "Lotes obtenidos con éxito"));
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<LoteDTO>> crearLote(@Valid @RequestBody CrearLoteDTO loteDTO) {
        int nuevoId = loteService.crearLote(loteDTO);
        LoteDTO nuevoLote = loteService.obtenerLotePorId(nuevoId);
        return new ResponseEntity<>(
            ApiResponse.success(nuevoLote, "Lote creado con éxito"),
            HttpStatus.CREATED
        );
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> actualizarLote(@PathVariable int id, @Valid @RequestBody ActualizarLoteDTO loteDTO) {
        loteService.actualizarLote(id, loteDTO);
        return ResponseEntity.ok(ApiResponse.success(null, "Lote actualizado con éxito"));
    }
    
    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Integer>> contarLotes() {
        int cantidad = loteService.contarLotes();
        return ResponseEntity.ok(ApiResponse.success(cantidad, "Total de lotes obtenido con éxito"));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarLote(@PathVariable int id) {
        loteService.eliminarLote(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Lote eliminado con éxito"));
    }
}