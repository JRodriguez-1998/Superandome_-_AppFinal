package com.example.superandome_appfinal.Daos;

import com.example.superandome_appfinal.Entidades.Emocion;
import com.example.superandome_appfinal.Helpers.DataDB;
import com.example.superandome_appfinal.IDaos.EmocionDao;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;

public class EmocionDaoImpl extends BaseDaoImpl<Emocion, Integer> implements EmocionDao {
    public EmocionDaoImpl() throws SQLException {
        super(DataDB.getConnectionSource(), Emocion.class);
    }
}
