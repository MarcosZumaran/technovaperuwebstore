package com.technovaperu.technovaperuwebstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technovaperu.technovaperuwebstore.model.dto.base.ComentarioDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearComentarioDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarComentarioDTO;
import com.technovaperu.technovaperuwebstore.services.ComentarioService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/comentario")
public class ComentarioRestController {
    
    private final ComentarioService comentarioService;

    @Autowired
    public ComentarioRestController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @GetMapping("/getComentariosByProduct/{id}")
    public ResponseEntity<List<ComentarioDTO>> obtenerComentariosPorProducto(int id) {
        List<ComentarioDTO> comentarios = comentarioService.obtenerComentariosPorProducto(id, 1);
        return ResponseEntity.ok(comentarios);
    }

    @GetMapping("/getComentario/{id}")
    public ResponseEntity<ComentarioDTO> obtenerComentarioPorId(int id) {
        ComentarioDTO comentario = comentarioService.obtenerComentarioPorId(id);
        return ResponseEntity.ok(comentario);
    }

    @PostMapping
    public ResponseEntity<ComentarioDTO> crearComentario(@Valid @RequestBody CrearComentarioDTO comentario) {
        ComentarioDTO comentarioCreado = comentarioService.crearComentario(comentario);
        return new ResponseEntity<>(comentarioCreado, HttpStatus.CREATED);
    }

    @PostMapping("/updateComentario/{id}")
    public ResponseEntity<Void> actualizarComentario(int id, @RequestBody ActualizarComentarioDTO comentario) {
        comentarioService.actualizarComentario(id, comentario);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/deleteComentario/{id}")
    public ResponseEntity<Void> eliminarComentario(int id) {
        comentarioService.eliminarComentario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/countComentariosByProduct/{id}")
    public ResponseEntity<Integer> contarComentariosPorProducto(int id) {
        int count = comentarioService.contarComentariosDeProducto(id);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/countComentarios")
    public ResponseEntity<Integer> contarComentarios() {
        int count = comentarioService.contarComentarios();
        return ResponseEntity.ok(count);
    }

}
