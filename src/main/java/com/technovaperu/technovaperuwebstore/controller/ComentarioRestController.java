package com.technovaperu.technovaperuwebstore.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.technovaperu.technovaperuwebstore.services.ComentarioService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/comentario")
public class ComentarioRestController {
    
    @Autowired
    private ComentarioService comentarioService;

    @GetMapping("/getComentariosByProduct/{id}")
    public List<Map<String, Object>> getComentariosByProduct(int id) {
        return comentarioService.obtenerComentariosPorProducto(id, 1);
    }

    @GetMapping("/getComentario/{id}")
    public Map<String, Object> getComentario(int id) {
        return comentarioService.obtenerComentarioPorId(id);
    }

    @PostMapping
    public String create(@RequestBody Map<String, Object> comentario) {
        return comentarioService.crearComentario(comentario);
    }

    @PostMapping("/updateComentario/{id}")
    public String update(int id, @RequestBody Map<String, Object> comentario) {
        return comentarioService.actualizarComentario(id, comentario);
    }

    @GetMapping("/deleteComentario/{id}")
    public String delete(int id) {
        return comentarioService.eliminarComentario(id);
    }

    @GetMapping("/countComentariosByProduct/{id}")
    public int countComentariosByProduct(int id) {
        return comentarioService.contarComentariosDeProducto(id);
    }

    @GetMapping("/countComentarios")
    public int count() {
        return comentarioService.contarComentarios();
    }

}
