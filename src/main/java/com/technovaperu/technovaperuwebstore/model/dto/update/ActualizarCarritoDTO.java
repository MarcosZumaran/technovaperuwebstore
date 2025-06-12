package com.technovaperu.technovaperuwebstore.model.dto.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ActualizarCarritoDTO {

    @Schema(description = "Estado del carrito", example = "ACTIVO")
    private String estado;

}
