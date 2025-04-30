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
@Table(name = "item_carrito")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ItemCarritoModel {

    /**
     * Identificador numero del item del carrito. Es un numero unico que se
     * incrementa en cada nuevo item del carrito.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
    private int id;

    /**
     * El carrito al que se asocia este item. Un item del carrito se asocia
     * con un solo carrito.
     */
    @ManyToOne
    @JoinColumn(name = "id_carrito", nullable = false)
    private CarritoModel carrito;

    /**
     * El producto asociado a este item del carrito. Un item del carrito se
     * asocia con un solo producto.
     */
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoModel producto;

    /**
     * La cantidad de este item del carrito. La cantidad puede ser 1 o mas.
     */
    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    /**
     * La fecha en que se agreg el item al carrito. La fecha se establece
     * automaticamente en el momento en que se crea el item del carrito.
     */
    @Column(name = "fecha_agregado", nullable = false)
    private LocalDateTime fecha_agregado;


    public ItemCarritoModel(CarritoModel carrito, ProductoModel producto, int cantidad) {
        this.carrito = carrito;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    /**
     * Method executed before persisting the object to the database.
     * Sets the 'fecha_agregado' field to the current date and time.
     */
    @PrePersist
    public void prePersist() {
        this.fecha_agregado = LocalDateTime.now();
    }

}
