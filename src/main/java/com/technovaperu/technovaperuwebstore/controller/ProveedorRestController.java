package com.technovaperu.technovaperuwebstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technovaperu.technovaperuwebstore.model.dto.base.ProveedorDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearProveedorDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarProveedorDTO;
import com.technovaperu.technovaperuwebstore.services.ProveedorService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/proveedor")
public class ProveedorRestController {

    private final ProveedorService proveedorService;

    @Autowired
    public ProveedorRestController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping
    public ResponseEntity<List<ProveedorDTO>> obtenerTodosLosProveedores(int pagina){
        List<ProveedorDTO> proveedores = proveedorService.obtenerTodosLosProveedores(pagina);
        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("/getProveedor/{id}")
    public ResponseEntity<ProveedorDTO> obtenerProveedorPorId(@PathVariable int id){
        ProveedorDTO proveedor = proveedorService.obtenerProveedorPorId(id);
        return ResponseEntity.ok(proveedor);
    }

    @PostMapping
    public ResponseEntity<ProveedorDTO> crearProveedor(@RequestBody CrearProveedorDTO proveedor){
        ProveedorDTO proveedorCreado = proveedorService.crearProveedor(proveedor);
        return new ResponseEntity<>(proveedorCreado, HttpStatus.CREATED);
    }

    @PostMapping("/updateProveedor/{id}")
    public ResponseEntity<Void> actualizarProveedor(@PathVariable int id, @RequestBody ActualizarProveedorDTO proveedor){
        proveedorService.actualizarProveedor(id, proveedor);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/deleteProveedor/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable int id){
        proveedorService.eliminarProveedor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> contarProveedores(){
        int count = proveedorService.contarProveedores();
        return ResponseEntity.ok(count);
    }
    
}
