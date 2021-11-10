package com.example.superandome_appfinal.Services;

import com.example.superandome_appfinal.Daos.EmocionUsuarioDaoImpl;
import com.example.superandome_appfinal.Entidades.EmocionUsuario;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.IDaos.EmocionUsuarioDao;
import com.example.superandome_appfinal.IServices.EmocionUsuarioService;

import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EmocionUsuarioServiceImpl implements EmocionUsuarioService {

    private EmocionUsuarioDao dao;

    public EmocionUsuarioServiceImpl() throws SQLException {
        this.dao = new EmocionUsuarioDaoImpl();
    }

    @Override
    public Boolean guardar(EmocionUsuario emocionUsuario) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> f = executor.submit(() -> {
            try {
                emocionUsuario.setFecha(new Date());
                dao.create(emocionUsuario);
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
        }    }

    @Override
    public EmocionUsuario getEmocionByFechaAndId(Integer idUsuario, Date fecha) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<EmocionUsuario> f = executor.submit(() -> {
            try {
                return dao.getEmocionByFechaAndId(idUsuario, fecha);
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
