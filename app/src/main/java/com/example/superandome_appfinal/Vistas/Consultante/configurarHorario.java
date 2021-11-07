package com.example.superandome_appfinal.Vistas.Consultante;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.R;

public class configurarHorario extends Fragment {

     TimePicker reloj;
     TextView txtHora;
     Button btnConfirmar;
     String horaElegida;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        reloj = (TimePicker) view.findViewById(R.id.reloj);
        txtHora = (TextView) view.findViewById(R.id.txtHoraSeleccionada);
        btnConfirmar = (Button) view.findViewById(R.id.btnConfirmarHora);

        reloj.setOnTimeChangedListener((timePicker, i, i1) ->
                txtHora.setText("Horario elegido: " + i + ":" + i1)
        );

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                horaElegida = txtHora.getText().toString();

                if(!horaElegida.isEmpty()){
                    Usuario usuario = new Usuario();
                    usuario.setHorarioEmocion(null);

                    Toast.makeText(getActivity(), "Exito", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(), "Debe elegir un horario", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_configurar_horario, container, false);
    }
}