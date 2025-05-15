package com.technovaperu.technovaperuwebstore.model;

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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proveedores")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProveedorModel {

    /**
     * Identificador único del proveedor.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    /**
     * Nombre del proveedor.
     */
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    /**
     * Dirección del proveedor.
     */
    @Column(name = "direccion", nullable = false, length = 255)
    private String direccion;
    
    /**
     * Teléfono del proveedor.
     */
    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;
    
    /**
     * Correo electrónico del proveedor.
     */
    @Column(name = "email", nullable = false, length = 255)
    private String email;
    
    /**
     * País del proveedor.
     */
    @Column(name = "pais", nullable = false, length = 255)
    private String pais;

    /**
     * Lista de productos asociados con el proveedor.
     */
    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductoModel> productos = new ArrayList<>();

    public ProveedorModel(String nombre, String direccion, String telefono, String email, String pais) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.pais = pais;
    }
}
