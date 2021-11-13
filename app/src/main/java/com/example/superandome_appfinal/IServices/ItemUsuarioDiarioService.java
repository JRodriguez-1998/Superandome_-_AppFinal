package com.example.superandome_appfinal.IServices;

import com.example.superandome_appfinal.Entidades.ItemUsuarioDiario;

import java.util.Date;
import java.util.Map;

public interface ItemUsuarioDiarioService {
    Boolean guardarItemUsuarioDiario(ItemUsuarioDiario itemUsuarioDiario);

    Integer getItemUsuarioFecha(String idItem, String idUsuario, Date fecha);

    ItemUsuarioDiario getItemUsuarioFechaObj(String idItem, String idUsuario, Date fecha);

    Boolean deleteItemUsuarioDiario(ItemUsuarioDiario itemusuariodiario);

    Map<Integer, Float> getReporteMensualRutina(int idUsuario, int anio, int mes);
}
