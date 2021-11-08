package com.example.superandome_appfinal.Services;

import com.example.superandome_appfinal.Daos.UsuarioDaoImpl;
import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.IDaos.UsuarioDao;
import com.example.superandome_appfinal.IServices.UsuarioService;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioDao dao;

    public UsuarioServiceImpl() throws SQLException {
        dao = new UsuarioDaoImpl();
    }

    @Override
    public void guardar(Usuario usuario) {

    }

    @Override
    public Usuario obtenerUsuario(String nick, String pass) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future f = executor.submit(() -> {
            try {
                return dao.obtenerUsuario(nick, pass);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });

        try {
            return (Usuario) f.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Usuario> getUsers(String nick, String pass) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<List<Usuario>> f = executor.submit(() -> {
            try {
                return dao.getUsers(nick, pass);
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

    @Override
    public List<Usuario> getUsers() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<List<Usuario>> f = executor.submit(() -> {
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
