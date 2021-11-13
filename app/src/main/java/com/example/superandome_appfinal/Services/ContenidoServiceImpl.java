package com.example.superandome_appfinal.Services;

import com.example.superandome_appfinal.Daos.ContenidoDaoImpl;
import com.example.superandome_appfinal.Daos.TipoArchivoDaoImpl;
import com.example.superandome_appfinal.Daos.UsuarioDaoImpl;
import com.example.superandome_appfinal.Entidades.Contenido;
import com.example.superandome_appfinal.IDaos.ContenidoDao;
import com.example.superandome_appfinal.IDaos.TipoArchivoDao;
import com.example.superandome_appfinal.IDaos.TipoConsejoDao;
import com.example.superandome_appfinal.IDaos.UsuarioDao;
import com.example.superandome_appfinal.IServices.ContenidoService;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ContenidoServiceImpl implements ContenidoService {


    private final ContenidoDao dao;
    private final UsuarioDao usuarioDao;
    private final TipoArchivoDao tipoArchivoDao;

    public  ContenidoServiceImpl()throws SQLException{
        dao = new ContenidoDaoImpl();
        usuarioDao = new UsuarioDaoImpl();
        tipoArchivoDao = new TipoArchivoDaoImpl();
    }




    @Override
    public Boolean guardar(Contenido contenido) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Boolean> f = executor.submit(() -> {
            try {
                contenido.setFechaCarga(new Date());
                dao.create(contenido);
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
    public Contenido getContenidoByID(int id) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Contenido> f = executor.submit(() -> {
            try {
                return dao.queryForId(id);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });

        try {
            return f.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null
                    ;
        }




    }





    @Override
    public List<Contenido> getContenidosAprobados() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<List<Contenido>> f = executor.submit(() -> {
            try {
                List<Contenido> lista = dao.getContenidosAprobados();
                for (Contenido contenido:lista){
                    usuarioDao.refresh(contenido.getUsuario());
                    tipoArchivoDao.refresh(contenido.getTipoArchivo());
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
    public List<Contenido> getContenidosDERIVAR() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<List<Contenido>> f = executor.submit(() -> {
            try {
                List<Contenido> lista = dao.getContenidosDERIVAR();
                for (Contenido contenido:lista){
                    usuarioDao.refresh(contenido.getUsuario());
                    tipoArchivoDao.refresh(contenido.getTipoArchivo());
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


}
