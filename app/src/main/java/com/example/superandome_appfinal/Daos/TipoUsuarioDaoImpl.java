package com.example.superandome_appfinal.Daos;

import com.example.superandome_appfinal.Entidades.TipoUsuario;
import com.example.superandome_appfinal.IDaos.TipoUsuarioDao;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;

public class TipoUsuarioDaoImpl extends BaseDaoImpl<TipoUsuario, Integer> implements TipoUsuarioDao {
    public TipoUsuarioDaoImpl() throws SQLException {
        super(DataDB.getConnectionSource(), TipoUsuario.class);
    }
}
