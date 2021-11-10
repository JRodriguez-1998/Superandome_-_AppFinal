package com.example.superandome_appfinal.Services;

import com.example.superandome_appfinal.Daos.PreguntaSeguridadDaoImpl;
import com.example.superandome_appfinal.Entidades.PreguntaSeguridad;
import com.example.superandome_appfinal.IDaos.PreguntaSeguridadDao;
import com.example.superandome_appfinal.IServices.PreguntaSeguridadService;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PreguntaSeguridadServiceImpl implements PreguntaSeguridadService {
    private final PreguntaSeguridadDao dao;

    public PreguntaSeguridadServiceImpl() throws SQLException {
        dao = new PreguntaSeguridadDaoImpl();
    }

    @Override
    public List<PreguntaSeguridad> getPreguntas() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<List<PreguntaSeguridad>> f = executor.submit(() -> {
            try {
                return dao.queryForAll();
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
