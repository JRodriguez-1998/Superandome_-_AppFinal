package com.example.superandome_appfinal.Helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.superandome_appfinal.Entidades.TipoUsuario;
import com.example.superandome_appfinal.Entidades.Usuario;

public class SessionManager {

    static SharedPreferences preferences;
    static SharedPreferences.Editor editor;

    public static void guardarUsuario(Context context, Usuario usuario) {
        preferences = context.getSharedPreferences("sesiones", Context.MODE_PRIVATE);
        editor = preferences.edit();

        editor.putBoolean("sesion", true);
        editor.putInt("idUser", usuario.getIdUsuario());
        editor.putString("nickname", usuario.getNickname());
        editor.putInt("tipoUser", usuario.getTipoUsuario().getIdTipoUsuario());
        editor.apply();
    }

    public static Boolean tieneSesion(Context context) {
        preferences = context.getSharedPreferences("sesiones", Context.MODE_PRIVATE);
        return preferences.getBoolean("sesion",false);
    }

    public static Usuario obtenerUsuario(Context context) {
        if (!tieneSesion(context))
            return null;

        int idUsuario = preferences.getInt("idUser",0);
        int idTipoUsuario = preferences.getInt("tipoUser",0);
        String nickname = preferences.getString("nickname",null);

        if (idUsuario == 0)
            return null;

        TipoUsuario tipoUsuario = new TipoUsuario(idTipoUsuario);
        return new Usuario(idUsuario, nickname, tipoUsuario);
    }
}
