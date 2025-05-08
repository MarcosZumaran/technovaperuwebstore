package com.technovaperu.technovaperuwebstore.model.dto.base;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoritosDTO {
    private int id;

    @NotBlank(message = "El id de usuario no puede estar vacío")
    @Min(value = 1, message = "El id de usuario debe ser mayor que 0")
    private int idUsuario;

    @NotBlank(message = "El id de producto no puede estar vacío")
    @Min(value = 1, message = "El id de producto debe ser mayor que 0")
    private int idProducto;
    
    private LocalDateTime fechaAgregado;
}
