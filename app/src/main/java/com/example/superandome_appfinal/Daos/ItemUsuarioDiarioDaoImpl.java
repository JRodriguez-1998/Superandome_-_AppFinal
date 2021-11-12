package com.example.superandome_appfinal.Daos;

import com.example.superandome_appfinal.Entidades.ItemUsuario;
import com.example.superandome_appfinal.Entidades.ItemUsuarioDiario;
import com.example.superandome_appfinal.Helpers.DataDB;
import com.example.superandome_appfinal.IDaos.ItemUsuarioDiarioDao;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;
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




}
