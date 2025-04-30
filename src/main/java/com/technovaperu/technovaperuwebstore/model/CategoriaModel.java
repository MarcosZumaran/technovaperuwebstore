package com.technovaperu.technovaperuwebstore.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categoria")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaModel {

    /**
     * Identificador único de la categoría.
     */
    @Id // Identificador único
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
    private int id;

    /**
     * Nombre de la categoría.
     */
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    /**
     * Descripción de la categoría.
     */
    @Column(name = "descripcion", nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    public CategoriaModel(String nombre, String descripcion, ProductoModel producto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.productos.add(producto); // Asocia un producto con la categoría
    }

    // Relación ManyToMany con ProductoModel
    @ManyToMany(mappedBy = "categorias")
    private Set<ProductoModel> productos = new HashSet<>(); // Relación con productos

    // Constructor personalizado para facilitar la creación de instancias sin productos asociados
    public CategoriaModel(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
