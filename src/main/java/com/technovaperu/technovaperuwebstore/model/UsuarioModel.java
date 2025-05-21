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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
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
     * 
     * @param nombre
     */
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    /**
     * Apellido del usuario
     * 
     * @param apellido
     */
    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    /**
     * Email del usuario, debe ser único y no nulo
     * 
     * Este campo almacena la dirección de correo electrónico del usuario
     * y se valida para asegurar que el formato sea correcto.
     * 
     * @param email dirección de correo electrónico del usuario
     */
    @Column(name = "email", unique = true, nullable = false, length = 255)
    @Email(message = "El email debe ser válido")
    private String email;

    /**
     * Contraseña del usuario
     * 
     * @param password
     */
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    /**
     * Dirección del usuario
     * 
     * @param direccion
     */
    @Column(name = "direccion", nullable = false, length = 255)
    private String direccion;

    /**
     * Teléfono del usuario
     * 
     * @param telefono
     */
    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;

    /**
     * Fecha de creación del usuario.
     * Este campo es asignado automáticamente al persistir el objeto.
     */
    @Column(name = "fecha_registro", updatable = false, nullable = false)
    private LocalDateTime fechaRegistro;

    /**
     * Fecha de la última modificación del usuario.
     * Se actualiza automáticamente al modificar el objeto.
     */
    @Column(name = "fecha_modificacion", updatable = true, nullable = false)
    private LocalDateTime fechaModificacion;

    /**
     * Relación uno a uno con el carrito de compras del usuario.
     * Cada usuario tiene un carrito único.
     */
    @OneToOne(mappedBy = "usuario")
    private CarritoModel carrito;

    /**
     * Lista de pedidos realizados por el usuario.
     * Relación uno a muchos entre usuario y pedidos.
     */
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PedidoModel> pedidos = new ArrayList<>();

    /**
     * Comentarios realizados por el usuario en distintos productos.
     * Relación uno a muchos entre usuario y comentarios.
     */
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ComentarioModel> comentarios = new ArrayList<>();

    /**
     * Productos marcados como favoritos por el usuario.
     * Relación uno a muchos entre usuario y favoritos.
     */
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FavoritoModel> favoritos = new ArrayList<>();

    public UsuarioModel(String nombre, String apellido, String email, String password, String direccion,
            String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.direccion = direccion;
        this.telefono = telefono;
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

}
