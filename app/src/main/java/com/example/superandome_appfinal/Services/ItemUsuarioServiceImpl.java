package com.example.superandome_appfinal.Services;

import com.example.superandome_appfinal.Daos.ItemUsuarioDaoImpl;
import com.example.superandome_appfinal.Entidades.ItemUsuario;
import com.example.superandome_appfinal.IDaos.ItemUsuarioDao;
import com.example.superandome_appfinal.IServices.ItemUsuarioService;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ItemUsuarioServiceImpl implements ItemUsuarioService {

    private  final ItemUsuarioDao dao;


    public ItemUsuarioServiceImpl() throws SQLException{
       dao = new ItemUsuarioDaoImpl();


    }

    @Override
    public Boolean guardarItemUsuario(ItemUsuario itemusuario){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> f = executor.submit(() ->{

            try {
                dao.create(itemusuario);
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
    public Integer getItemUsuario(String idItem, String idUsuario) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future f = executor.submit(() -> {
            try {
                return dao.getItemUsuario(idItem, idUsuario);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });

        try {
            return (Integer) f.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public ItemUsuario getItemUsuarioObj(String idItem, String idUsuario) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future f = executor.submit(() -> {
            try {
                return dao.getItemUsuarioObj(idItem, idUsuario);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });

        try {
            return (ItemUsuario) f.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }




    @Override
    public Boolean deleteItemUsuario(ItemUsuario itemusuario){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> f = executor.submit(() ->{

            try {
                dao.delete(itemusuario);
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
    public List<ItemUsuario> getItemUsuariosByIdUsuario(int idUsuario) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<List<ItemUsuario>> f = executor.submit(() ->{
            try {
                return dao.getItemUsuariosByIdUsuario(idUsuario);
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
