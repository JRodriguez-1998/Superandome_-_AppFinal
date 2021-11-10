package com.example.superandome_appfinal.IServices;

import com.example.superandome_appfinal.Entidades.EmocionUsuario;
import com.example.superandome_appfinal.Entidades.Usuario;

import java.util.Date;

public interface EmocionUsuarioService {
    Boolean guardar(EmocionUsuario emocionUsuario);
    EmocionUsuario getEmocionByFechaAndId(Integer idUsuario, Date fecha);

}
