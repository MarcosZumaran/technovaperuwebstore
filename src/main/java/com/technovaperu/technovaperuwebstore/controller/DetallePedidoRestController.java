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
import org.springframework.web.bind.annotation.RestController;

import com.technovaperu.technovaperuwebstore.model.dto.base.DetallePedidoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearDetallePedidoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarDetallePedidoDTO;
import com.technovaperu.technovaperuwebstore.services.DetallePedidoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/detallePedido")
public class DetallePedidoRestController {

    private final DetallePedidoService detallePedidoService;

    @Autowired
    public DetallePedidoRestController(DetallePedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }

    @GetMapping("/getDetallePedidoByPedido/{id}")
    public ResponseEntity<List<DetallePedidoDTO>> obtenerDetallesPedidoPorPedido (int id) {
        List<DetallePedidoDTO> detallesPedido = detallePedidoService.obtenerDetallesPedidoPorPedido(id, 1);
        return ResponseEntity.ok(detallesPedido);
    }

    @GetMapping("/getDetallePedido/{id}")
    public ResponseEntity<DetallePedidoDTO> obtenerDetallePedidoPorId(int id) {
        DetallePedidoDTO detallePedido = detallePedidoService.obtenerDetallePedidoPorId(id);
        return ResponseEntity.ok(detallePedido);
    }

    @PostMapping
    public ResponseEntity<DetallePedidoDTO> crearDetallePedido(@RequestBody CrearDetallePedidoDTO detallePedido) {
        DetallePedidoDTO detallePedidoCreado = detallePedidoService.crearDetallePedido(detallePedido);
        return new ResponseEntity<>(detallePedidoCreado, HttpStatus.CREATED);
    }

    @PostMapping("/updateDetallePedido/{id}")
    public ResponseEntity<Void> actualizarDetallePedido(@PathVariable int id, @RequestBody ActualizarDetallePedidoDTO detallePedido) {
        detallePedidoService.actualizarDetallePedido(id, detallePedido);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteDetallePedido/{id}")
    public ResponseEntity<Void> eliminarDetallePedido(@PathVariable int id) {
        detallePedidoService.eliminarDetallePedido(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/countDetallePedidos")
    public ResponseEntity<Integer> contarDetallesPedido() {
        int count = detallePedidoService.contarDetallesPedido();
        return ResponseEntity.ok(count);
    }
    
}
