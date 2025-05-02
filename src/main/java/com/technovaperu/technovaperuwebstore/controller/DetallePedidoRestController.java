package com.technovaperu.technovaperuwebstore.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.technovaperu.technovaperuwebstore.services.DetallePedidoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public class DetallePedidoRestController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    @GetMapping("/getDetallePedidoByPedido/{id}")
    public List<Map<String, Object>> getDetallePedidoByPedido(@PathVariable int id) {
        return detallePedidoService.obtenerDetallesPedidoPorPedido(id, 1);
    }

    @GetMapping("/getDetallePedido/{id}")
    public Map<String, Object> getDetallePedido(@PathVariable int id) {
        return detallePedidoService.obtenerDetallePedidoPorId(id);
    }

    @PostMapping
    public String create(@RequestBody Map<String, Object> detallePedido) {
        return detallePedidoService.crearDetallePedido(detallePedido);
    }

    @PostMapping("/updateDetallePedido/{id}")
    public String update(@PathVariable int id, @RequestBody Map<String, Object> detallePedido) {
        return detallePedidoService.actualizarDetallePedido(id, detallePedido);
    }

    @GetMapping("/deleteDetallePedido/{id}")
    public String delete(@PathVariable int id) {
        return detallePedidoService.eliminarDetallePedido(id);
    }

    @GetMapping("/countDetallePedidos")
    public int count() {
        return detallePedidoService.contarDetallesPedido();
    }
    
}
