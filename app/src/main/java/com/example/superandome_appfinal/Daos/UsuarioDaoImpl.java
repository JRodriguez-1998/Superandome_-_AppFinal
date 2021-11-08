package com.example.superandome_appfinal.Daos;

import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.Helpers.DataDB;
import com.example.superandome_appfinal.IDaos.UsuarioDao;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;
import java.util.List;

public class UsuarioDaoImpl extends BaseDaoImpl<Usuario, Integer> implements UsuarioDao {
    public UsuarioDaoImpl() throws SQLException {
        super(DataDB.getConnectionSource(), Usuario.class);
    }

    @Override
    public Usuario getUsuarioById(int idUsuario) throws SQLException {
        return this.queryForId(idUsuario);
    }
}
