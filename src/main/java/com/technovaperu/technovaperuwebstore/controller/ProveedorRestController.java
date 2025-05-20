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

import com.technovaperu.technovaperuwebstore.model.dto.base.ProveedorDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearProveedorDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarProveedorDTO;
import com.technovaperu.technovaperuwebstore.model.response.ApiResponse;
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
    public ResponseEntity<ApiResponse<List<ProveedorDTO>>> obtenerTodosLosProveedores(int pagina){
        List<ProveedorDTO> proveedores = proveedorService.obtenerTodosLosProveedores(pagina);
        return ResponseEntity.ok(ApiResponse.success(proveedores, "Proveedores obtenidos con éxito"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProveedorDTO>> obtenerProveedorPorId(@PathVariable int id){
        ProveedorDTO proveedor = proveedorService.obtenerProveedorPorId(id);
        return ResponseEntity.ok(ApiResponse.success(proveedor, "Proveedor obtenido con éxito"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProveedorDTO>> crearProveedor(@RequestBody CrearProveedorDTO proveedor){
        ProveedorDTO proveedorCreado = proveedorService.crearProveedor(proveedor);
        return new ResponseEntity<>(
            ApiResponse.success(proveedorCreado, "Proveedor creado con éxito"),
            HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> actualizarProveedor(@PathVariable int id, @RequestBody ActualizarProveedorDTO proveedor){
        proveedorService.actualizarProveedor(id, proveedor);
        return ResponseEntity.ok(ApiResponse.success(null, "Proveedor actualizado con éxito"));
    }

    @DeleteMapping("/{id}") 
    public ResponseEntity<ApiResponse<Void>> eliminarProveedor(@PathVariable int id){
        proveedorService.eliminarProveedor(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Proveedor eliminado con éxito"));
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Integer>> contarProveedores(){
        int count = proveedorService.contarProveedores();
        return ResponseEntity.ok(ApiResponse.success(count, "Total de proveedores obtenido con éxito"));
    }
    
}
