package com.example.superandome_appfinal.IDaos;

import com.example.superandome_appfinal.Entidades.Contenido;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public interface ContenidoDao extends Dao<Contenido, Integer> {
    List<Contenido> getContenidosAprobados() throws SQLException;
    List<Contenido> getContenidosPendientes() throws SQLException;
}
