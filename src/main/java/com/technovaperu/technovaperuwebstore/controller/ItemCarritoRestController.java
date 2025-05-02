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

import com.technovaperu.technovaperuwebstore.services.ItemCarritoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/itemCarrito")
public class ItemCarritoRestController {

    @Autowired
    private ItemCarritoService itemCarritoService;

    @GetMapping("/getItemCarritoByCarrito/{id}")
    public List<Map<String, Object>> getItemCarritoByCarrito(@PathVariable int id, @RequestParam(defaultValue = "1") int pagina) {
        return itemCarritoService.obtenerItemsCarritoPorCarrito(id, pagina);
    }

    @GetMapping("/getItemCarrito/{id}")
    public Map<String, Object> getItemCarrito(@PathVariable int id) {
        return itemCarritoService.obtenerItemCarritoPorId(id);
    }

    @PostMapping
    public String create(@RequestBody Map<String, Object> itemCarrito) {
        return itemCarritoService.crearItemCarrito(itemCarrito);
    }

    @PostMapping("/updateItemCarrito/{id}")
    public String update(@PathVariable int id, @RequestBody Map<String, Object> itemCarrito) {
        return itemCarritoService.actualizarItemCarrito(id, itemCarrito);
    }

    @DeleteMapping("/deleteItemCarrito/{id}")
    public String delete(@PathVariable int id) {
        return itemCarritoService.eliminarItemCarrito(id);
    }

    @GetMapping("/countItemCarrito")
    public int count() {
        return itemCarritoService.contarItemsCarrito();
    }
    
}
