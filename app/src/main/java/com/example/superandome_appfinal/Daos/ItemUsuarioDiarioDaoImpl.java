package com.example.superandome_appfinal.Daos;

import com.example.superandome_appfinal.Entidades.ItemUsuarioDiario;
import com.example.superandome_appfinal.Helpers.DataDB;
import com.example.superandome_appfinal.IDaos.ItemUsuarioDiarioDao;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;

public class ItemUsuarioDiarioDaoImpl extends BaseDaoImpl<ItemUsuarioDiario, Integer> implements ItemUsuarioDiarioDao {
    public ItemUsuarioDiarioDaoImpl() throws SQLException {
        super(DataDB.getConnectionSource(), ItemUsuarioDiario.class);
    }
}
