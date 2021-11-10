package com.example.superandome_appfinal.Daos;

import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.Helpers.DataDB;
import com.example.superandome_appfinal.IDaos.UsuarioDao;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


public class UsuarioDaoImpl extends BaseDaoImpl<Usuario, Integer> implements UsuarioDao {
    public UsuarioDaoImpl() throws SQLException {
        super(DataDB.getConnectionSource(), Usuario.class);
    }

    @Override
    public Usuario getUsuarioById(int idUsuario) throws SQLException {
        return this.queryForId(idUsuario);
    }

    @Override
    public Usuario getUsuario(String nick, String pass) throws SQLException {
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("nickname", nick);
        filtros.put("password", pass);

        try {
            List<Usuario> list = queryForFieldValues(filtros);
            if (list != null && list.size() > 0)
                return list.get(0);
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public Usuario getUsuario(String nick) throws SQLException {
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("nickname", nick);

        try {
            List<Usuario> list = queryForFieldValues(filtros);
            if (list != null && list.size() > 0)
                return list.get(0);
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

}
