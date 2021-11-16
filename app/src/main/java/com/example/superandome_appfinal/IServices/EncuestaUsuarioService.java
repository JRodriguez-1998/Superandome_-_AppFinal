package com.example.superandome_appfinal.IServices;

import com.example.superandome_appfinal.Entidades.EncuestaUsuario;

import java.util.List;

public interface EncuestaUsuarioService {
    Boolean guardar(EncuestaUsuario encuestaUsuario);
    List<EncuestaUsuario> getEncuestaUsuarioByUsuario(int idUsuario);
    List<EncuestaUsuario> getEncuestaUsuarioById(Integer idEncuesta, Integer idUsuario);
}
