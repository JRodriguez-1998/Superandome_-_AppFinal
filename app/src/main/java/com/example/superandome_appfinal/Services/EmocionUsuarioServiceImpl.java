package com.example.superandome_appfinal.Services;

import com.example.superandome_appfinal.Daos.EmocionUsuarioDaoImpl;
import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.EmocionUsuario;
import com.example.superandome_appfinal.IDaos.EmocionUsuarioDao;
import com.example.superandome_appfinal.IServices.EmocionUsuarioService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EmocionUsuarioServiceImpl implements EmocionUsuarioService {

    private final EmocionUsuarioDao dao;

    public EmocionUsuarioServiceImpl() throws SQLException {
        dao = new EmocionUsuarioDaoImpl();
    }

    @Override
    public Boolean guardar(EmocionUsuario emocionUsuario) {
        return null;
    }

    @Override
    public Map<Integer, Float> getReporteMensualEmocion(int idUsuario, int anio, int mes) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<Integer, Float>> f = executor.submit(() -> {
            try {
                return dao.getReporteMensualEmocion(idUsuario, anio, mes);
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
