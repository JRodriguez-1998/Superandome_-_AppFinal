package com.example.superandome_appfinal.IDaos;

import com.example.superandome_appfinal.Entidades.TipoUsuario;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public interface TipoUsuarioDao extends Dao<TipoUsuario, Integer> {
    TipoUsuario getTipoUsuarioById(int id) throws SQLException;
}
