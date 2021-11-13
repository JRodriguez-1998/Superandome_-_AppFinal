package com.example.superandome_appfinal.IDaos;

import com.example.superandome_appfinal.Entidades.Consejo;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public interface ConsejoDao extends Dao<Consejo, Integer> {
    List<Consejo> getConsejosPendientes() throws SQLException;
    List<Consejo> getConsejosPendientesDIRECTOR() throws SQLException;
    List<Consejo> getConsejosByEstado(int idEstado) throws SQLException;
    List<Consejo> getConsejosByEstadoAndTipo(int idEstado, int idTipoConsejo) throws SQLException;
    Consejo getConsejoById(int idConsejo) throws SQLException;;
}
