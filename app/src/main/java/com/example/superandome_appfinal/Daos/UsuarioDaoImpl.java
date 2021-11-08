package com.example.superandome_appfinal.Daos;

import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.Helpers.DataDB;
import com.example.superandome_appfinal.IDaos.UsuarioDao;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioDaoImpl extends BaseDaoImpl<Usuario, Integer> implements UsuarioDao {
    public UsuarioDaoImpl() throws SQLException {
        super(DataDB.getConnectionSource(), Usuario.class);
    }

    @Override
    public Usuario obtenerUsuario(String nick, String pass) throws android.database.SQLException {

        Map<String, Object> filtros = new HashMap<>();
        filtros.put("nickname", nick);
        filtros.put("password", pass);

        try {
            return (Usuario) queryForFieldValues(filtros);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }


    public List<Usuario> findUser(String nick, String pass) throws SQLException
    {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("nickname", nick);
        userMap.put("password", pass);
        List<Usuario> userlistEntities= queryForFieldValues(userMap);
        return userlistEntities==null?null:userlistEntities;
    }

    @Override
    public List<Usuario> getUsers(String nick, String pass) throws SQLException {

        Map<String, Object> filtros = new HashMap<>();
        filtros.put("nickname", nick);
        filtros.put("password", pass);

        return queryForFieldValues(filtros);

        //return this.queryForEq("nickaname", nick);
    }

}
