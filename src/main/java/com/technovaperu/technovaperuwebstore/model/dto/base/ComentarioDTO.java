package com.technovaperu.technovaperuwebstore.model.dto.base;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
public class ComentarioDTO {

    @Schema(description = "ID único del comentario", example = "1")
    private int id;

    @NotNull(message = "El id de usuario no puede estar vacío")
    @Positive(message = "El id de usuario debe ser mayor que 0")
    @Schema(description = "ID del usuario al que pertenece el comentario", example = "1")
    private int idUsuario;

    @NotNull(message = "El id de producto no puede estar vacío")
    @Positive(message = "El id de producto debe ser mayor que 0")
    @Schema(description = "ID del producto al que pertenece el comentario", example = "1")
    private int idProducto;

    @NotBlank(message = "El comentario no puede estar vacío")
    @Schema(description = "Texto del comentario", example = "Esto es un comentario")
    private String texto;

    @NotNull(message = "La calificacion no puede estar vacía")
    @Positive(message = "La calificacion debe ser mayor o igual que 0")
    @Schema(description = "Calificacion del comentario", example = "5")
    private int calificacion;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Fecha y hora de creación del comentario", example = "2023-01-01 10:00:00")
    private LocalDateTime fechaCreacion;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "Fecha y hora de modificación del comentario", example = "2023-01-15 15:30:00")
    private LocalDateTime fechaModificacion;
}
