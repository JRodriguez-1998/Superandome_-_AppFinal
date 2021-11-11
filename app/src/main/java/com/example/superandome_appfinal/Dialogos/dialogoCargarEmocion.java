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
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Vistas.Consultante.activity_altaConsultante;
import com.example.superandome_appfinal.Vistas.Consultante.cargarEmocionDiaria;
import com.example.superandome_appfinal.Vistas.Consultante.homeConsultante;

import com.example.superandome_appfinal.Vistas.Consultante.homeConsultante;
import com.example.superandome_appfinal.Vistas.Consultante.navigationDrawer_consultante;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.internal.NavigationMenuItemView;

public class dialogoCargarEmocion extends DialogFragment {

    Activity actividad;
    TextView txtEstatus, txtMensaje, btnAceptar;
    FragmentTransaction transaction;
    Fragment fragmentHome;

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
                fragmentHome = new homeConsultante();
                getActivity().getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment_content_navigation_drawer_consultante, fragmentHome).commit();
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