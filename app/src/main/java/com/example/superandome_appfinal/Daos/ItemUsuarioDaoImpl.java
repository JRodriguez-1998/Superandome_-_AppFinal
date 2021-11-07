package com.example.superandome_appfinal.Daos;

import com.example.superandome_appfinal.Entidades.ItemUsuario;
import com.example.superandome_appfinal.Helpers.DataDB;
import com.example.superandome_appfinal.IDaos.ItemUsuarioDao;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;

public class ItemUsuarioDaoImpl extends BaseDaoImpl<ItemUsuario, Integer> implements ItemUsuarioDao {
    public ItemUsuarioDaoImpl() throws SQLException {
        super(DataDB.getConnectionSource(), ItemUsuario.class);
    }
}
