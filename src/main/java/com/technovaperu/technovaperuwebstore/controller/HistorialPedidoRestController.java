package com.technovaperu.technovaperuwebstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technovaperu.technovaperuwebstore.model.dto.base.HistorialPedidosDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearHistorialPedidosDTO;
import com.technovaperu.technovaperuwebstore.model.response.ApiResponse;
import com.technovaperu.technovaperuwebstore.services.HistorialPedidosService;


@RestController
@RequestMapping("/api/historial-pedido")
public class HistorialPedidoRestController {

    private final HistorialPedidosService historialPedidoService;

    @Autowired
    public HistorialPedidoRestController(HistorialPedidosService historialPedidoService) {
        this.historialPedidoService = historialPedidoService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<HistorialPedidosDTO>>> obtenerHistorialPedidos() {
        List<HistorialPedidosDTO> historialPedidos = historialPedidoService.obtenerHistorialPedidos();
        return ResponseEntity.ok(ApiResponse.success(historialPedidos, "HistorialPedidos obtenidos correctamente"));
    }

    @GetMapping("/usuario/{id}/pagina/{pagina}")
    public ResponseEntity<ApiResponse<List<HistorialPedidosDTO>>> obtenerHistorialPedidosPorUsuario(@PathVariable long id ,@PathVariable int pagina) {
        List<HistorialPedidosDTO> historialPedidos = historialPedidoService.obtenerHistorialPedidoPorUsuario(pagina, id);
        return ResponseEntity.ok(ApiResponse.success(historialPedidos, "HistorialPedidos obtenidos correctamente"));
    }

    @GetMapping("pedido/{id}/pagina/{pagina}")
    public ResponseEntity<ApiResponse<List<HistorialPedidosDTO>>> obtenerHistorialPedidosPorPedido(@PathVariable long id ,@PathVariable int pagina) {
        List<HistorialPedidosDTO> historialPedidos = historialPedidoService.obtenerHistorialPedidoPorPedido(pagina, id);
        return ResponseEntity.ok(ApiResponse.success(historialPedidos, "HistorialPedidos obtenidos correctamente"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<HistorialPedidosDTO>> obtenerHistorialPedidoPorId(@PathVariable long id) {
        HistorialPedidosDTO historialPedido = historialPedidoService.obtenerHistorialPedidoPorId(id);
        return ResponseEntity.ok(ApiResponse.success(historialPedido, "HistorialPedido obtenido correctamente"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<HistorialPedidosDTO>> crearHistorialPedido(@RequestBody CrearHistorialPedidosDTO historialPedidoDTO) {
        HistorialPedidosDTO historialPedido = historialPedidoService.crearHistorialPedido(historialPedidoDTO);
        return new ResponseEntity<>(ApiResponse.success(historialPedido, "HistorialPedido creado correctamente"), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> borrarHistorialPedido(@PathVariable long id) {
        historialPedidoService.borrarHistorialPedido(id);
        return ResponseEntity.ok(ApiResponse.success(null, "HistorialPedido borrado correctamente"));
    }

    @GetMapping("/existe/{id}")
    public ResponseEntity<ApiResponse<Boolean>> existeHistorialPedido(@PathVariable long id) {
        Boolean existe = historialPedidoService.existeHistorialPedido(id);
        return ResponseEntity.ok(ApiResponse.success(existe, "Existe el historialPedido correctamente"));
    }

}
