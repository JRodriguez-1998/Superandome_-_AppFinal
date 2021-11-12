package com.example.superandome_appfinal.Services;

import com.example.superandome_appfinal.Daos.ItemDaoImpl;
import com.example.superandome_appfinal.Entidades.Item;
import com.example.superandome_appfinal.IDaos.ItemDao;
import com.example.superandome_appfinal.IServices.ItemService;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ItemServiceImpl implements ItemService {

    private final ItemDao dao;

    public ItemServiceImpl() throws SQLException {
       this.dao = new ItemDaoImpl();
        }


    @Override
    public List<Item> getItemsRutina() {

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<List<Item>> a = executor.submit(() ->{
            try{
                return dao.getItemsRutina();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        });

        try {
            return a.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
}
