package com.technovaperu.technovaperuwebstore.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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

    /**
     * Identificador único del comentario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
    private int id;

    /**
     * Relación con el usuario que realizó el comentario.
     */
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioModel usuario;

    /**
     * Relación con el producto sobre el que se realizó el comentario.
     */
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false) // Relación con el producto
    private ProductoModel producto;

    /**
     * Contenido del comentario.
     */
    @Column(name = "texto", nullable = false, columnDefinition = "TEXT") // Columna de texto
    private String texto;

    /**
     * Calificación del comentario. Puede ser positiva o negativa.
     */
    @Column(name = "calificacion", nullable = false) // Columna de calificación
    private int calificacion;

    /**
     * Estado del comentario. Puede ser VISIBLE, OCULTO o BORRADO.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false) // Columna de estado
    private EstadoComentario estado = EstadoComentario.VISIBLE;

    /**
     * Fecha y hora en que se creó el comentario.
     */
    @Column(name = "fecha_creacion", nullable = false) // Columna de fecha de creación
    private LocalDateTime fecha_creacion;

    /**
     * Fecha y hora en que se modificó el comentario por última vez.
     */
    @Column(name = "fecha_modificacion", nullable = false) // Columna de fecha de modificación
    private LocalDateTime fecha_modificacion;

    public ComentarioModel(UsuarioModel usuario, ProductoModel producto, String texto, int calificacion) {
        this.usuario = usuario;
        this.producto = producto;
        this.texto = texto;
        this.calificacion = calificacion;
    }

    /**
     * Método que se ejecuta antes de insertar un registro en la base de datos.
     * Establece la fecha de creación y la fecha de modificación a la fecha y hora actual.
     */
    @PrePersist
    public void prePersist() {
        // Establece la fecha de creación a la fecha y hora actual
        this.fecha_creacion = LocalDateTime.now();
        
        // Establece la fecha de modificación a la fecha y hora actual
        this.fecha_modificacion = LocalDateTime.now();
    }

    /**
     * Método que se ejecuta antes de actualizar un registro en la base de datos.
     * Establece la fecha de modificación a la fecha y hora actual.
     */
    @PreUpdate
    public void preUpdate() {
        // Establece la fecha de modificación a la fecha y hora actual
        this.fecha_modificacion = LocalDateTime.now();
    }

    /**
     * Enumerado que representa el estado de un comentario.
     * <ul>
     *     <li>{@link #VISIBLE}: El comentario es visible.</li>
     *     <li>{@link #HIDDEN}: El comentario es invisible.</li>
     * </ul>
     */
    public enum EstadoComentario {
        /**
         * El comentario es visible.
         */
        VISIBLE,

        /**
         * El comentario es invisible.
         */
        OCULTO
    }
}
