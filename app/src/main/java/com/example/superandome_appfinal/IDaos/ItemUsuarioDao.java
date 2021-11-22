package com.example.superandome_appfinal.IDaos;

import com.example.superandome_appfinal.Entidades.ItemUsuario;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public interface ItemUsuarioDao extends Dao<ItemUsuario, Integer> {


    Integer getItemUsuario(String idItem, String idUsuario) throws SQLException;
    //Boolean deleteItemUsuario(String idItem, String idUsuario) throws SQLException;
    ItemUsuario getItemUsuarioObj(String idItem, String idUsuario) throws SQLException;
    List<ItemUsuario> getItemUsuariosByIdUsuario(int idUsuario) throws SQLException;
}
