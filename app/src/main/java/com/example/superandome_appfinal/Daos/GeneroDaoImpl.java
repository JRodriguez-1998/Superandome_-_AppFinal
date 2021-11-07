package com.example.superandome_appfinal.Daos;

import com.example.superandome_appfinal.Entidades.Genero;
import com.example.superandome_appfinal.Helpers.DataDB;
import com.example.superandome_appfinal.IDaos.GeneroDao;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;

public class GeneroDaoImpl extends BaseDaoImpl<Genero, Integer> implements GeneroDao {
    public GeneroDaoImpl() throws SQLException {
        super(DataDB.getConnectionSource(), Genero.class);
    }
}
