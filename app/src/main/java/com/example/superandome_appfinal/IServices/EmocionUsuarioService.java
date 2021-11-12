package com.example.superandome_appfinal.IServices;

import com.example.superandome_appfinal.Entidades.EmocionUsuario;
import com.example.superandome_appfinal.Entidades.Usuario;

import java.util.Date;

import java.util.Map;

public interface EmocionUsuarioService {
    Boolean guardar(EmocionUsuario emocionUsuario);
    Map<Integer, Float> getReporteMensualEmocion(int idUsuario, int anio, int mes);
    EmocionUsuario getEmocionByFechaAndId(Integer idUsuario, Date fecha);
}
