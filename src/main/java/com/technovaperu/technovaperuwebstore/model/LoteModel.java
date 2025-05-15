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
@Table(name = "lotes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoteModel {

    /**
     * Identificador unico de la instancia.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Producto al que se asocia el lote.
     */
    @ManyToOne
    @JoinColumn(name =  "id_producto", nullable = false)
    private ProductoModel producto;

    /**
     * Unidad de medida del lote (pieza, kilogramo, etc.).
     */
    @Column(name = "unidad_medida", nullable = false)
    private String unidad_medida;

    /**
     * Costo del lote.
     */
    @Column(name = "costo", nullable = false)
    private BigDecimal costo;

    /**
     * Precio del lote.
     */
    @Column(name = "precio", nullable = false)
    private BigDecimal precio;

    /**
     * Cantidad de items o unidades del lote.
     */
    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    /**
     * Constructor de la clase que inicializa los campos.
     * @param producto Producto al que se asocia el lote.
     * @param unidad_medida Unidad de medida del lote (pieza, kilogramo, etc.).
     * @param costo Costo del lote.
     * @param precio Precio del lote.
     * @param cantidad Cantidad de items o unidades del lote.
     */
    public LoteModel(ProductoModel producto, String unidad_medida, BigDecimal costo, BigDecimal precio, int cantidad) {
        this.producto = producto;
        this.unidad_medida = unidad_medida;
        this.costo = costo;
        this.precio = precio;
        this.cantidad = cantidad;
    }

}
