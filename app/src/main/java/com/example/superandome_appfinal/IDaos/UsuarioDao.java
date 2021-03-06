package com.example.superandome_appfinal.IDaos;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioDao extends Dao<Usuario, Integer> {

    Usuario getUsuario(String nick, String pass) throws SQLException;
    Usuario getUsuarioById(int idUsuario) throws SQLException;
    Usuario getUsuario(String nick) throws SQLException;
}
