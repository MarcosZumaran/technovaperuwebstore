package com.technovaperu.technovaperuwebstore.model.dto.create;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CrearCarritoDTO {

    @Positive(message = "El id del usuario no puede ser negativo")
    @NotNull(message = "El id del usuario no puede ser nulo")
    @Schema(description = "Identificador del usuario", example = "1")
    private long idUsuario;

    @NotNull(message = "El estado del carrito no puede estar en blanco")
    @Schema(description = "Estado del carrito", example = "ACTIVO")
    private String estado;

}
