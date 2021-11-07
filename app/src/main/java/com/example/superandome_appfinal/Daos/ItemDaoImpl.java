package com.example.superandome_appfinal.Daos;

import com.example.superandome_appfinal.Entidades.Item;
import com.example.superandome_appfinal.Helpers.DataDB;
import com.example.superandome_appfinal.IDaos.ItemDao;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;

public class ItemDaoImpl extends BaseDaoImpl<Item, Integer> implements ItemDao {
    public ItemDaoImpl() throws SQLException {
        super(DataDB.getConnectionSource(), Item.class);
    }
}
