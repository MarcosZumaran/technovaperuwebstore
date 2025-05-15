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
@Table(name = "unidad_medidas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UnidadMedidaModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera un valor único para el ID
    int id;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false) // Relación con la tabla de productos
    ProductoModel producto;

    @Column(name = "unidad_medida", nullable = false) // Columna para la unidad de medida
    String unidad_medida;

    @Column(name = "precio", nullable = false) // Columna para el precio
    BigDecimal precio;

    /**
     * Constructor para inicializar el modelo de UnidadMedida.
     *
     * @param producto El producto asociado a la unidad de medida
     * @param unidad_medida La unidad de medida
     * @param precio El precio de la unidad de medida
     */
    public UnidadMedidaModel(ProductoModel producto, String unidad_medida, BigDecimal precio) {
        this.producto = producto;
        this.unidad_medida = unidad_medida;
        this.precio = precio;
    }

}
