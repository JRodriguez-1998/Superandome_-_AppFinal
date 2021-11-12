package com.example.superandome_appfinal.Services;

import com.example.superandome_appfinal.Daos.ConsejoDaoImpl;
import com.example.superandome_appfinal.Daos.TipoConsejoDaoImpl;
import com.example.superandome_appfinal.Daos.UsuarioDaoImpl;
import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.TipoConsejo;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.IDaos.ConsejoDao;
import com.example.superandome_appfinal.IDaos.TipoConsejoDao;
import com.example.superandome_appfinal.IDaos.UsuarioDao;
import com.example.superandome_appfinal.IServices.ConsejoService;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConsejoServiceImpl implements ConsejoService {

    private final ConsejoDao dao;
    private final UsuarioDao usuarioDao;
    private final TipoConsejoDao tipoConsejoDao;

    public ConsejoServiceImpl() throws SQLException {
        dao = new ConsejoDaoImpl();
        usuarioDao = new UsuarioDaoImpl();
        tipoConsejoDao = new TipoConsejoDaoImpl();
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

    @Override
    public List<Consejo> getConsejosPendientes() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<List<Consejo>> f = executor.submit(() -> {
            try {
                List<Consejo> lista = dao.getConsejosPendientes();
                for (Consejo consejo:lista){
                    usuarioDao.refresh(consejo.getUsuarioAutor());
                    tipoConsejoDao.refresh(consejo.getTipoConsejo());
                }
                return lista;
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

    @Override
    public List<Consejo> getConsejosByEstadoAndTipo(int idEstado, int idTipo) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<List<Consejo>> f = executor.submit(() -> {
            try {
                return dao.getConsejosByEstadoAndTipo(idEstado, idTipo);
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
    public Consejo getConsejoById(int idConsejo) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Consejo> f = executor.submit(() -> {
            try {
                return dao.getConsejoById(idConsejo);
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
    public Boolean actualizar(Consejo consejo){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> f = executor.submit(() -> {
            try {
                dao.update(consejo);
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
