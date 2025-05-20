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

import com.technovaperu.technovaperuwebstore.model.dto.base.ItemCarritoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearItemCarritoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarItemCarritoDTO;
import com.technovaperu.technovaperuwebstore.model.response.ApiResponse;
import com.technovaperu.technovaperuwebstore.services.ItemCarritoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/item-carrito")
public class ItemCarritoRestController {

    private final ItemCarritoService itemCarritoService;

    @Autowired
    public ItemCarritoRestController(ItemCarritoService itemCarritoService) {
        this.itemCarritoService = itemCarritoService;
    }

    @GetMapping("/carrito/{id}")
    public ResponseEntity<ApiResponse<List<ItemCarritoDTO>>> obtenerItemsCarritoPorCarrito(@PathVariable int id, @RequestParam(name = "pagina", required = false, defaultValue = "1") int pagina) {
        List<ItemCarritoDTO> itemCarrito = itemCarritoService.obtenerItemsCarritoPorCarrito(id, pagina);
        return ResponseEntity.ok(ApiResponse.success(itemCarrito, "Items carrito obtenidos con éxito"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ItemCarritoDTO>> obtenerItemCarritoPorId(@PathVariable int id) {
        ItemCarritoDTO itemCarrito = itemCarritoService.obtenerItemCarritoPorId(id);
        return ResponseEntity.ok(ApiResponse.success(itemCarrito, "Item carrito obtenido con éxito"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ItemCarritoDTO>> crearItemCarrito(@RequestBody CrearItemCarritoDTO itemCarrito) {
        ItemCarritoDTO itemCarritoCreado = itemCarritoService.crearItemCarrito(itemCarrito);
        return new ResponseEntity<>(
            ApiResponse.success(itemCarritoCreado, "Item carrito creado con éxito"),
            HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ItemCarritoDTO>> actualizarItemCarrito(@PathVariable int id, @RequestBody ActualizarItemCarritoDTO itemCarrito) {
        itemCarritoService.actualizarItemCarrito(id, itemCarrito);
        return ResponseEntity.ok(ApiResponse.success(null, "Item carrito actualizado con éxito"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarItemCarrito(@PathVariable int id) {
        itemCarritoService.eliminarItemCarrito(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Item carrito eliminado con éxito"));
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Integer>> contarItemsCarrito() {
        int count = itemCarritoService.contarItemsCarrito();
        return ResponseEntity.ok(ApiResponse.success(count, "Total de items carrito obtenido con éxito"));
    }
    
}
