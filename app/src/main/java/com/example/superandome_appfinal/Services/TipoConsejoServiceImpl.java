package com.example.superandome_appfinal.Services;

import com.example.superandome_appfinal.Daos.TipoConsejoDaoImpl;
import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.TipoConsejo;
import com.example.superandome_appfinal.IDaos.TipoConsejoDao;
import com.example.superandome_appfinal.IServices.TipoConsejoService;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TipoConsejoServiceImpl implements TipoConsejoService {
    private final TipoConsejoDao dao;

    public TipoConsejoServiceImpl() throws SQLException {
        dao = new TipoConsejoDaoImpl();
    }

    @Override
    public List<TipoConsejo> getTipoConsejos() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<List<TipoConsejo>> f = executor.submit(() -> {
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
