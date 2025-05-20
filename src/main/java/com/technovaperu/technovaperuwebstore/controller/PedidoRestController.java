package com.technovaperu.technovaperuwebstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.technovaperu.technovaperuwebstore.model.dto.base.PedidoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearPedidoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarPedidoDTO;
import com.technovaperu.technovaperuwebstore.model.response.ApiResponse;
import com.technovaperu.technovaperuwebstore.services.PedidoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/pedido")
public class PedidoRestController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoRestController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<ApiResponse<List<PedidoDTO>>> obtenerPedidosPorUsuario(@PathVariable int id, @RequestParam(name = "pagina", required = false, defaultValue = "1") int pagina) {
        List<PedidoDTO> pedidos = pedidoService.obtenerPedidosPorUsuario(id, pagina);
        return ResponseEntity.ok(ApiResponse.success(pedidos, "Pedidos obtenidos con éxito"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PedidoDTO>> obtenerPedidoPorId(@PathVariable int id) {
        PedidoDTO pedido = pedidoService.obtenerPedidoPorId(id);
        return ResponseEntity.ok(ApiResponse.success(pedido, "Pedido obtenido con éxito"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PedidoDTO>> crearPedido(@RequestBody CrearPedidoDTO pedido) {
        PedidoDTO pedidoCreado = pedidoService.crearPedido(pedido);
        return new ResponseEntity<>(
            ApiResponse.success(pedidoCreado, "Pedido creado con éxito"),
            HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PedidoDTO>> actualizarPedido(@PathVariable int id, @RequestBody ActualizarPedidoDTO pedido) {
        pedidoService.actualizarPedido(id, pedido);
        return ResponseEntity.ok(ApiResponse.success(null, "Pedido actualizado con éxito"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarPedido(@PathVariable int id) {
        pedidoService.eliminarPedido(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Pedido eliminado con éxito"));
    }

    @GetMapping("/count/usuario/{id}")
    public ResponseEntity<ApiResponse<Integer>> contarPedidosPorUsuario(@PathVariable int id) {
        int cantidad = pedidoService.contarPedidosPorUsuario(id);
        return ResponseEntity.ok(ApiResponse.success(cantidad, "Total de pedidos obtenido con éxito"));
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Integer>> contarPedidos() {
        int cantidad = pedidoService.contarPedidos();
        return ResponseEntity.ok(ApiResponse.success(cantidad, "Total de pedidos obtenido con éxito"));
    }
    
}
