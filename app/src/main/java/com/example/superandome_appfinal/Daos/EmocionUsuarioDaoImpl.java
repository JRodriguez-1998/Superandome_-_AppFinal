package com.example.superandome_appfinal.Daos;

import com.example.superandome_appfinal.Entidades.EmocionUsuario;
import com.example.superandome_appfinal.IDaos.EmocionUsuarioDao;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;

public class EmocionUsuarioDaoImpl extends BaseDaoImpl<EmocionUsuario, Integer> implements EmocionUsuarioDao {
    public EmocionUsuarioDaoImpl() throws SQLException {
        super(DataDB.getConnectionSource(), EmocionUsuario.class);
    }
}
