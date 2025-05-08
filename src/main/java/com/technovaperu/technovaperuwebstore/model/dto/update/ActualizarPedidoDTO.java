package com.technovaperu.technovaperuwebstore.model.dto.update;

import com.technovaperu.technovaperuwebstore.model.PedidoModel.EstadoPedido;

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

    @NotBlank
    private EstadoPedido estado;

    @NotBlank(message = "La direccion de envio no puede estar vacía")
    @Size(max = 255, message = "La direccion de envio no puede exceder 255 caracteres")
    private String direccionEnvio;
}
