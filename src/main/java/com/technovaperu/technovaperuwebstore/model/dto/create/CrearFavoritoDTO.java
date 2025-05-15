package com.technovaperu.technovaperuwebstore.model.dto.create;

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
public class CrearFavoritoDTO {

    @NotNull(message = "El id de usuario no puede estar vacío")
    @Positive(message = "El id de usuario debe ser positivo")
    @Schema(description = "ID del usuario al que pertenece el favorito", example = "1")
    private int idUsuario;

    @NotNull(message = "El id de producto no puede estar vacío")
    @Positive(message = "El id de producto debe ser mayor que 0")
    @Schema(description = "ID del producto al que pertenece el favorito", example = "1")
    private int idProducto;
}

