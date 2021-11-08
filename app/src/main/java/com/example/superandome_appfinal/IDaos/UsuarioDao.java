package com.example.superandome_appfinal.IDaos;

import android.database.SQLException;

import com.example.superandome_appfinal.Entidades.Usuario;
import com.j256.ormlite.dao.Dao;

import java.util.List;

public interface UsuarioDao extends Dao<Usuario, Integer> {
    Usuario obtenerUsuario(String nick, String pass) throws SQLException;

    List<Usuario> getUsers(String nick, String pass) throws java.sql.SQLException;


}
