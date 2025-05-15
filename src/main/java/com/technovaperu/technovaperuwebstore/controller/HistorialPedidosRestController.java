package com.technovaperu.technovaperuwebstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.technovaperu.technovaperuwebstore.model.dto.base.HistorialPedidosDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearHistorialPedidosDTO;
import com.technovaperu.technovaperuwebstore.services.HistorialPedidosService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/historial-pedido")
public class HistorialPedidosRestController {

    private final HistorialPedidosService historialPedidosService;

    @Autowired
    public HistorialPedidosRestController(HistorialPedidosService historialPedidosService) {
        this.historialPedidosService = historialPedidosService;
    }

    @GetMapping("/getHistorialPedidosByPedido/{id}")
    public ResponseEntity<List<HistorialPedidosDTO>> obtenerHistorialPorPedido(@PathVariable int id, @RequestParam(name = "pagina", required = false, defaultValue = "1") int pagina) {
        List<HistorialPedidosDTO> historialPedidos = historialPedidosService.obtenerHistorialPorPedido(id, pagina);
        return ResponseEntity.ok(historialPedidos);
    }

    @GetMapping("/getHistorialPedido/{id}")
    public ResponseEntity<HistorialPedidosDTO> obtenerHistorialPorId(@PathVariable int id) {
        HistorialPedidosDTO historialPedido = historialPedidosService.obtenerHistorialPorId(id);
        return ResponseEntity.ok(historialPedido);
    }

    @PostMapping
    public ResponseEntity<HistorialPedidosDTO> crearHistorialPedido(@RequestBody CrearHistorialPedidosDTO historialPedido) {
        HistorialPedidosDTO historialPedidoCreado = historialPedidosService.crearHistorialPedido(historialPedido);
        return new ResponseEntity<>(historialPedidoCreado, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteHistorialPedido/{id}")
    public ResponseEntity<Void> eliminarHistorialPedido(@PathVariable int id) {
        historialPedidosService.eliminarHistorialPedido(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> contarHistorialPedidos() {
        int count = historialPedidosService.contarHistorialPedidos();
        return ResponseEntity.ok(count);
    }
    
}
