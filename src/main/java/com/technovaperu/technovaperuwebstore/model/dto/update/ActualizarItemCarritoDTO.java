package com.technovaperu.technovaperuwebstore.model.dto.update;

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
public class ActualizarItemCarritoDTO {

    @NotNull(message = "la cantidad no puede estar vacía")
    @Positive(message = "la cantidad debe ser mayor o igual que 0")
    @Schema(description = "ID del carrito al que pertenece el item", example = "1")
    private int cantidad;
}
