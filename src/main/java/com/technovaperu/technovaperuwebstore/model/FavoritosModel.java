package com.technovaperu.technovaperuwebstore.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "favoritos")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FavoritosModel {
    /**
     * Identificador unico del registro en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Usuario que ha agregado el producto a favoritos.
     */
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioModel usuario;

    /**
     * Producto agregado a favoritos.
     */
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoModel producto;
    
    /**
     * Fecha y hora en que se agreg o el producto a favoritos.
     */
    @Column(name = "fecha_agregado", nullable = false)
    private LocalDateTime fecha_agregado;

    public FavoritosModel(UsuarioModel usuario, ProductoModel producto) {
        this.usuario = usuario;
        this.producto = producto;
    }

    /**
     * Metodo que se ejecuta antes de persistir el objeto en la base de datos.
     * Asigna la fecha actual a la columna fecha_agregado.
     */
    @PrePersist
    public void prePersist() {
        this.fecha_agregado = LocalDateTime.now();
    }
}
