package com.example.superandome_appfinal.Services;

import com.example.superandome_appfinal.Daos.GeneroDaoImpl;
import com.example.superandome_appfinal.Entidades.Genero;
import com.example.superandome_appfinal.IDaos.GeneroDao;
import com.example.superandome_appfinal.IServices.GeneroService;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GeneroServiceImpl implements GeneroService {
    private final GeneroDao dao;

    public GeneroServiceImpl() throws SQLException {
        dao = new GeneroDaoImpl();
    }

    @Override
    public List<Genero> getGeneros() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<List<Genero>> f = executor.submit(() -> {
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
