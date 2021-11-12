package com.example.superandome_appfinal.IDaos;

import com.example.superandome_appfinal.Entidades.ItemUsuarioDiario;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.Date;

public interface ItemUsuarioDiarioDao extends Dao<ItemUsuarioDiario, Integer> {

    Integer getItemUsuarioFecha(String idItem, String idUsuario, Date fecha) throws SQLException;
    ItemUsuarioDiario getItemUsuarioFechaObj(String idItem, String idUsuario, Date fecha) throws SQLException;
}
