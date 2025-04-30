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
@Table(name = "historial_pedidos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistorialPedidosModel {
    /**
     * Identificador unico del historial de pedidos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
    private int id;

    /**
     * Pedido al que se refiere este registro de historial.
     */
    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private PedidoModel pedido;

    /**
     * Estado en que se encuentra el pedido.
     */
    @Column(name = "estado", nullable = false)
    private EstadoHistorialPedidos estado;

    /**
     * Fecha y hora en que se cambia el estado del pedido.
     */
    @Column(name = "fecha_estado", nullable = false)
    private LocalDateTime fecha_estado;

    public HistorialPedidosModel(PedidoModel pedido, EstadoHistorialPedidos estado) {
        this.pedido = pedido;
        this.estado = estado;
    }

    /**
     * Este m todo se ejecuta antes de persistir el objeto en la base de datos.
     * Establece la fecha de estado a la fecha y hora actual.
     */
    @PrePersist
    public void prePersist() {
        // Establece la fecha de estado a la fecha y hora actual
        this.fecha_estado = LocalDateTime.now();
    }

    /**
     * Enumerado que representa los posibles estados de un pedido.
     * <ul>
     * <li>{@link #PENDIENTE}: El pedido est  pendiente de ser preparado.</li>
     * <li>{@link #ENVIADO}: El pedido ha sido enviado al cliente.</li>
     * <li>{@link #ENTREGADO}: El pedido ha sido entregado al cliente.</li>
     * <li>{@link #CANCELADO}: El pedido ha sido cancelado.</li>
     * </ul>
     */
    public enum EstadoHistorialPedidos {
        /**
         * El pedido est  pendiente de ser preparado.
         */
        PENDIENTE,

        /**
         * El pedido ha sido enviado al cliente.
         */
        ENVIADO,

        /**
         * El pedido ha sido entregado al cliente.
         */
        ENTREGADO,

        /**
         * El pedido ha sido cancelado.
         */
        CANCELADO
    }
}
