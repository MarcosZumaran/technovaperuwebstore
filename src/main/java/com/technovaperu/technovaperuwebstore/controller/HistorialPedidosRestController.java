package com.technovaperu.technovaperuwebstore.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.technovaperu.technovaperuwebstore.services.HistorialPedidosService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/historialPedidos")
public class HistorialPedidosRestController {

    @Autowired
    private HistorialPedidosService historialPedidosService;

    @GetMapping("/getHistorialPedidosByPedido/{id}")
    public List<Map<String, Object>> getHistorialPedidosByPedido(@PathVariable int id, @RequestParam(defaultValue = "1") int pagina) {
        return historialPedidosService.obtenerHistorialPorPedido(id, pagina);
    }

    @GetMapping("/getHistorialPedido/{id}")
    public Map<String, Object> getHistorialPedido(@PathVariable int id) {
        return historialPedidosService.obtenerHistorialPorId(id);
    }

    @PostMapping
    public String create (@RequestBody Map<String, Object> historialPedido) {
        return historialPedidosService.crearHistorialPedido(historialPedido);
    }

    @PostMapping("/updateHistorialPedido/{id}")
    public String update (@PathVariable int id, @RequestBody Map<String, Object> historialPedido) {
        return historialPedidosService.actualizarHistorialPedido(id, historialPedido);
    }

    @DeleteMapping("/deleteHistorialPedido/{id}")
    public String delete (@PathVariable int id) {
        return historialPedidosService.eliminarHistorialPedido(id);
    }

    @GetMapping("/countHistorialPedidos")
    public int count() {
        return historialPedidosService.contarHistorialPedidos();
    }
    
}
