package com.example.superandome_appfinal.Dialogos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Vistas.activity_login;

public class cerrarSesion extends DialogFragment {

    Activity actividad;
    TextView txtEstatus, txtMensaje, btnSi, btnNo;

    //Creo Objeto SharedPreferences para utilizar para las Sesiones
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public cerrarSesion() {}

    @NonNull
    @Override
    public AlertDialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return crearDialogo();
    }

    private AlertDialog crearDialogo(){
        AlertDialog.Builder builder  = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_dialogo_cerrar_sesion,null);
        builder.setView(v);

        preferences = actividad.getSharedPreferences("sesiones", Context.MODE_PRIVATE);
        editor = preferences.edit();

        txtEstatus = (TextView) v.findViewById(R.id.txtEstatus);
        txtMensaje = (TextView) v.findViewById(R.id.txtMensajeDialogo);
        btnSi = (TextView) v.findViewById(R.id.btnSiDialog);
        btnNo = (TextView) v.findViewById(R.id.btnNoDialog);

        eventoBotones();

        return builder.create();
    }

    private void eventoBotones(){
        btnSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                cerrarSesionUsuario(false);
                Intent intent =  new Intent(getActivity(), activity_login.class);
                startActivity(intent);
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public void cerrarSesionUsuario(boolean cerrar){
        editor.putBoolean("sesion",cerrar);
        editor.apply();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.actividad  = (Activity) context;
        }else{
            throw new RuntimeException(context.toString());
        }
    }
}