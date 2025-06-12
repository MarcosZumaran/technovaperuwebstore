
package com.technovaperu.technovaperuwebstore.implement;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.technovaperu.technovaperuwebstore.exception.RecursoNoEncontradoException;
import com.technovaperu.technovaperuwebstore.model.dto.base.HistorialPedidosDTO;
import com.technovaperu.technovaperuwebstore.model.dto.create.CrearHistorialPedidosDTO;
import com.technovaperu.technovaperuwebstore.services.HistorialPedidosService;
import com.technovaperu.technovaperuwebstore.util.DynamicSqlBuilder;

@Service
public class HistorialPedidosServiceImpl implements HistorialPedidosService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Logger log = LoggerFactory.getLogger(HistorialPedidosServiceImpl.class);

    private final RowMapper<HistorialPedidosDTO> historialPedidoRowMapper = (rs, rowNum) -> HistorialPedidosDTO.builder()
            .id(rs.getLong("id"))
            .idPedido(rs.getLong("id_pedido"))
            .estadoAnterior(rs.getString("estado_anterior"))
            .estadoNuevo(rs.getString("estado_nuevo"))
            .fechaCambio(rs.getTimestamp("fecha_cambio").toLocalDateTime())
            .comentario(rs.getString("comentario"))
            .build();

    private List<HistorialPedidosDTO> ejecutarConsultaListaHistorialPedidos(String sql, Object... parametros) {
        try {
            log.info("Ejecutando consulta: {}", sql);
            return jdbcTemplate.query(sql, historialPedidoRowMapper, parametros);
        } catch (EmptyResultDataAccessException e) {
            log.error("Error al ejecutar la consulta: {}", e.getMessage());
            throw new RecursoNoEncontradoException("HistorialPedidos", "filtro aplicado", null);
        }
    }

    @Override
    public List<HistorialPedidosDTO> obtenerHistorialPedidos() {
        return ejecutarConsultaListaHistorialPedidos("SELECT * FROM historial_pedido ORDER BY id LIMIT 10 OFFSET ?");
    }

    @Override
    public List<HistorialPedidosDTO> obtenerHistorialPedidoPorUsuario(int pagina, long idUsuario) {
        int offset = (pagina - 1) * 10;
        String sql = """
                SELECT h.*
                FROM historial_pedido h
                JOIN pedido p ON h.id_pedido = p.id_pedido
                WHERE p.id_usuario = ?
                LIMIT 10 OFFSET ?
                """;
        ;

        return ejecutarConsultaListaHistorialPedidos(sql, idUsuario, offset);
    }

    @Override
    public List<HistorialPedidosDTO> obtenerHistorialPedidoPorPedido(int pagina, long idPedido) {
        int offset = (pagina - 1) * 10;
        return ejecutarConsultaListaHistorialPedidos(
                "SELECT * FROM historial_pedido WHERE id_pedido = ? ORDER BY id LIMIT 10 OFFSET ?", idPedido, offset);
    }

    @Override
    public HistorialPedidosDTO obtenerHistorialPedidoPorId(long id) {
        try {
            log.info("obteniendo historial pedido por ID: {}", id);
            return jdbcTemplate.queryForObject("SELECT * FROM historial_pedido WHERE id = ?", historialPedidoRowMapper,
                    id);
        } catch (EmptyResultDataAccessException e) {
            log.error("Error al ejecutar la consulta: {}", e.getMessage());
            throw new RecursoNoEncontradoException("HistorialPedidos", "filtro aplicado", null);
        }
    }

    @Override
    public HistorialPedidosDTO crearHistorialPedido(CrearHistorialPedidosDTO historialPedidoDTO) {

        Map<String, Object> fields = new LinkedHashMap<>();

        fields.put("id_pedido", historialPedidoDTO.getIdPedido());
        fields.put("estado_anterior", historialPedidoDTO.getEstadoAnterior());
        fields.put("estado_nuevo", historialPedidoDTO.getEstadoNuevo());
        fields.put("fecha_cambio", Timestamp.valueOf(historialPedidoDTO.getFechaCambio()));
        fields.put("comentario", historialPedidoDTO.getComentario());

        String sql = DynamicSqlBuilder.buildInsertSql("historial_pedido", fields);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        log.info("Creando historial pedido con datos: {}", fields);

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            for (Object value : fields.values()) {
                ps.setObject(i++, value);
            }
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        if (key == null) {
            log.error("Error al crear el historial pedido, clave generada es nula");
            throw new RecursoNoEncontradoException("HistorialPedidos", "filtro aplicado", null);
        }
        return obtenerHistorialPedidoPorId(key.longValue());

    }

    @Override
    public void borrarHistorialPedido(long id){
        if (!existeHistorialPedido(id)) {
            throw new RecursoNoEncontradoException("HistorialPedidos", "filtro aplicado", null);
        }
        String sql = DynamicSqlBuilder.buildDeleteSql("historial_pedido", "id = ?");
        jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existeHistorialPedido(long id){
        String sql = DynamicSqlBuilder.buildCountSql("historial_pedido", "id = ?");
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

}
