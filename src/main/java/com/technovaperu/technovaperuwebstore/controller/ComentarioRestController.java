package com.technovaperu.technovaperuwebstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technovaperu.technovaperuwebstore.model.dto.base.ComentarioDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearComentarioDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarComentarioDTO;
import com.technovaperu.technovaperuwebstore.model.response.ApiResponse;
import com.technovaperu.technovaperuwebstore.services.ComentarioService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/comentario")
public class ComentarioRestController {
    
    private final ComentarioService comentarioService;

    @Autowired
    public ComentarioRestController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<ApiResponse<List<ComentarioDTO>>> obtenerComentariosPorProducto(int id) {
        List<ComentarioDTO> comentarios = comentarioService.obtenerComentariosPorProducto(id, 1);
        return ResponseEntity.ok(ApiResponse.success(comentarios, "Comentarios obtenidos con éxito"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ComentarioDTO>> obtenerComentarioPorId(int id) {
        ComentarioDTO comentario = comentarioService.obtenerComentarioPorId(id);
        return ResponseEntity.ok(ApiResponse.success(comentario, "Comentario obtenido con éxito"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ComentarioDTO>> crearComentario(@Valid @RequestBody CrearComentarioDTO comentario) {
        ComentarioDTO comentarioCreado = comentarioService.crearComentario(comentario);
        return new ResponseEntity<>(
            ApiResponse.success(comentarioCreado, "Comentario creado con éxito"),
            HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> actualizarComentario(int id, @RequestBody ActualizarComentarioDTO comentario) {
        comentarioService.actualizarComentario(id, comentario);
        return ResponseEntity.ok(ApiResponse.success(null, "Comentario actualizado con éxito"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarComentario(int id) {
        comentarioService.eliminarComentario(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Comentario eliminado con éxito"));
    }

    @GetMapping("/count/producto/{id}")
    public ResponseEntity<ApiResponse<Integer>> contarComentariosPorProducto(int id) {
        int count = comentarioService.contarComentariosDeProducto(id);
        return ResponseEntity.ok(ApiResponse.success(count, "Total de comentarios obtenido con éxito"));
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Integer>> contarComentarios() {
        int count = comentarioService.contarComentarios();
        return ResponseEntity.ok(ApiResponse.success(count, "Total de comentarios obtenido con éxito"));
    }

}
