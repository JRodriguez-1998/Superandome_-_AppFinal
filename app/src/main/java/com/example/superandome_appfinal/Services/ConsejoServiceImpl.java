package com.example.superandome_appfinal.Services;

import com.example.superandome_appfinal.Daos.ConsejoDaoImpl;
import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.IDaos.ConsejoDao;
import com.example.superandome_appfinal.IServices.ConsejoService;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConsejoServiceImpl implements ConsejoService {
    private final ConsejoDao dao;

    public ConsejoServiceImpl() throws SQLException {
        dao = new ConsejoDaoImpl();
    }

    @Override
    public Boolean guardar(Consejo consejo) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> f = executor.submit(() -> {
            try {
                consejo.setFechaAlta(new Date());
                dao.create(consejo);
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

//    @Override
//    public List<Consejo> getConsejosPendientes() {
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        Future<List<Consejo>> f = executor.submit(() -> {
//            try {
//                return dao.getConsejosPendientes();
//            } catch (Exception e) {
//                e.printStackTrace();
//                return null;
//            }
//        });
//
//        try {
//            return f.get();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    @Override
    public List<Consejo> getConsejosByEstado(int idEstado) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<List<Consejo>> f = executor.submit(() -> {
            try {
                return dao.getConsejosByEstado(idEstado);
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
