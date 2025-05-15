package com.technovaperu.technovaperuwebstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "producto_imagen")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductolImagenModel {
    
    /**
     * Identificador único del registro.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Referencia al producto al que pertenece la imagen.
     */
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoModel producto;

    /**
     * URL de la imagen.
     */
    @Column(name = "url", nullable = false, length = 255)
    private String url;

    /**
     * Tipo de imagen. Puede ser PORTADA o GALERIA.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private Tipo tipo;

    /**
     * Constructor para crear una instancia de {@link ProductolImagen}.
     * 
     * @param producto Referencia al producto al que pertenece la imagen.
     * @param url      URL de la imagen.
     * @param tipo     Tipo de imagen.
     */
    public ProductolImagenModel(ProductoModel producto, String url, Tipo tipo) {
        this.producto = producto;
        this.url = url;
        this.tipo = tipo;
    }

    /**
     * Enumerado que indica el tipo de imagen.
     */
    public enum Tipo {
        /**
         * Imagen de portada del producto.
         */
        PORTADA("PORTADA"),

        /**
         * Imagen de la galeria del producto.
         */
        GALERIA("GALERIA");

        private final String value;

        Tipo(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}
