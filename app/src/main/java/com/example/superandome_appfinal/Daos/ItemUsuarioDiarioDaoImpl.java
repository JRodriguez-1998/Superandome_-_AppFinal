package com.example.superandome_appfinal.Daos;

import com.example.superandome_appfinal.Entidades.ItemUsuario;
import com.example.superandome_appfinal.Entidades.ItemUsuarioDiario;
import com.example.superandome_appfinal.Helpers.DataDB;
import com.example.superandome_appfinal.IDaos.ItemUsuarioDiarioDao;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.GenericRawResults;

import java.io.IOException;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemUsuarioDiarioDaoImpl extends BaseDaoImpl<ItemUsuarioDiario, Integer> implements ItemUsuarioDiarioDao {
    public ItemUsuarioDiarioDaoImpl() throws SQLException {
        super(DataDB.getConnectionSource(), ItemUsuarioDiario.class);
    }


    @Override
    public Integer getItemUsuarioFecha(String idItem, String idUsuario, Date fecha) throws SQLException {
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("idItem",idItem );
        filtros.put("idUsuario",idUsuario);
        filtros.put("fecha",fecha);

        try {
            List<ItemUsuarioDiario> list = queryForFieldValues(filtros);

            return list.size();

        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }

    @Override
    public ItemUsuarioDiario getItemUsuarioFechaObj(String idItem, String idUsuario, Date fecha) throws SQLException {
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("idItem",idItem );
        filtros.put("idUsuario",idUsuario);
        filtros.put("fecha",fecha);

        try {
            List<ItemUsuarioDiario> list = queryForFieldValues(filtros);

            return list.get(0);

        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }

    @Override
    public Map<Integer, Float> getReporteMensualRutina(int idUsuario, int anio, int mes) throws SQLException, IOException {
        GenericRawResults<Map.Entry<Integer, Float>> rawResults = this.queryRaw(
                "" +
                        " SELECT " +
                        "   iud.idItem, " +
                        "   aux.count / DAY(LAST_DAY(iud.fecha)) * 100 'porcentaje' " +
                        " FROM " +
                        "   sql10450855.itemusuariodiario iud " +
                        "   INNER JOIN ( " +
                        "       SELECT " +
                        "           idItem, " +
                        "           COUNT(1) count " +
                        "       FROM sql10450855.itemusuariodiario " +
                        "       WHERE " +
                        "           idUsuario = " + idUsuario +
                        "           AND YEAR(fecha) = " + anio +
                        "           AND MONTH(fecha) = " + mes +
                        "       GROUP BY idItem " +
                        "   ) aux ON aux.idItem = iud.idItem " +
                        " WHERE " +
                        "   iud.idUsuario = " + idUsuario +
                        "   AND YEAR(iud.fecha) = " + anio +
                        "   AND MONTH(iud.fecha) = " + mes +
                        " GROUP BY iud.idItem",
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


}
