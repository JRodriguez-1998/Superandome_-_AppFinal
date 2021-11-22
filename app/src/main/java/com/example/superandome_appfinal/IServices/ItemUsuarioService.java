package com.example.superandome_appfinal.IServices;

import com.example.superandome_appfinal.Entidades.ItemUsuario;

import java.util.List;

public interface ItemUsuarioService {
    Boolean guardarItemUsuario(ItemUsuario itemUsuario);
  //  Boolean deleteItemUsuario(ItemUsuario itemUsuario);
    Integer getItemUsuario(String idItem, String idUsuario);
    ItemUsuario getItemUsuarioObj(String idItem, String idUsuario);

    Boolean deleteItemUsuario(ItemUsuario itemdelete);

    List<ItemUsuario> getItemUsuariosByIdUsuario(int idUsuario);
}
