package com.technovaperu.technovaperuwebstore.model.dto.create;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
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
    @Min(value = 1, message = "El ID de usuario debe ser mayor que 0")
    private int idUsuario;
    
    private LocalDateTime fechaCreacion;
}