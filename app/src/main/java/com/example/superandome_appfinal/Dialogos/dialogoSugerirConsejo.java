package com.example.superandome_appfinal.Dialogos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.superandome_appfinal.R;

public class dialogoSugerirConsejo extends DialogFragment {

    Activity actividad;
    TextView txtEstatus, txtMensaje, btnAceptar;

    public dialogoSugerirConsejo() {}

    @NonNull
    @Override
    public AlertDialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return crearDialogo();
    }

    private AlertDialog crearDialogo(){
        AlertDialog.Builder builder  = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_dialogo_sugerir_consejo,null);
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
}