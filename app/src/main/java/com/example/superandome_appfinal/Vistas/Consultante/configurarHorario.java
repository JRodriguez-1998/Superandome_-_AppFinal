package com.example.superandome_appfinal.Vistas.Consultante;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.example.superandome_appfinal.Dialogos.dialogoSugerirConsejo;
import com.example.superandome_appfinal.Dialogos.dialogoConfHorario;

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
     Integer idUsuario;
     UsuarioServiceImpl usuarioService;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        reloj = (TimePicker) view.findViewById(R.id.reloj);
        txtHora = (TextView) view.findViewById(R.id.txtHoraSeleccionada);
        txtConfigActual = (TextView) view.findViewById(R.id.tvConfiActual);
        btnConfirmar = (Button) view.findViewById(R.id.btnConfirmarHora);
        try {


        SharedPreferences preferences = requireActivity().getSharedPreferences("sesiones", Context.MODE_PRIVATE);
        idUsuario = preferences.getInt("idUser", 0);

        iniciarServicios();

        reloj.setIs24HourView(true);

        reloj.setOnTimeChangedListener((timePicker, hour, minute) ->
                txtHora.setText("Horario elegido: " + hour + ":" + minute)
        );

        Usuario usuario = usuarioService.getUsuarioById(idUsuario);

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date hora = usuario.getHorarioEmocion();
        txtConfigActual.append(" " + dateFormat.format(hora));

        btnConfirmar.setOnClickListener(view1 -> {

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, reloj.getCurrentHour());
            cal.set(Calendar.MINUTE, reloj.getCurrentMinute());

            Date horario = cal.getTime();

            if(!txtHora.getText().toString().isEmpty()){
                usuario.setHorarioEmocion(horario);
                if(usuarioService.actualizar(usuario)){
                    usuarioService.actualizar(usuario);
                    dialogoConfHorario d = new dialogoConfHorario();
                    d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_conf_horario");
                }else{
                    Toast.makeText(getActivity(),"ERROR", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getActivity(), "Debe elegir un horario", Toast.LENGTH_LONG).show();
            }
        });
    } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Ha ocurrido un error al inicializar la pantalla", Toast.LENGTH_SHORT).show();
        }
    }
    public void iniciarServicios(){
        try {
            usuarioService = new UsuarioServiceImpl();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_configurar_horario, container, false);
    }
}