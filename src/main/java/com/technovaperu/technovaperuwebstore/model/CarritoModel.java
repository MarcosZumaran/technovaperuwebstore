package com.technovaperu.technovaperuwebstore.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "carrito")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CarritoModel {
    
    /**
     * Identificador único del carrito, auto-incrementable.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Relación con el usuario que posee el carrito.
     * La columna en la base de datos se llama 'id_cliente'.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioModel usuario;

    /**
     * Fecha de creación del carrito.
     * Se establece automáticamente antes de persistir.
     */
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fecha_creacion;

    /**
     * Lista de items en el carrito.
     * Se carga perezosamente y se elimina en cascada.
     */
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItemCarritoModel> items = new ArrayList<>();

    /**
     * Constructor que inicializa el carrito con un cliente.
     */
    public CarritoModel(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    /**
     * Método que se ejecuta antes de insertar un registro en la base de datos.
     * Establece la fecha de creación a la fecha y hora actual.
     */
    @PrePersist
    public void prePersist() {
        this.fecha_creacion = LocalDateTime.now();
    }

}
