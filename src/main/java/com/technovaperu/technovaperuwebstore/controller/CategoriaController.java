        package com.technovaperu.technovaperuwebstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.technovaperu.technovaperuwebstore.model.dto.base.CategoriaDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearCategoriaDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarCategoriaDTO;
import com.technovaperu.technovaperuwebstore.model.response.ApiResponse;
import com.technovaperu.technovaperuwebstore.services.CategoriaService;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categoria")
@CrossOrigin(origins = "*")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired
    // Constructor injection for CategoriaService
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoriaDTO>>> obtenerTodasLasCategorias() {
        List<CategoriaDTO> categorias = categoriaService.obtenerTodasLasCategorias();
        return ResponseEntity.ok(ApiResponse.success(categorias, "Categorias obtenidas con éxito"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoriaDTO>> obtenerCategoriaPorId(@PathVariable int id) {
        CategoriaDTO categoria = categoriaService.obtenerCategoriaPorId(id);
        return ResponseEntity.ok(ApiResponse.success(categoria, "Categoria obtenida con éxito"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoriaDTO>> crearCategoria(@Valid @RequestBody CrearCategoriaDTO categoriaDTO) {
        CategoriaDTO categoriaCreada = categoriaService.crearCategoria(categoriaDTO);
        return new ResponseEntity<>(
            ApiResponse.success(categoriaCreada, "Categoria creada con éxito"),
            HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> actualizarCategoria(
            @PathVariable int id,
            @Valid @RequestBody ActualizarCategoriaDTO categoriaDTO) {
        categoriaService.actualizarCategoria(id, categoriaDTO);
        return ResponseEntity.ok(ApiResponse.success(null, "Categoria actualizada con éxito"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarCategoria(@PathVariable int id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Categoria eliminada con éxito"));
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Integer>> contarCategorias() {
        int count = categoriaService.contarCategorias();
        return ResponseEntity.ok(ApiResponse.success(count, "Total de categorias obtenido con éxito"));
    }
    
}