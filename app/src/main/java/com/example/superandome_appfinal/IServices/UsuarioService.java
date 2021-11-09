package com.example.superandome_appfinal.IServices;

import com.example.superandome_appfinal.Entidades.Usuario;

import java.util.List;

public interface UsuarioService {
    void guardar(Usuario usuario);

    Usuario getUsuario(String nick, String pass);

    List<Usuario> getUsers();
}
