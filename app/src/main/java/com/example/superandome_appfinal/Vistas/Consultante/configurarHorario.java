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

import com.example.superandome_appfinal.Entidades.Estado;
import com.example.superandome_appfinal.Entidades.Genero;
import com.example.superandome_appfinal.Entidades.TipoUsuario;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.UsuarioServiceImpl;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

public class configurarHorario extends Fragment {

     TimePicker reloj;
     TextView txtHora, txtConfigActual;
     Button btnConfirmar;
     String horaElegida;

     UsuarioServiceImpl usuarioService;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        reloj = (TimePicker) view.findViewById(R.id.reloj);
        txtHora = (TextView) view.findViewById(R.id.txtHoraSeleccionada);
        txtConfigActual = (TextView) view.findViewById(R.id.tvConfiActual);
        btnConfirmar = (Button) view.findViewById(R.id.btnConfirmarHora);

        try {
            usuarioService = new UsuarioServiceImpl();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Usuario u = usuarioService.getUsuarioById(1);
//        txtConfigActual.setText(u.getHorarioEmocion().toString());

        reloj.setOnTimeChangedListener((timePicker, hour, minute) ->
                txtHora.setText("Horario elegido: " + hour + ":" + minute)
        );

        btnConfirmar.setOnClickListener(view1 -> {
//            if(!horaElegida.isEmpty()){
//                Usuario usuario = new Usuario();
//                usuario.setHorarioEmocion(null);
//
//                Toast.makeText(getActivity(), "Exito", Toast.LENGTH_LONG).show();
//            }else{
//                Toast.makeText(getActivity(), "Debe elegir un horario", Toast.LENGTH_LONG).show();
//            }

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR, 12);
            cal.set(Calendar.MINUTE, 15);

            Date horario = cal.getTime();

            u.setHorarioEmocion(horario);

            usuarioService.actualizar(u);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_configurar_horario, container, false);
    }
}