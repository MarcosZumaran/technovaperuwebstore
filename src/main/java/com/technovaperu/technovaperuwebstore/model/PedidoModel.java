package com.technovaperu.technovaperuwebstore.model;

import java.math.BigDecimal;
import java.time.LocalDate;
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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedido")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PedidoModel {

    /**
     * Identificador único del pedido.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
    private int id;

    /**
     * Usuario que realizó el pedido.
     */
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioModel cliente;

    /**
     * Fecha en la que se realizó el pedido.
     */
    @Column(name = "fecha_pedido", nullable = false)
    private LocalDate fecha_pedido;

    /**
     * Estado actual del pedido.
     */
    @Column(name = "estado", nullable = false)
    private EstadoPedido estado = EstadoPedido.PENDIENTE;

    /**
     * Total del pedido.
     */
    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    /**
     * Dirección de envío del pedido.
     */
    @Column(name = "direccion_envio", nullable = false , length = 255)
    private String direccion_envio;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetallePedidoModel> detalles = new ArrayList<>();

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HistorialPedidosModel> historiales = new ArrayList<>();

    public PedidoModel(UsuarioModel cliente, EstadoPedido estado, BigDecimal total, String direccion_envio) {
        this.cliente = cliente;
        this.estado = estado;
        this.total = total;
        this.direccion_envio = direccion_envio;
    }

    /**
     * Método que se ejecuta antes de persistir el objeto en la base de datos.
     * Establece la fecha del pedido a la fecha actual.
     */
    @PrePersist
    public void prePersist() {
        // Establece la fecha del pedido a la fecha actual
        this.fecha_pedido = LocalDate.now();
    }

    public enum EstadoPedido {
        PENDIENTE,
        ENVIADO,
        ENTREGADO,
        CANCELADO
    }
}
