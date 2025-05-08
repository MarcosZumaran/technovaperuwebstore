package com.technovaperu.technovaperuwebstore.model.dto.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CrearPedidoDTO {

    @NotBlank(message = "El id de usuario no puede estar vacío")
    @Min(value = 1, message = "El id de usuario debe ser mayor que 0")
    private int idUsuario;

    @NotBlank(message = "La direccion de envio no puede estar vacía")
    @Size(max = 255, message = "La direccion de envio no puede exceder 255 caracteres")
    private String direccionEnvio;
}
