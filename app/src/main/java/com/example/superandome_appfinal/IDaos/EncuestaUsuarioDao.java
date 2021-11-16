package com.example.superandome_appfinal.IDaos;

import com.example.superandome_appfinal.Entidades.EncuestaUsuario;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public interface EncuestaUsuarioDao extends Dao<EncuestaUsuario, Integer> {
    List<EncuestaUsuario> getEncuestaUsuarioByUsuario(int idUsuario) throws SQLException;
}
