package com.example.superandome_appfinal.Services;

import com.example.superandome_appfinal.Daos.EncuestaDaoImpl;
import com.example.superandome_appfinal.Entidades.Encuesta;
import com.example.superandome_appfinal.IDaos.EncuestaDao;
import com.example.superandome_appfinal.IServices.EncuestaService;

import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EncuestaServiceImpl implements EncuestaService {
    private final EncuestaDao dao;

    public EncuestaServiceImpl() throws SQLException {
        dao = new EncuestaDaoImpl();
    }

    @Override
    public Encuesta getEncuestaById(int id) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Encuesta> f = executor.submit(() -> {
            try {
                return dao.queryForId(id);
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
