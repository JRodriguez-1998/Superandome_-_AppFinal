package com.example.superandome_appfinal.Services;

import com.example.superandome_appfinal.Daos.ContenidoDaoImpl;
import com.example.superandome_appfinal.Entidades.Contenido;
import com.example.superandome_appfinal.IDaos.ContenidoDao;
import com.example.superandome_appfinal.IServices.ContenidoService;

import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ContenidoServiceImpl implements ContenidoService {


    private final ContenidoDao dao;

    public  ContenidoServiceImpl()throws SQLException{
        dao = new ContenidoDaoImpl();
    }




    @Override
    public Boolean guardar(Contenido contenido) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> f = executor.submit(() -> {
            try {
                contenido.setFechaCarga(new Date());
                dao.create(contenido);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });

        try {
            return f.get();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Contenido getContenidoByID(int id) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Contenido> f = executor.submit(() -> {
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
            return null
                    ;
        }




    }









}
