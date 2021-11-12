package com.example.superandome_appfinal.Daos;

import com.example.superandome_appfinal.Entidades.ItemUsuario;
import com.example.superandome_appfinal.Helpers.DataDB;
import com.example.superandome_appfinal.IDaos.ItemUsuarioDao;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemUsuarioDaoImpl extends BaseDaoImpl<ItemUsuario, Integer> implements ItemUsuarioDao {
    public ItemUsuarioDaoImpl() throws SQLException {
        super(DataDB.getConnectionSource(), ItemUsuario.class);
    }


    @Override
    public Integer getItemUsuario(String idItem, String idUsuario) throws SQLException {
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("idItem",idItem );
        filtros.put("idUsuario",idUsuario);

        try {
            List<ItemUsuario> list = queryForFieldValues(filtros);

                return list.size();

        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }


    @Override
    public ItemUsuario getItemUsuarioObj(String idItem, String idUsuario) throws SQLException {
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("idItem",idItem );
        filtros.put("idUsuario",idUsuario);

        try {
            List<ItemUsuario> list = queryForFieldValues(filtros);

            return list.get(0);

        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }


}
