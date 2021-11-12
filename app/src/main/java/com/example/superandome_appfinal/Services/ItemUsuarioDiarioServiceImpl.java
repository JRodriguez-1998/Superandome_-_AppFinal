package com.example.superandome_appfinal.Services;

import com.example.superandome_appfinal.Daos.ItemUsuarioDiarioDaoImpl;
import com.example.superandome_appfinal.Entidades.ItemUsuarioDiario;
import com.example.superandome_appfinal.IDaos.ItemUsuarioDao;
import com.example.superandome_appfinal.IDaos.ItemUsuarioDiarioDao;
import com.example.superandome_appfinal.IServices.ItemUsuarioDiarioService;

import java.sql.SQLException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ItemUsuarioDiarioServiceImpl implements ItemUsuarioDiarioService {

    private final ItemUsuarioDiarioDao dao;

    public ItemUsuarioDiarioServiceImpl() throws SQLException{
        dao = new ItemUsuarioDiarioDaoImpl();
    }

    @Override
    public Boolean guardarItemUsuarioDiario(ItemUsuarioDiario itemusuariodiario){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> f = executor.submit(() ->{

            try {
                dao.create(itemusuariodiario);
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
    public Integer getItemUsuarioFecha(String idItem, String idUsuario, Date fecha) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future f = executor.submit(() -> {
            try {
                return dao.getItemUsuarioFecha(idItem, idUsuario,fecha);
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
    public ItemUsuarioDiario getItemUsuarioFechaObj(String idItem, String idUsuario, Date fecha) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future f = executor.submit(() -> {
            try {
                return dao.getItemUsuarioFechaObj(idItem, idUsuario,fecha);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });

        try {
            return (ItemUsuarioDiario) f.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean deleteItemUsuarioDiario(ItemUsuarioDiario itemusuariodiario){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> f = executor.submit(() ->{

            try {
                dao.delete(itemusuariodiario);
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




}
