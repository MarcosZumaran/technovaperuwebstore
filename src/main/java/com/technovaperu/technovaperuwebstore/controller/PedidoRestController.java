package com.technovaperu.technovaperuwebstore.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.technovaperu.technovaperuwebstore.services.PedidoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/pedido")
public class PedidoRestController {

    private PedidoService pedidoService;

    @GetMapping("/getPedidoByUsuario/{id}")
    public List<Map<String, Object>> getPedidoByUsuario(@PathVariable int id, @RequestParam(defaultValue = "1") int pagina) {
        return pedidoService.obtenerPedidosPorUsuario(id, pagina);
    }

    @GetMapping("/getPedido/{id}")
    public Map<String, Object> getPedido(@PathVariable int id) {
        return pedidoService.obtenerPedidoPorId(id);
    }

    @PostMapping
    public String create(@RequestBody Map<String, Object> pedido) {
        return pedidoService.crearPedido(pedido);
    }

    @PostMapping("/updatePedido/{id}")
    public String update(@PathVariable int id, @RequestBody Map<String, Object> pedido) {
        return pedidoService.actualizarPedido(id, pedido);
    }

    @GetMapping("/deletePedido/{id}")
    public String delete(@PathVariable int id) {
        return pedidoService.eliminarPedido(id);
    }

    @GetMapping("/countPedidosByUsuario/{id}")
    public int countPedidosByUsuario(@PathVariable int id) {
        return pedidoService.contarPedidosPorUsuario(id);
    }

    @GetMapping("/countPedidos")
    public int count() {
        return pedidoService.contarPedidos();
    }
    
}
