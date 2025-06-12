package com.technovaperu.technovaperuwebstore.model;

import java.time.LocalDateTime;

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
@Table(name = "comentario")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ComentarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioModel usuario;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false) // Relación con el producto
    private ProductoModel producto;

    @Column(name = "texto", nullable = false, columnDefinition = "TEXT") // Columna de texto
    private String texto;

    @Column(name = "calificacion", nullable = false) // Columna de calificación
    private int calificacion;

    @Column(name = "fecha_creacion", nullable = false) // Columna de fecha de creación
    private LocalDateTime fecha_creacion;

    @Column(name = "fecha_modificacion", nullable = false) // Columna de fecha de modificación
    private LocalDateTime fecha_modificacion;

    public ComentarioModel(UsuarioModel usuario, ProductoModel producto, String texto, int calificacion) {
        this.usuario = usuario;
        this.producto = producto;
        this.texto = texto;
        this.calificacion = calificacion;
    }

}
