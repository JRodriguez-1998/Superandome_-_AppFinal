package com.example.superandome_appfinal.IDaos;

import com.example.superandome_appfinal.Entidades.Item;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public interface ItemDao extends Dao<Item, Integer> {

    List<Item> getItemsRutina() throws SQLException;
}
