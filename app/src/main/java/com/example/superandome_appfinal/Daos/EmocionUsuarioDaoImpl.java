package com.example.superandome_appfinal.Daos;

import com.example.superandome_appfinal.Entidades.EmocionUsuario;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.Helpers.DataDB;
import com.example.superandome_appfinal.IDaos.EmocionUsuarioDao;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmocionUsuarioDaoImpl extends BaseDaoImpl<EmocionUsuario, Integer> implements EmocionUsuarioDao {

    public EmocionUsuarioDaoImpl() throws SQLException {
        super(DataDB.getConnectionSource(), EmocionUsuario.class);
    }

    @Override
    public EmocionUsuario getEmocionByFechaAndId(Integer idUsuario, Date fecha) throws SQLException {
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("idUsuario", idUsuario);
        filtros.put("fecha", fecha);

        try {
            List<EmocionUsuario> list = queryForFieldValues(filtros);
            if (list != null && list.size() > 0)
                return list.get(0);
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
