package com.technovaperu.technovaperuwebstore.model;

import java.math.BigDecimal;

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
@Table(name = "detalle_pedido")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DetallePedidoModel {
    /**
     * Identificador numérico único del detalle de pedido.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
    private int id;

    /**
     * Pedido al que pertenece este detalle de pedido.
     */
    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private PedidoModel pedido;

    /**
     * Producto que se esta vendiendo.
     */
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoModel producto;

    /**
     * Cantidad de productos vendidos.
     */
    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    /**
     * Precio unitario del producto.
     */
    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio_unitario;
    
    public DetallePedidoModel(PedidoModel pedido, ProductoModel producto, int cantidad, BigDecimal precio_unitario) {
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
    }
    
}
