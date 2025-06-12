package com.technovaperu.technovaperuwebstore.model;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Table(name = "producto_imagen")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductoImagenModel {
    
    // Atributos de la tabla producto_imagen
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // Foreign key
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private ProductoModel producto;

    @Column(name = "url", length = 255)
    private String url;

    @Column(name = "tipo")
    @Schema(description = "Tipo de imagen del producto, puede ser PORTADA o GALERIA")
    private String tipo;

    @Column(name =  "fecha_creacion")
    @Schema(description = "Fecha de creación de la imagen del producto")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    @Schema(description = "Fecha de actualización de la imagen del producto")
    private LocalDateTime fechaActualizacion;

    // Constructor personalizado para la creación de objetos de la tabla producto_imagen

    public ProductoImagenModel(ProductoModel producto, String url, String tipo) {
        this.producto = producto;
        this.url = url;
        this.tipo = tipo;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }
    
}
