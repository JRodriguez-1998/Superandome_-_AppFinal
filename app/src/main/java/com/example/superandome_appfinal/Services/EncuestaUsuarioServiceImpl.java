package com.example.superandome_appfinal.Services;

import com.example.superandome_appfinal.Daos.EncuestaUsuarioDaoImpl;
import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.EncuestaUsuario;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.IDaos.EncuestaUsuarioDao;
import com.example.superandome_appfinal.IServices.EncuestaUsuarioService;

import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EncuestaUsuarioServiceImpl implements EncuestaUsuarioService {

    private EncuestaUsuarioDao dao;

    public EncuestaUsuarioServiceImpl()  throws SQLException {
        this.dao = new EncuestaUsuarioDaoImpl();
    }

    @Override
    public Boolean guardar(EncuestaUsuario encuestaUsuario) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> f = executor.submit(() -> {
            try {
                encuestaUsuario.setFecha(new Date());
                dao.create(encuestaUsuario);
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
    public EncuestaUsuario getEncuestaUsuarioById(Integer idEncuesta, Integer idUsuario) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future f = executor.submit(() -> {
            try {
                return dao.getEncuestaUsuarioById(idEncuesta, idUsuario);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });

        try {
            return (EncuestaUsuario) f.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
