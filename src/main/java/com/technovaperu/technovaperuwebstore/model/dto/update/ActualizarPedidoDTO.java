package com.technovaperu.technovaperuwebstore.model.dto.update;


import io.swagger.v3.oas.annotations.media.Schema;
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
public class ActualizarPedidoDTO {

    @Schema(description = "Estado actual del pedido", example = "ENVIADO")
    private String estado;

    @NotBlank(message = "La direccion de envio no puede estar vacía")
    @Size(max = 255, message = "La direccion de envio no puede exceder 255 caracteres")
    @Schema(description = "Direccion de envio del pedido", example = "Calle 123, Ciudad de Los Santos, San Andreas")
    private String direccionEnvio;
}
