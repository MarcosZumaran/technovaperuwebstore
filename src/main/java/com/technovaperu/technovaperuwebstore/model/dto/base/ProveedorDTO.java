package com.technovaperu.technovaperuwebstore.model.dto.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProveedorDTO {
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private String pais;
}
