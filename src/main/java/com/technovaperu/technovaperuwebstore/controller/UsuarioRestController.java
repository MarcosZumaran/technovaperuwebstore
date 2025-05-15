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

import com.technovaperu.technovaperuwebstore.model.dto.base.UsuarioDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearUsuarioDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarUsuarioDTO;
import com.technovaperu.technovaperuwebstore.services.UsuarioService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioRestController {

    private UsuarioService usuarioService;

    @Autowired
    public UsuarioRestController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> obtenerTodosLosUsuarios(@RequestParam(defaultValue = "1") int page){
        List<UsuarioDTO> usuarios = usuarioService.obtenerTodosLosUsuarios(page);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/getUsuario/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable int id){
        UsuarioDTO usuario = usuarioService.obtenerUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody CrearUsuarioDTO usuario){
        UsuarioDTO user = usuarioService.crearUsuario(usuario);
        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }

    @PostMapping("/updateUsuario/{id}")
    public ResponseEntity<Void> actualizarUsuario(@PathVariable int id, @RequestBody ActualizarUsuarioDTO updates){
        usuarioService.actualizarUsuario(id, updates);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteUsuario/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable int id){
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> contarUsuarios(){
        int cantidad = usuarioService.contarUsuarios();
        return ResponseEntity.ok(cantidad);
    }
    
}
