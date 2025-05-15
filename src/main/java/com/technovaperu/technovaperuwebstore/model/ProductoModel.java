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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoModel {

    /**
     * Identificador único del producto.
     * Es un auto-incremental que se va a ir incrementando cada vez que se
     * cree un nuevo producto.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
    private int id;

    /**
     * El proveedor que suministra el producto.
     */
    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    private ProveedorModel proveedor;

    /**
     * Las categorías que se relacionan con el producto.
     */
    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaModel categorias;

    /**
     * El nombre del producto.
     */
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    /**
     * La descripción del producto, que puede tener un
     * largo indefinido.
     */
    @Column(name = "descripcion", nullable = false, columnDefinition = "TEXT")
    private String descripcion;
    /**
     * La cantidad de stock disponible del producto.
     */
    @Column(name = "stock", nullable = false)
    private int stock;

    /**
     * La fecha de registro del producto, que no se puede actualizar.
     */
    @Column(name = "fecha_registro", updatable = false, nullable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    /**
     * La fecha de última actualización del producto.
     */
    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion = LocalDateTime.now();

    /**
     * La galería de imágenes del producto, que se va a mostrar en la vista de
     * detalles del producto.
     */
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ProductolImagenModel> imagenes = new ArrayList<>();

    /**
     * Los items del carrito que se relacionan con el producto.
     */
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCarritoModel> items = new ArrayList<>();

    /**
     * Los comentarios que se relacionan con el producto.
     */
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComentarioModel> comentarios = new ArrayList<>();

    /**
     * Los favoritos que se relacionan con el producto.
     */
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoritoModel> favoritos = new ArrayList<>();

    /**
     * Los detalles de pedidos que se relacionan con el producto.
     */
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedidoModel> detalles = new ArrayList<>();

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoteModel> lotes = new ArrayList<>();

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UnidadMedidaModel> unidades = new ArrayList<>();

    // Constructor personalizado
    public ProductoModel(ProveedorModel proveedor, CategoriaModel categorias, String nombre, String descripcion, int stock) {
        this.proveedor = proveedor;
        this.categorias = categorias;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
    }

    /**
     * Método que se ejecuta antes de persistir el objeto en la base de datos.
     * Asigna la fecha actual a las columnas 'fecha_registro' y 'fecha_actualizacion'.
     */
    @PrePersist
    public void prePersist() {
        // Asigna la fecha actual a la columna 'fecha_registro'
        this.fechaRegistro = LocalDateTime.now();
        // Asigna la fecha actual a la columna 'fecha_actualizacion'
        this.fechaActualizacion = LocalDateTime.now();
    }

    /**
     * Método que se ejecuta antes de actualizar un registro en la base de datos.
     * Asigna la fecha actual a la columna 'fecha_actualizacion'.
     */
    @PreUpdate
    public void actualizarFecha() {
        // Asigna la fecha actual a la columna 'fecha_actualizacion'
        this.fechaActualizacion = LocalDateTime.now();
    }

}
