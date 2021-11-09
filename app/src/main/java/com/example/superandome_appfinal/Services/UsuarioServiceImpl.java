package com.example.superandome_appfinal.Services;

import com.example.superandome_appfinal.Daos.ConsejoDaoImpl;
import com.example.superandome_appfinal.Daos.UsuarioDaoImpl;
import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.IDaos.ConsejoDao;
import com.example.superandome_appfinal.IDaos.UsuarioDao;
import com.example.superandome_appfinal.IServices.UsuarioService;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDao dao;

    public UsuarioServiceImpl() throws SQLException {
        this.dao = new UsuarioDaoImpl();
    }

    @Override
    public Boolean actualizar(Usuario usuario) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> f = executor.submit(() -> {
            try {
                dao.update(usuario);
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
    public Usuario getUsuarioById(int idUsuario) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Usuario> f = executor.submit(() -> {
            try {
                return dao.getUsuarioById(idUsuario);
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
