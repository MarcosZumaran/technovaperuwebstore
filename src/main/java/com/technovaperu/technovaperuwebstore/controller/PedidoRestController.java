package com.technovaperu.technovaperuwebstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.technovaperu.technovaperuwebstore.model.dto.base.PedidoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearPedidoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarPedidoDTO;
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

    @GetMapping("/getPedidoByUsuario/{id}")
    public ResponseEntity<List<PedidoDTO>> obtenerPedidosPorUsuario(@PathVariable int id, @RequestParam(name = "pagina", required = false, defaultValue = "1") int pagina) {
        List<PedidoDTO> pedidos = pedidoService.obtenerPedidosPorUsuario(id, pagina);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/getPedido/{id}")
    public ResponseEntity<PedidoDTO> obtenerPedidoPorId(@PathVariable int id) {
        PedidoDTO pedido = pedidoService.obtenerPedidoPorId(id);
        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> crearPedido(@RequestBody CrearPedidoDTO pedido) {
        PedidoDTO pedidoCreado = pedidoService.crearPedido(pedido);
        return new ResponseEntity<>(pedidoCreado, HttpStatus.CREATED);
    }

    @PostMapping("/updatePedido/{id}")
    public ResponseEntity<PedidoDTO> actualizarPedido(@PathVariable int id, @RequestBody ActualizarPedidoDTO pedido) {
        pedidoService.actualizarPedido(id, pedido);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/deletePedido/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable int id) {
        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/countPedidosByUsuario/{id}")
    public ResponseEntity<Integer> contarPedidosPorUsuario(@PathVariable int id) {
        int cantidad = pedidoService.contarPedidosPorUsuario(id);
        return ResponseEntity.ok(cantidad);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> contarPedidos() {
        int cantidad = pedidoService.contarPedidos();
        return ResponseEntity.ok(cantidad);
    }
    
}
