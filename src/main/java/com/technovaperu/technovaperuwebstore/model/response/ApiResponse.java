package com.technovaperu.technovaperuwebstore.model.response;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private boolean exito;
    private String mensaje;
    private T datos;
    private LocalDateTime timestamp;

    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .exito(true)
                .mensaje(message)
                .datos(data)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder()
                .exito(false)
                .mensaje(message)
                .timestamp(LocalDateTime.now())
                .build();
    }
    
    public static ApiResponse<Map<String, String>> validationError(String message, Map<String, String> errors) {
        return ApiResponse.<Map<String, String>>builder()
                .exito(false)
                .mensaje(message)
                .datos(errors)
                .timestamp(LocalDateTime.now())
                .build();
    }
}