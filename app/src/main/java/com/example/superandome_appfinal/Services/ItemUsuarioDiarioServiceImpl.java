package com.example.superandome_appfinal.Services;

import com.example.superandome_appfinal.Constantes.ItemRutinaEnum;
import com.example.superandome_appfinal.Daos.ItemUsuarioDiarioDaoImpl;
import com.example.superandome_appfinal.Entidades.ItemUsuarioDiario;
import com.example.superandome_appfinal.IDaos.ItemUsuarioDao;
import com.example.superandome_appfinal.IDaos.ItemUsuarioDiarioDao;
import com.example.superandome_appfinal.IServices.ItemUsuarioDiarioService;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
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

    @Override
    public Map<Integer, Float> getReporteMensualRutina(int idUsuario, int anio, int mes) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Map<Integer, Float>> f = executor.submit(() -> {
            try {
                Map<Integer, Float> result = dao.getReporteMensualRutina(idUsuario, anio, mes);
//                if (result.size() == 0)
//                    return result;

                // Si alguno no viene, le pongo 0.
                if (result.get(ItemRutinaEnum.DIETA.getId()) == null)
                    result.put(ItemRutinaEnum.DIETA.getId(), 0f);

                if (result.get(ItemRutinaEnum.CONSUMO.getId()) == null)
                    result.put(ItemRutinaEnum.CONSUMO.getId(), 0f);

                if (result.get(ItemRutinaEnum.EJERCICIO.getId()) == null)
                    result.put(ItemRutinaEnum.EJERCICIO.getId(), 0f);

                if (result.get(ItemRutinaEnum.DESCANSO.getId()) == null)
                    result.put(ItemRutinaEnum.DESCANSO.getId(), 0f);

                if (result.get(ItemRutinaEnum.AMBIENTE.getId()) == null)
                    result.put(ItemRutinaEnum.AMBIENTE.getId(), 0f);

                if (result.get(ItemRutinaEnum.HIGIENE.getId()) == null)
                    result.put(ItemRutinaEnum.HIGIENE.getId(), 0f);

                if (result.get(ItemRutinaEnum.EQUILIBRIO.getId()) == null)
                    result.put(ItemRutinaEnum.EQUILIBRIO.getId(), 0f);

                if (result.get(ItemRutinaEnum.SOCIAL.getId()) == null)
                    result.put(ItemRutinaEnum.SOCIAL.getId(), 0f);

                if (result.get(ItemRutinaEnum.OCIO.getId()) == null)
                    result.put(ItemRutinaEnum.OCIO.getId(), 0f);

                if (result.get(ItemRutinaEnum.NATURALEZA.getId()) == null)
                    result.put(ItemRutinaEnum.NATURALEZA.getId(), 0f);

                return result;
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
