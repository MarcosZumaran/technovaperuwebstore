package com.technovaperu.technovaperuwebstore.model.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CrearProveedorDTO {
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private String pais;
}
