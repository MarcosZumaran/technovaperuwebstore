package com.technovaperu.technovaperuwebstore.model;

import java.time.LocalDateTime;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    private ProveedorModel proveedor;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaModel categorias;

    @ManyToOne
    @JoinColumn(name = "id_subcategoria", nullable = false)
    private SubCategoriaModel subcategorias;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "detalles_producto", columnDefinition = "TEXT")
    private String detalles_producto;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ProductoImagenModel> imagenes;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComentarioModel> comentarios;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoritoModel> favoritos;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoteModel> lotes;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductoPresentacionModel> presentaciones;

    public ProductoModel(ProveedorModel proveedor, CategoriaModel categorias, SubCategoriaModel subcategorias,
            String nombre, String descripcion, String detalles_producto, LocalDateTime fechaRegistro,
            LocalDateTime fechaActualizacion) {
        this.proveedor = proveedor;
        this.categorias = categorias;
        this.subcategorias = subcategorias;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.detalles_producto = detalles_producto;
        this.fechaRegistro = fechaRegistro;
        this.fechaActualizacion = fechaActualizacion;
    }

}
