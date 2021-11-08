package com.example.superandome_appfinal.IServices;

import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.Usuario;

import java.util.List;

public interface UsuarioService {
   // void guardar(Usuario usuario);
    Boolean actualizar(Usuario usuario);
    Usuario getUsuarioById(int idUsuario);
}
