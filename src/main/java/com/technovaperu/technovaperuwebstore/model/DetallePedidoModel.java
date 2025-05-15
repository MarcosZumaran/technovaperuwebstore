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
@Table(name = "detalle_pedidos")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DetallePedidoModel {
    /**
     * Identificador numérico único del detalle de pedido.
     * 
     * Este campo es auto-incrementable y se utiliza como clave
     * primaria para el detalle de pedido.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
    private int id;

    /**
     * Pedido al que pertenece este detalle de pedido.
     * 
     * Este campo se relaciona con la tabla "pedido" y se utiliza para
     * obtener el pedido al que se le ha agregado este detalle de pedido.
     */
    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private PedidoModel pedido;

    /**
     * Producto que se esta vendiendo.
     * 
     * Este campo se relaciona con la tabla "producto" y se utiliza para
     * obtener el producto que se esta vendiendo en este detalle de pedido.
     */
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoModel producto;

    /**
     * Cantidad de productos vendidos.
     * 
     * Este campo debe ser mayor a cero y se utiliza para calcular el
     * subtotal del pedido.
     */
    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    /**
     * Precio unitario del producto.
     * 
     * Este campo debe ser mayor a cero y se utiliza para calcular el
     * subtotal del pedido.
     * 
     * El precio unitario debe ser un valor numérico con dos decimales,
     * por ejemplo 10.99.
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
