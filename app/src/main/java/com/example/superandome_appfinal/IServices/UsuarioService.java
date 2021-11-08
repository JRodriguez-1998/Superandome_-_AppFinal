package com.example.superandome_appfinal.IServices;

import com.example.superandome_appfinal.Entidades.Usuario;

import java.util.List;

public interface UsuarioService {
    void guardar(Usuario usuario);

    Usuario obtenerUsuario(String nick, String pass);

    List<Usuario> getUsers(String nick, String pass);

    List<Usuario> getUsers();
}
