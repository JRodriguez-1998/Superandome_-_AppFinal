package com.example.superandome_appfinal.Daos;

import com.example.superandome_appfinal.Entidades.Encuesta;
import com.example.superandome_appfinal.Helpers.DataDB;
import com.example.superandome_appfinal.IDaos.EncuestaDao;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;

public class EncuestaDaoImpl extends BaseDaoImpl<Encuesta, Integer> implements EncuestaDao {
    public EncuestaDaoImpl() throws SQLException {
        super(DataDB.getConnectionSource(), Encuesta.class);
    }
}
