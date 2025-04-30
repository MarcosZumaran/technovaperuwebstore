package com.technovaperu.technovaperuwebstore.model.dto.base;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoritosDTO {
    private int id;
    private int idUsuario;
    private int idProducto;
    private LocalDateTime fechaAgregado;
}
