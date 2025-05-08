package com.technovaperu.technovaperuwebstore.model.dto.base;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarritoDTO {

    @Schema(description = "ID único del carrito", example = "1")
    private int id;

    @NotNull(message = "El id de usuario no puede estar vacío")
    @Min(value = 1, message = "El id de usuario debe ser mayor que 0")
    private int idUsuario;

    private LocalDateTime fechaCreacion;
}