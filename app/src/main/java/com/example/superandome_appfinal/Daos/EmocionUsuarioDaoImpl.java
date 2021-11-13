package com.example.superandome_appfinal.Daos;

import com.example.superandome_appfinal.Entidades.EmocionUsuario;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.Helpers.DataDB;
import com.example.superandome_appfinal.IDaos.EmocionUsuarioDao;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.field.DataType;

import java.io.IOException;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class EmocionUsuarioDaoImpl extends BaseDaoImpl<EmocionUsuario, Integer> implements EmocionUsuarioDao {

    public EmocionUsuarioDaoImpl() throws SQLException {
        super(DataDB.getConnectionSource(), EmocionUsuario.class);
    }

    @Override
    public Map<Integer, Float> getReporteMensualEmocion(int idUsuario, int anio, int mes) throws SQLException, IOException {
        GenericRawResults<Map.Entry<Integer, Float>> rawResults = this.queryRaw(
                "" +
                        " SELECT " +
                        "   idEmocion, " +
                        "   COUNT(1) / aux.count * 100 'porcentaje' " +
                        " FROM " +
                        "   sql10450855.emocionusuario " +
                        "   CROSS JOIN ( " +
                        "       SELECT COUNT(1) count " +
                        "       FROM sql10450855.emocionusuario " +
                        "       WHERE " +
                        "           idUsuario = " + idUsuario +
                        "           AND YEAR(fecha) = " + anio +
                        "           AND MONTH(fecha) = " + mes +
                        "   ) aux " +
                        " WHERE " +
                        "   idUsuario = " + idUsuario +
                        "   AND YEAR(fecha) = " + anio +
                        "   AND MONTH(fecha) = " + mes +
                        " GROUP BY idEmocion",
                (columnNames, resultColumns) -> {
                    Integer key = Integer.parseInt(resultColumns[0]);
                    Float value = Float.parseFloat(resultColumns[1]);
                    return new AbstractMap.SimpleEntry<>(key, value);
                }
        );

        Map<Integer, Float> map = new HashMap<>();
        for (Map.Entry<Integer, Float> entry : rawResults.getResults()) {
            map.put(entry.getKey(), entry.getValue());
        }

        rawResults.close();

        return map;
    }

    @Override
    public EmocionUsuario getEmocionByFechaAndId(Integer idUsuario, Date fecha) throws SQLException {
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("idUsuario", idUsuario);
        filtros.put("fecha", fecha);

        try {
            List<EmocionUsuario> list = queryForFieldValues(filtros);
            if (list != null && list.size() > 0)
                return list.get(0);
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
