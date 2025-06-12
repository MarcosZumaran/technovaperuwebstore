package com.technovaperu.technovaperuwebstore.model;

import java.time.LocalDateTime;

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
@Table(name = "favorito")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FavoritoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioModel usuario;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoModel producto;
    
    @Column(name = "fecha_agregado", nullable = false)
    private LocalDateTime fecha_agregado;

    public FavoritoModel(UsuarioModel usuario, ProductoModel producto) {
        this.usuario = usuario;
        this.producto = producto;
    }

}
