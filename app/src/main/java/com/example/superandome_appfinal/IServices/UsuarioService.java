package com.example.superandome_appfinal.IServices;

import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.Usuario;

import java.util.List;

public interface UsuarioService {
    Boolean actualizar(Usuario usuario);
    Usuario getUsuarioById(int idUsuario);

    Usuario getUsuario(String nick, String pass);
    List<Usuario> getUsers();
    Usuario getUsuario(String nick);
    Boolean guardar(Usuario usuario);
}
