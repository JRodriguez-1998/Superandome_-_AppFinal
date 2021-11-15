package com.example.superandome_appfinal.IServices;

import com.example.superandome_appfinal.Entidades.EncuestaUsuario;

import java.sql.SQLException;

public interface EncuestaUsuarioService {
    Boolean guardar(EncuestaUsuario encuestaUsuario);
    EncuestaUsuario getEncuestaUsuarioById(Integer idEncuesta, Integer idUsuario);

    }
