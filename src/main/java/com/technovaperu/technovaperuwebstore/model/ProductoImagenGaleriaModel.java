package com.technovaperu.technovaperuwebstore.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto_imagen_galeria")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoImagenGaleriaModel {

    /**
     * Identificador único para cada imagen en la galería.
     * Generado automáticamente como Auto Increment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Relación Many-to-One con el modelo de Producto.
     * Asocia una imagen con un producto específico.
     */
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoModel producto;

    /**
     * URL de la imagen en la galería.
     * No puede ser nulo y tiene una longitud máxima de 255 caracteres.
     */
    @Column(name = "url", nullable = false, length = 255)
    private String url;

    public ProductoImagenGaleriaModel(ProductoModel producto, String url) {
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
