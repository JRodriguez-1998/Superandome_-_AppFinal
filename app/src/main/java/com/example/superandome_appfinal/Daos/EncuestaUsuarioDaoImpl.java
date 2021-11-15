package com.example.superandome_appfinal.Daos;

import com.example.superandome_appfinal.Entidades.EncuestaUsuario;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.Helpers.DataDB;
import com.example.superandome_appfinal.IDaos.EncuestaUsuarioDao;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EncuestaUsuarioDaoImpl extends BaseDaoImpl<EncuestaUsuario, Integer> implements EncuestaUsuarioDao {

    public EncuestaUsuarioDaoImpl() throws SQLException {
        super(DataDB.getConnectionSource(), EncuestaUsuario.class);
    }

    @Override
    public EncuestaUsuario getEncuestaUsuarioById(Integer idEncuesta, Integer idUsuario) throws SQLException {
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("idEncuesta", idEncuesta);
        filtros.put("idUsuario", idUsuario);

        try {
            List<EncuestaUsuario> list = queryForFieldValues(filtros);
            if (list != null && list.size() > 0)
                return list.get(0);
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
}
