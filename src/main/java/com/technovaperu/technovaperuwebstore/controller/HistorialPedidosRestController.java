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
import com.technovaperu.technovaperuwebstore.model.response.ApiResponse;
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

    @GetMapping("/pedido/{id}")
    public ResponseEntity<ApiResponse<List<HistorialPedidosDTO>>> obtenerHistorialPorPedido(@PathVariable int id, @RequestParam(name = "pagina", required = false, defaultValue = "1") int pagina) {
        List<HistorialPedidosDTO> historialPedidos = historialPedidosService.obtenerHistorialPorPedido(id, pagina);
        return ResponseEntity.ok(ApiResponse.success(historialPedidos, "Historial pedido obtenidos con éxito"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<HistorialPedidosDTO>> obtenerHistorialPorId(@PathVariable int id) {
        HistorialPedidosDTO historialPedido = historialPedidosService.obtenerHistorialPorId(id);
        return ResponseEntity.ok(ApiResponse.success(historialPedido, "Historial pedido obtenido con éxito"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<HistorialPedidosDTO>> crearHistorialPedido(@RequestBody CrearHistorialPedidosDTO historialPedido) {
        HistorialPedidosDTO historialPedidoCreado = historialPedidosService.crearHistorialPedido(historialPedido);
        return new ResponseEntity<>(
            ApiResponse.success(historialPedidoCreado, "Historial pedido creado con éxito"),
            HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarHistorialPedido(@PathVariable int id) {
        historialPedidosService.eliminarHistorialPedido(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Historial pedido eliminado con éxito"));
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Integer>> contarHistorialPedidos() {
        int count = historialPedidosService.contarHistorialPedidos();
        return ResponseEntity.ok(ApiResponse.success(count, "Total de historial pedidos obtenido con éxito"));
    }
    
}
