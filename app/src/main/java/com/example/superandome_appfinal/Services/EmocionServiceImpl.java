package com.example.superandome_appfinal.Services;

import com.example.superandome_appfinal.Daos.EmocionDaoImpl;
import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.Emocion;
import com.example.superandome_appfinal.IDaos.ConsejoDao;
import com.example.superandome_appfinal.IDaos.EmocionDao;
import com.example.superandome_appfinal.IServices.EmocionService;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EmocionServiceImpl implements EmocionService {

    private final EmocionDao dao;

    public EmocionServiceImpl() throws SQLException {
        dao = new EmocionDaoImpl();
    }

    @Override
    public Emocion getEmocionById(int idEmocion) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Emocion> f = executor.submit(() -> {
            try {
                return dao.getEmocionById(idEmocion);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });

        try {
            return f.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
