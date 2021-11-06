package com.example.superandome_appfinal.Daos;

import com.example.superandome_appfinal.Entidades.EncuestaUsuario;
import com.example.superandome_appfinal.Helpers.DataDB;
import com.example.superandome_appfinal.IDaos.EncuestaUsuarioDao;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;

public class EncuestaUsuarioDaoImpl extends BaseDaoImpl<EncuestaUsuario, Integer> implements EncuestaUsuarioDao {
    public EncuestaUsuarioDaoImpl() throws SQLException {
        super(DataDB.getConnectionSource(), EncuestaUsuario.class);
    }
}
