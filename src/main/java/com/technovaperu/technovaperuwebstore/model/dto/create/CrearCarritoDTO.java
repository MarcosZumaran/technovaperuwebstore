package com.technovaperu.technovaperuwebstore.model.dto.create;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CrearCarritoDTO {

    @NotNull(message = "El ID de usuario no puede ser nulo")
    @Positive(message = "El ID de usuario debe ser mayor que 0")
    @Schema(description = "ID del usuario al que pertenece el carrito", example = "1")
    private int idUsuario;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Fecha y hora de creación del carrito", example = "2023-01-01 10:00:00")
    private LocalDateTime fechaCreacion;
}