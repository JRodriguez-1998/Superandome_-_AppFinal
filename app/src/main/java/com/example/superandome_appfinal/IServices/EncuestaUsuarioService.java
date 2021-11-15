package com.example.superandome_appfinal.IServices;

import com.example.superandome_appfinal.Entidades.EncuestaUsuario;

import java.sql.SQLException;
import java.util.List;

public interface EncuestaUsuarioService {
    Boolean guardar(EncuestaUsuario encuestaUsuario);
    List<EncuestaUsuario> getEncuestaUsuarioById(Integer idEncuesta, Integer idUsuario);

    }
