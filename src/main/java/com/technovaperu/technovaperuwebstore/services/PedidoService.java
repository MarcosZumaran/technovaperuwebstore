package com.technovaperu.technovaperuwebstore.services;

import com.technovaperu.technovaperuwebstore.model.dto.base.PedidoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearPedidoDTO;
import com.technovaperu.technovaperuwebstore.model.dto.update.ActualizarPedidoDTO;
import java.math.BigDecimal;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PedidoService {
    PedidoDTO obtenerPedidoPorId(int id);
    List<PedidoDTO> obtenerPedidosPorUsuario(int idUsuario);
    Page<PedidoDTO> obtenerPedidosPorUsuarioPaginados(int idUsuario, int pagina, int tamanoPagina); // Paginación añadida
    PedidoDTO crearPedido(CrearPedidoDTO dto);
    PedidoDTO actualizarPedido(int id, ActualizarPedidoDTO dto);
    void eliminarPedido(int id);
    BigDecimal obtenerTotalPedidos(int idUsuario);
}
