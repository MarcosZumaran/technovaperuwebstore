package com.technovaperu.technovaperuwebstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto_imagen_portada")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoImagenPortadaModel {

    /**
     * Identificador único del objeto.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
    private int id;

    /**
     * El producto al que se le asocia esta imagen de portada.
     */
    @OneToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoModel producto;

    /**
     * La URL de la imagen de portada.
     */
    @Column(name = "url", nullable = false, length = 255)
    private String url;

    public ProductoImagenPortadaModel(ProductoModel producto, String url) {
        this.producto = producto;
        this.url = url;
    }

    public void procesarUrl(String url) {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("La URL no puede estar vacía.");
        }
        this.url = url;
    }
}
