package com.example.superandome_appfinal.Daos;

import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Helpers.DataDB;
import com.example.superandome_appfinal.IDaos.ConsejoDao;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsejoDaoImpl extends BaseDaoImpl<Consejo, Integer> implements ConsejoDao {

    public ConsejoDaoImpl() throws SQLException {
        super(DataDB.getConnectionSource(), Consejo.class);
    }

//    @Override
//    public List<Consejo> getConsejosPendientes() throws SQLException {
//        return this.queryForEq("idEstado", 1);
//    }

    @Override
    public List<Consejo> getConsejosByEstado(int idEstado) throws SQLException {

//        Map<String, Object> filtros = new HashMap<>();
//        filtros.put("idEstado", 1);
//        filtros.put("idTipoConsejo", 2);
//
//        return queryForFieldValues(filtros);
        return this.queryForEq("idEstado", idEstado);
    }

    @Override
    public List<Consejo> getConsejosByEstadoAndTipo(int idEstado, int idTipoConsejo) throws SQLException {
        Map<String, Object> filtros = new HashMap<>();

        filtros.put("idEstado", idEstado);
        filtros.put("idTipoConsejo", idTipoConsejo);

        return queryForFieldValues(filtros);
    }

    @Override
    public Consejo getConsejoById(int idConsejo) throws SQLException{
        return this.queryForId(idConsejo);
    }
}
