package com.technovaperu.technovaperuwebstore.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    private ProveedorModel proveedor;

    /**
     * Nombre del producto.
     */
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    /**
     * Descripción del producto.
     */
    @Column(name = "descripcion", nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    /**
     * Precio del producto.
     */
    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    /**
     * Porcentaje de descuento, por defecto 0%.
     */
    @Column(name = "descuento", nullable = false, precision = 5, scale = 2)
    private BigDecimal descuento = BigDecimal.ZERO; 

    /**
     * Cantidad de stock disponible.
     */
    @Column(name = "stock", nullable = false)
    private int stock;

    /**
     * Estado activo del producto, por defecto true.
     */
    @Column(name = "activo", nullable = false)
    private boolean activo = true; 

    /**
     * Fecha de registro del producto, no actualizable.
     */
    @Column(name = "fecha_registro", updatable = false, nullable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    /**
     * Fecha de última actualización del producto.
     */
    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion = LocalDateTime.now();

    /**
     * Relación 1:1 con la imagen de portada.
     */
    @OneToOne(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private ProductoImagenPortadaModel imagenPortada;

    /**
     * Relación 1:N con la galería de imágenes.
     */
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ProductoImagenGaleriaModel> imagenesGaleria = new ArrayList<>();

    /**
     * Relación con categorías del producto.
     */
    @ManyToMany
    @JoinTable(
        name = "producto_categoria", // Nombre de la tabla intermedia
        joinColumns = @JoinColumn(name = "id_producto"),
        inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private Set<CategoriaModel> categorias = new HashSet<>();

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCarritoModel> items = new ArrayList<>();

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComentarioModel> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoritosModel> favoritos = new ArrayList<>();

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedidoModel> detalles = new ArrayList<>();

    // Constructor personalizado
    public ProductoModel(String nombre, String descripcion, BigDecimal precio, BigDecimal descuento, int stock, CategoriaModel categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.descuento = descuento;
        this.stock = stock;
        this.categorias.add(categoria); // Asocia un producto con al menos una categoría
        this.activo = true;
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
