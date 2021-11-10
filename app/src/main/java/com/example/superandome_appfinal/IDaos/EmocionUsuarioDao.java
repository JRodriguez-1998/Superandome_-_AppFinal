package com.example.superandome_appfinal.IDaos;

import com.example.superandome_appfinal.Entidades.EmocionUsuario;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.Date;

public interface EmocionUsuarioDao extends Dao<EmocionUsuario, Integer> {

    EmocionUsuario getEmocionByFechaAndId(Integer idUsuario, Date fecha) throws SQLException;
}
