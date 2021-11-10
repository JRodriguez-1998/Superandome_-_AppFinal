package com.example.superandome_appfinal.Dialogos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Vistas.Consultante.homeConsultante;
import com.example.superandome_appfinal.Vistas.Consultante.navigationDrawer_consultante;

public class dialogoCargarEmocion extends DialogFragment {

    Activity actividad;
    TextView txtEstatus, txtMensaje, btnAceptar;

    public dialogoCargarEmocion() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return crearDialogo();
    }

    private AlertDialog crearDialogo(){
        AlertDialog.Builder builder  = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_dialogo_cargar_emocion,null);
        builder.setView(v);

        txtEstatus = (TextView) v.findViewById(R.id.txtEstatus);
        txtMensaje = (TextView) v.findViewById(R.id.txtMensajeDialogo);
        btnAceptar = (TextView) v.findViewById(R.id.btnAceptarDialog);

        eventoBotones();

        return builder.create();
    }

    private void eventoBotones(){
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
//// Crear fragmento de tu clase
//                Fragment home = new homeConsultante();
//// Obtener el administrador de fragmentos a través de la actividad
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//// Definir una transacción
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//// Remplazar el contenido principal por el fragmento
//                fragmentTransaction.replace(R.id.content, home);
//                fragmentTransaction.addToBackStack(null);
//// Cambiar
//                fragmentTransaction.commit();
//
            }
        });
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialogo_cargar_emocion, container, false);
    }
}