package com.example.superandome_appfinal.Daos;

import com.example.superandome_appfinal.Entidades.EncuestaUsuario;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.Helpers.DataDB;
import com.example.superandome_appfinal.IDaos.EncuestaUsuarioDao;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EncuestaUsuarioDaoImpl extends BaseDaoImpl<EncuestaUsuario, Integer> implements EncuestaUsuarioDao {

    public EncuestaUsuarioDaoImpl() throws SQLException {
        super(DataDB.getConnectionSource(), EncuestaUsuario.class);
    }

    @Override
    public List<EncuestaUsuario> getEncuestaUsuarioByUsuario(int idUsuario) throws SQLException {
        QueryBuilder<EncuestaUsuario, Integer> qb = this.queryBuilder();
        qb = qb.where().eq("idUsuario", idUsuario).queryBuilder();
        return qb.orderBy("fecha", false).query();
    }
    
    @Override
    public List<EncuestaUsuario> getEncuestaUsuarioById(Integer idEncuesta, Integer idUsuario) throws SQLException {
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("idEncuesta", idEncuesta);
        filtros.put("idUsuario", idUsuario);

        return queryForFieldValues(filtros);
    }
}
