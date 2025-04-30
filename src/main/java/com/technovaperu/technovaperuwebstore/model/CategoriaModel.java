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

    /**
     * Constructor que recibe el nombre y la descripción de la categoría.
     * Asocia un producto con la categoría.
     */
    public CategoriaModel(String nombre, String descripcion, ProductoModel producto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.productos.add(producto); // Asocia un producto con la categoría
    }

    // Relación ManyToMany con ProductoModel
    @ManyToMany(mappedBy = "categorias")
    private Set<ProductoModel> productos = new HashSet<>(); // Relación con productos

    /**
     * Constructor que recibe el nombre y la descripción de la categoría.
     * No asocia un producto con la categoría.
     */
    public CategoriaModel(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
