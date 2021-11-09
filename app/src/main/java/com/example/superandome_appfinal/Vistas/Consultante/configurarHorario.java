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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;

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

       // reloj.setIs24HourView(true);

        try {
            usuarioService = new UsuarioServiceImpl();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Usuario usuario = usuarioService.getUsuarioById(1);

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date hora = usuario.getHorarioEmocion();



        reloj.setOnTimeChangedListener((timePicker, hour, minute) ->
                txtHora.setText("Horario elegido: " + hour + ":" + minute)
        );

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, reloj.getCurrentHour());
        cal.set(Calendar.MINUTE, reloj.getCurrentMinute());

        Date horario = cal.getTime();

        usuario.setHorarioEmocion(horario);
        usuarioService.actualizar(usuario);

//        btnConfirmar.setOnClickListener(view1 -> {
//            if(!horaElegida.isEmpty()){
//                usuario.setHorarioEmocion(horario);
//                if(usuarioService.actualizar(usuario)){
//
//                }else{
//
//                }
//            }else{
//                Toast.makeText(getActivity(), "Debe elegir un horario", Toast.LENGTH_LONG).show();
//            }
       // });



        //txtConfigActual.append(" " + dateFormat.format(hora));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_configurar_horario, container, false);
    }
}