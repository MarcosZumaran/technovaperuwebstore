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
    @OneToOne(mappedBy = "carrito")
    private UsuarioModel cliente;

    /**
     * Fecha de creación del carrito.
     */
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fecha_creacion;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItemCarritoModel> items = new ArrayList<>();

    public CarritoModel(UsuarioModel cliente) {
        this.cliente = cliente;
    }

    @PrePersist // Método que se ejecuta al insertar un registro en la base de datos
    public void prePersist() {
        this.fecha_creacion = LocalDateTime.now();
    }

}
