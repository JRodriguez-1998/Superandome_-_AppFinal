package com.example.superandome_appfinal.Daos;

import com.example.superandome_appfinal.Entidades.TipoConsejo;
import com.example.superandome_appfinal.IDaos.TipoConsejoDao;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;

public class TipoConsejoDaoImpl extends BaseDaoImpl<TipoConsejo, Integer> implements TipoConsejoDao {
    public TipoConsejoDaoImpl() throws SQLException {
        super(DataDB.getConnectionSource(), TipoConsejo.class);
    }
}
