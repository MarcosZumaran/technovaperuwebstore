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

import com.technovaperu.technovaperuwebstore.services.UsuarioService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/usuario")
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Map<String, Object>> getAll(@RequestParam(defaultValue = "1") int page){
        return usuarioService.obtenerTodosLosUsuarios(page);
    }

    @GetMapping("/getUsuario/{id}")
    public Map<String, Object> getUsuario(@PathVariable int id){
        return usuarioService.obtenerUsuarioPorId(id);
    }

    @GetMapping("/getCarritoUsuario/{id}")
    public int getCarritoUsuario(@PathVariable int id){
        return usuarioService.obtenerCarritoDeUsuario(id);
    }

    @PostMapping
    public String create(@RequestBody Map<String, Object> usuario){
        return usuarioService.crearUsuario(usuario);
    }

    @PostMapping("/updateUsuario/{id}")
    public String update(@PathVariable int id, @RequestBody Map<String, Object> usuario){
        return usuarioService.actualizarUsuario(id, usuario);
    }

    @DeleteMapping("/deleteUsuario/{id}")
    public String delete(@PathVariable int id){
        return usuarioService.eliminarUsuario(id);
    }

    @GetMapping("/countUsuarios")
    public int count(){
        return usuarioService.contarUsuarios();
    }
    
}
