package com.example.superandome_appfinal.IDaos;

import com.example.superandome_appfinal.Entidades.ItemUsuarioDiario;
import com.j256.ormlite.dao.Dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

public interface ItemUsuarioDiarioDao extends Dao<ItemUsuarioDiario, Integer> {

    Integer getItemUsuarioFecha(String idItem, String idUsuario, Date fecha) throws SQLException;
    ItemUsuarioDiario getItemUsuarioFechaObj(String idItem, String idUsuario, Date fecha) throws SQLException;
    Map<Integer, Float> getReporteMensualRutina(int idUsuario, int anio, int mes) throws SQLException, IOException;
}
