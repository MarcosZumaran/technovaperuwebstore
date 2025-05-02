package com.technovaperu.technovaperuwebstore.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technovaperu.technovaperuwebstore.services.ProveedorService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/proveedor")
public class ProveedorRestController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public List<Map<String, Object>> getAll(){
        return proveedorService.obtenerTodosLosProveedores();
    }

    @GetMapping("/getProveedor/{id}")
    public Map<String, Object> getProveedor(@PathVariable int id){
        return proveedorService.obtenerProveedorPorId(id);
    }

    @PostMapping
    public String create(@RequestBody Map<String, Object> proveedor){
        return proveedorService.crearProveedor(proveedor);
    }

    @PostMapping("/updateProveedor/{id}")
    public String update(@PathVariable int id, @RequestBody Map<String, Object> proveedor){
        return proveedorService.actualizarProveedor(id, proveedor);
    }

    @GetMapping("/deleteProveedor/{id}")
    public String delete(@PathVariable int id){
        return proveedorService.eliminarProveedor(id);
    }

    @GetMapping("/countProveedores")
    public int count(){
        return proveedorService.contarProveedores();
    }
    
}
