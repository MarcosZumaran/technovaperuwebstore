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

import com.technovaperu.technovaperuwebstore.model.dto.base.ItemCarritoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearItemCarritoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarItemCarritoDTO;
import com.technovaperu.technovaperuwebstore.services.ItemCarritoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/itemCarrito")
public class ItemCarritoRestController {

    private final ItemCarritoService itemCarritoService;

    @Autowired
    public ItemCarritoRestController(ItemCarritoService itemCarritoService) {
        this.itemCarritoService = itemCarritoService;
    }

    @GetMapping("/getItemCarritoByCarrito/{id}")
    public ResponseEntity<List<ItemCarritoDTO>> obtenerItemsCarritoPorCarrito(@PathVariable int id, @RequestParam(name = "pagina", required = false, defaultValue = "1") int pagina) {
        List<ItemCarritoDTO> itemCarrito = itemCarritoService.obtenerItemsCarritoPorCarrito(id, pagina);
        return ResponseEntity.ok(itemCarrito);
    }

    @GetMapping("/getItemCarrito/{id}")
    public ResponseEntity<ItemCarritoDTO> obtenerItemCarritoPorId(@PathVariable int id) {
        ItemCarritoDTO itemCarrito = itemCarritoService.obtenerItemCarritoPorId(id);
        return ResponseEntity.ok(itemCarrito);
    }

    @PostMapping
    public ResponseEntity<ItemCarritoDTO> crearItemCarrito(@RequestBody CrearItemCarritoDTO itemCarrito) {
        ItemCarritoDTO itemCarritoCreado = itemCarritoService.crearItemCarrito(itemCarrito);
        return new ResponseEntity<>(itemCarritoCreado, HttpStatus.CREATED);
    }

    @PostMapping("/updateItemCarrito/{id}")
    public ResponseEntity<ItemCarritoDTO> update(@PathVariable int id, @RequestBody ActualizarItemCarritoDTO itemCarrito) {
        itemCarritoService.actualizarItemCarrito(id, itemCarrito);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteItemCarrito/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        itemCarritoService.eliminarItemCarrito(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/countItemCarrito")
    public ResponseEntity<Integer> contarItemsCarrito() {
        int count = itemCarritoService.contarItemsCarrito();
        return ResponseEntity.ok(count);
    }
    
}
