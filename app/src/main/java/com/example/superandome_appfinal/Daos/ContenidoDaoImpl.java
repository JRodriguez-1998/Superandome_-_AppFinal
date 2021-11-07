package com.example.superandome_appfinal.Daos;

import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.Contenido;
import com.example.superandome_appfinal.Helpers.DataDB;
import com.example.superandome_appfinal.IDaos.ContenidoDao;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;

public class ContenidoDaoImpl extends BaseDaoImpl<Contenido, Integer> implements ContenidoDao {
    public ContenidoDaoImpl() throws SQLException {
        super(DataDB.getConnectionSource(), Contenido.class);
    }
}
