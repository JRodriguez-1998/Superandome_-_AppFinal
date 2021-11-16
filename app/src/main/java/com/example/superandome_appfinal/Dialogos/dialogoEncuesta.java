package com.example.superandome_appfinal.Dialogos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superandome_appfinal.R;

import org.w3c.dom.Text;


public class dialogoEncuesta extends DialogFragment {

    Activity actividad;
    TextView txtMensaje, txtEstatus, btnAceptar;
    Integer resultado;

    public dialogoEncuesta() { }

    public dialogoEncuesta(Integer resultado) {
        this.resultado = resultado;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return crearDialogo();
    }

    private AlertDialog crearDialogo(){
        AlertDialog.Builder builder  = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_dialogo_encuesta,null);
        builder.setView(v);

        txtEstatus = (TextView) v.findViewById(R.id.txtEstatus);
        txtMensaje = (TextView) v.findViewById(R.id.txtMensajeDialogo);
        btnAceptar = (TextView) v.findViewById(R.id.btnAceptarDialog);

        if(resultado >= 0 && resultado <= 7){
            txtMensaje.setText("Minimo nivel de ansiedad");
        }

        if(resultado >= 8 && resultado <= 15){
            txtMensaje.setText("Leve nivel de ansiedad");
        }

        if(resultado >= 16 && resultado <= 25){
            txtMensaje.setText("Ansiedad moderada");
        }

        if(resultado >= 26 && resultado <= 63){
            txtMensaje.setText("Ansiedad grave");
        }

        eventoBotones();

        return builder.create();
    }

    private void eventoBotones(){
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_navigation_drawer_consultante);
                navController.navigate(R.id.nav_indexEncuestas);
                dismiss();
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
        return inflater.inflate(R.layout.fragment_dialogo_encuesta, container, false);
    }
}