package com.example.superandome_appfinal.IServices;

import com.example.superandome_appfinal.Entidades.ItemUsuarioDiario;

import java.util.Date;

public interface ItemUsuarioDiarioService {
    Boolean guardarItemUsuarioDiario(ItemUsuarioDiario itemUsuarioDiario);

    Integer getItemUsuarioFecha(String idItem, String idUsuario, Date fecha);

    ItemUsuarioDiario getItemUsuarioFechaObj(String idItem, String idUsuario, Date fecha);

    Boolean deleteItemUsuarioDiario(ItemUsuarioDiario itemusuariodiario);
}
