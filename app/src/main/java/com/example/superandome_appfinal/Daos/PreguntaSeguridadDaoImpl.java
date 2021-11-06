package com.example.superandome_appfinal.Daos;

import com.example.superandome_appfinal.Entidades.PreguntaSeguridad;
import com.example.superandome_appfinal.IDaos.PreguntaSeguridadDao;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;

public class PreguntaSeguridadDaoImpl  extends BaseDaoImpl<PreguntaSeguridad, Integer> implements PreguntaSeguridadDao {
    public PreguntaSeguridadDaoImpl() throws SQLException {
        super(DataDB.getConnectionSource(), PreguntaSeguridad.class);
    }
}
