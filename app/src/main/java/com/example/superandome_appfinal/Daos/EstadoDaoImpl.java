package com.example.superandome_appfinal.Daos;

import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.Estado;
import com.example.superandome_appfinal.IDaos.EstadoDao;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;

public class EstadoDaoImpl extends BaseDaoImpl<Estado, Integer> implements EstadoDao {
    public EstadoDaoImpl() throws SQLException {
        super(DataDB.getConnectionSource(), Estado.class);
    }
}
