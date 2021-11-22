package com.example.superandome_appfinal.Dialogos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.superandome_appfinal.Helpers.SessionManager;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Vistas.activity_login;


public class cerrarSesion extends DialogFragment {

    Activity actividad;
    TextView txtEstatus, txtMensaje, btnSi, btnNo;

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
                SessionManager.cerrarSesion(requireActivity());
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

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity){
            this.actividad  = (Activity) context;
        } else {
            throw new RuntimeException(context.toString());
        }
    }
}