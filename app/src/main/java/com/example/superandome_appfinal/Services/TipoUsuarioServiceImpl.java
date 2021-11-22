package com.example.superandome_appfinal.Services;
import com.example.superandome_appfinal.Daos.TipoUsuarioDaoImpl;
import com.example.superandome_appfinal.Entidades.TipoUsuario;
import com.example.superandome_appfinal.IDaos.TipoUsuarioDao;
import com.example.superandome_appfinal.IServices.TipoUsuarioService;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TipoUsuarioServiceImpl implements TipoUsuarioService {

    private final TipoUsuarioDao dao;

    public TipoUsuarioServiceImpl() throws SQLException {
        dao = new TipoUsuarioDaoImpl();
    }

    @Override
    public TipoUsuario getTipoUsuarioById(int id) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<TipoUsuario> f = executor.submit(() -> {
            try {
                return dao.getTipoUsuarioById(id);
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
