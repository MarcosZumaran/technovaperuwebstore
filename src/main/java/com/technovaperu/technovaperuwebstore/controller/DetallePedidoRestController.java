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
import org.springframework.web.bind.annotation.RestController;

import com.technovaperu.technovaperuwebstore.model.dto.base.DetallePedidoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearDetallePedidoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarDetallePedidoDTO;
import com.technovaperu.technovaperuwebstore.model.response.ApiResponse;
import com.technovaperu.technovaperuwebstore.services.DetallePedidoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/detalle-pedido")
public class DetallePedidoRestController {

    private final DetallePedidoService detallePedidoService;

    @Autowired
    public DetallePedidoRestController(DetallePedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }

    @GetMapping("/pedido/{id}")
    public ResponseEntity<ApiResponse<List<DetallePedidoDTO>>> obtenerDetallesPedidoPorPedido (int id) {
        List<DetallePedidoDTO> detallesPedido = detallePedidoService.obtenerDetallesPedidoPorPedido(id, 1);
        return ResponseEntity.ok(ApiResponse.success(detallesPedido, "Detalles pedido obtenidos con éxito"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DetallePedidoDTO>> obtenerDetallePedidoPorId(int id) {
        DetallePedidoDTO detallePedido = detallePedidoService.obtenerDetallePedidoPorId(id);
        return ResponseEntity.ok(ApiResponse.success(detallePedido, "Detalle pedido obtenido con éxito"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DetallePedidoDTO>> crearDetallePedido(@RequestBody CrearDetallePedidoDTO detallePedido) {
        DetallePedidoDTO detallePedidoCreado = detallePedidoService.crearDetallePedido(detallePedido);
        return new ResponseEntity<>(
            ApiResponse.success(detallePedidoCreado, "Detalle pedido creado con éxito"),
            HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> actualizarDetallePedido(@PathVariable int id, @RequestBody ActualizarDetallePedidoDTO detallePedido) {
        detallePedidoService.actualizarDetallePedido(id, detallePedido);
        return ResponseEntity.ok(ApiResponse.success(null, "Detalle pedido actualizado con éxito"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarDetallePedido(@PathVariable long id) {
        detallePedidoService.borrarDetallePedido(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Detalle pedido eliminado con éxito"));
    }
    
}
