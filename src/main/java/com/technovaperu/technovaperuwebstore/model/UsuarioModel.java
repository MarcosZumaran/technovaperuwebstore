package com.technovaperu.technovaperuwebstore.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioModel {

    // Atributos de la clase
    @Id // Identificador único
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
    private int id;

    // Atributos de la clase
    /**
     * Nombre del usuario
     */
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    /**
     * Apellido del usuario
     */
    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    /**
     * Email del usuario
     */
    @Column(name = "email", unique = true, nullable = false, length = 255)
    private String email;

    /**
     * Contraseña del usuario
     */
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    /**
     * Dirección del usuario
     */
    @Column(name = "direccion", nullable = false, length = 255)
    private String direccion;

    /**
     * Teléfono del usuario
     */
    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;

    /**
     * Rol del usuario
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private Rol rol = Rol.CLIENTE;

    /**
     * Ssi esta o no activo el usuario
     */
    @Column(name = "activo", nullable = false)
    private boolean activo;

    /**
     * Fecha de creación
     */
    @Column(name = "fecha_registro", updatable = false, nullable = false)
    private LocalDateTime fechaRegistro;

    /**
     * Fecha de modificación
     */
    @Column(name = "fecha_modificacion", updatable = true, nullable = false)
    private LocalDateTime fechaModificacion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_carrito", nullable = false, referencedColumnName = "id")
    private CarritoModel carrito;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PedidoModel> pedidos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ComentarioModel> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FavoritosModel> favoritos = new ArrayList<>();

    public UsuarioModel(String nombre, String apellido, String email, String password, String direccion,
            String telefono, Rol rol, boolean activo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.direccion = direccion;
        this.telefono = telefono;
        this.rol = rol;
        this.activo = activo;
    }

    /**
     * Método que se ejecuta antes de persistir el objeto en la base de datos.
     * Asigna la fecha actual a las columnas 'fecha_registro' y 'fecha_actualizacion'.
     */
    @PrePersist // Método que se ejecuta al insertar un registro en la base de datos
    public void prePersist() {
        // Asigna la fecha actual a la columna 'fecha_registro'
        this.fechaRegistro = LocalDateTime.now();
        // Asigna la fecha actual a la columna 'fecha_actualizacion'
        this.fechaModificacion = LocalDateTime.now();
    }

    /**
     * Método que se ejecuta antes de actualizar un registro en la base de datos.
     * Asigna la fecha actual a la columna 'fecha_modificacion'.
     */
    @PreUpdate // Método que se ejecuta al actualizar un registro en la base de datos
    public void preUpdate() {
        this.fechaModificacion = LocalDateTime.now(); // Fecha de modificación
    }

    // Enumeraciones
    public enum Rol {
        ADMIN,
        CLIENTE
    }
}
