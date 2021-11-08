package com.example.superandome_appfinal.IServices;

import com.example.superandome_appfinal.Entidades.EmocionUsuario;

import java.util.Map;

public interface EmocionUsuarioService {
    Boolean guardar(EmocionUsuario emocionUsuario);
    Map<Integer, Float> getReporteMensualEmocion(int idUsuario, int mes);
}
