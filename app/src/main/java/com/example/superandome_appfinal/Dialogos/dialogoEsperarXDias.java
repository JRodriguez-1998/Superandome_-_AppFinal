package com.example.superandome_appfinal.Dialogos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.superandome_appfinal.R;

import java.text.MessageFormat;

public class dialogoEsperarXDias extends DialogFragment {
    Activity actividad;
    TextView txtEstatus, txtMensaje, btnAceptar;
    Long dias;

    public dialogoEsperarXDias(long dias) {
        this.dias = dias;
    }

    @NonNull
    @Override
    public AlertDialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return crearDialogo();
    }

    private AlertDialog crearDialogo(){
        AlertDialog.Builder builder  = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_dialogo_esperarxdias,null);
        builder.setView(v);

        txtEstatus = (TextView) v.findViewById(R.id.txtEstatus);
        txtMensaje = (TextView) v.findViewById(R.id.txtMensajeDialogo);
        btnAceptar = (TextView) v.findViewById(R.id.btnAceptarDialogCambioPass);

        String textoDias = MessageFormat.format("" +
                        "{0} {1}",
                        dias.toString(),
                        dias == 1 ? "día" : "días");
        String mensajeDias = txtMensaje.getText().toString().replace("#DIAS#", textoDias);
        txtMensaje.setText(mensajeDias);

        btnAceptar.setOnClickListener(v1 -> dismiss());

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.actividad = (Activity) context;
        } else {
            throw new RuntimeException(context.toString());
        }
    }
}
