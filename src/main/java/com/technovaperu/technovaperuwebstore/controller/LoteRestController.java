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
    public ResponseEntity<LoteDTO> obtenerLote(@PathVariable int id) {
        LoteDTO lote = loteService.obtenerLotePorId(id);
        return ResponseEntity.ok(lote);
    }
    
    @GetMapping
    public ResponseEntity<List<LoteDTO>> obtenerTodosLosLotes() {
        List<LoteDTO> lotes = loteService.obtenerTodosLosLotes();
        return ResponseEntity.ok(lotes);
    }
    
    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<List<LoteDTO>> obtenerLotesPorProducto(@PathVariable int idProducto) {
        List<LoteDTO> lotes = loteService.obtenerLotesPorProducto(idProducto);
        return ResponseEntity.ok(lotes);
    }
    
    @PostMapping
    public ResponseEntity<LoteDTO> crearLote(@Valid @RequestBody CrearLoteDTO loteDTO) {
        int nuevoId = loteService.crearLote(loteDTO);
        LoteDTO nuevoLote = loteService.obtenerLotePorId(nuevoId);
        return new ResponseEntity<>(nuevoLote, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarLote(@PathVariable int id, @Valid @RequestBody ActualizarLoteDTO loteDTO) {
        loteService.actualizarLote(id, loteDTO);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/count")
    public ResponseEntity<Integer> contarLotes() {
        int cantidad = loteService.contarLotes();
        return ResponseEntity.ok(cantidad);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLote(@PathVariable int id) {
        loteService.eliminarLote(id);
        return ResponseEntity.noContent().build();
    }
}