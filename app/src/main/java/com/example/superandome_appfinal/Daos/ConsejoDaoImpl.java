package com.example.superandome_appfinal.Daos;

import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Helpers.DataDB;
import com.example.superandome_appfinal.IDaos.ConsejoDao;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;

public class ConsejoDaoImpl extends BaseDaoImpl<Consejo, Integer> implements ConsejoDao {
    public ConsejoDaoImpl() throws SQLException {
        super(DataDB.getConnectionSource(), Consejo.class);
    }
}
