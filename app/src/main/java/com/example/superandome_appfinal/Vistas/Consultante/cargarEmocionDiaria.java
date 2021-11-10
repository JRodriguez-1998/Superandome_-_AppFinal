package com.example.superandome_appfinal.Vistas.Consultante;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superandome_appfinal.Constantes.TipoEmocionEnum;
import com.example.superandome_appfinal.Dialogos.dialogo;
import com.example.superandome_appfinal.Dialogos.dialogoConfHorario;
import com.example.superandome_appfinal.Dialogos.dialogoCargarEmocion;

import com.example.superandome_appfinal.Entidades.Emocion;
import com.example.superandome_appfinal.Entidades.EmocionUsuario;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.EmocionServiceImpl;
import com.example.superandome_appfinal.Services.EmocionUsuarioServiceImpl;
import com.example.superandome_appfinal.Services.UsuarioServiceImpl;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class cargarEmocionDiaria extends Fragment {

    Button btnRegistrar;
    ImageButton btnAlegia, btnTristeza, btnIra, btnMiedo, btnAsco;
    Boolean estadoAlegria, estadoTristeza, estadoIra, estadoMiedo, estadoAsco;

    UsuarioServiceImpl usuarioService;
    EmocionUsuarioServiceImpl emocionUserService;
    EmocionServiceImpl emocionService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cargar_emocion_diaria, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnAlegia = (ImageButton) view.findViewById(R.id.btnAlegria);
        btnTristeza = (ImageButton) view.findViewById(R.id.btnTristeza);
        btnIra = (ImageButton) view.findViewById(R.id.btnIra);
        btnMiedo = (ImageButton) view.findViewById(R.id.btnMiedo);
        btnAsco = (ImageButton) view.findViewById(R.id.btnAsco);
        btnRegistrar = (Button) view.findViewById(R.id.btnRegistrar);

        estadoInicial();
        iniciarServicios();

        Usuario usuario = usuarioService.getUsuarioById(1);

        Date horaUsuario = usuario.getHorarioEmocion();
        Date horaActual = new Date(System.currentTimeMillis());

        DateFormat formatHora = new SimpleDateFormat("HH");
        DateFormat formatMinutos = new SimpleDateFormat("mm");

        LocalTime horaActualConver = LocalTime.of(Integer.parseInt(formatHora.format(horaActual).toString()),
                Integer.parseInt(formatMinutos.format(horaActual).toString()));

        LocalTime horaUserConver = LocalTime.of(Integer.parseInt(formatHora.format(horaUsuario).toString()),
                Integer.parseInt(formatMinutos.format(horaUsuario).toString()));

        btnAlegia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(estadoAlegria){
                    btnAlegia.setBackgroundResource(R.color.transparente);
                    estadoAlegria = false;
                }else{
                    habilitarAlegria();
                }
            }
        });

        btnTristeza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(estadoTristeza){
                    btnTristeza.setBackgroundResource(R.color.transparente);
                    estadoTristeza = false;
                }else{
                    habilitarTristeza();
                }
            }
        });

        btnIra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(estadoIra){
                    btnIra.setBackgroundResource(R.color.transparente);
                    estadoIra = false;
                }else{
                    habilitarIra();
                }
            }
        });

        btnMiedo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(estadoMiedo){
                    btnMiedo.setBackgroundResource(R.color.transparente);
                    estadoMiedo = false;
                }else{
                    habilitarMiedo();
                }
            }
        });

        btnAsco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(estadoAsco){
                    btnAsco.setBackgroundResource(R.color.transparente);
                    estadoAsco = false;
                }else{
                    habilitarAsco();
                }
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (horaActualConver.isAfter(horaUserConver) || horaActualConver.equals(horaUserConver)) {

                    if (emocionUserService.getEmocionByFechaAndId(1, new Date()) == null) {

                        if (buscarSeleccion(estadoAlegria, estadoTristeza, estadoIra, estadoMiedo, estadoAsco)) {

                            EmocionUsuario emocionUsuario = new EmocionUsuario();
                            emocionUsuario.setUsuario(usuario);

                            if (estadoAlegria) {
                                Emocion emocion = emocionService.getEmocionById(TipoEmocionEnum.FELICIDAD.getTipo());
                                emocionUsuario.setEmocion(emocion);

                                if (emocionUserService.guardar(emocionUsuario)) {
                                    mostrarDialogo();
                                } else {
                                    Toast.makeText(getActivity(), "Solo se permite un ingreso por dia", Toast.LENGTH_SHORT).show();
                                }

                            } else if (estadoTristeza) {
                                Emocion emocion = emocionService.getEmocionById(TipoEmocionEnum.TRISTERZA.getTipo());
                                emocionUsuario.setEmocion(emocion);

                                if (emocionUserService.guardar(emocionUsuario)) {
                                    mostrarDialogo();
                                } else {
                                    Toast.makeText(getActivity(), "Solo se permite un ingreso por dia", Toast.LENGTH_SHORT).show();
                                }

                            } else if (estadoIra) {
                                Emocion emocion = emocionService.getEmocionById(TipoEmocionEnum.IRA.getTipo());
                                emocionUsuario.setEmocion(emocion);

                                if (emocionUserService.guardar(emocionUsuario)) {
                                    mostrarDialogo();
                                } else {
                                    Toast.makeText(getActivity(), "Solo se permite un ingreso por dia", Toast.LENGTH_SHORT).show();
                                }

                            } else if (estadoMiedo) {
                                Emocion emocion = emocionService.getEmocionById(TipoEmocionEnum.MIEDO.getTipo());
                                emocionUsuario.setEmocion(emocion);

                                if (emocionUserService.guardar(emocionUsuario)) {
                                    mostrarDialogo();
                                } else {
                                    Toast.makeText(getActivity(), "Solo se permite un ingreso por dia", Toast.LENGTH_SHORT).show();
                                }
                            } else if (estadoAsco) {
                                Emocion emocion = emocionService.getEmocionById(TipoEmocionEnum.ASCO.getTipo());
                                emocionUsuario.setEmocion(emocion);

                                if (emocionUserService.guardar(emocionUsuario)) {
                                    mostrarDialogo();
                                } else {
                                    Toast.makeText(getActivity(), "Solo se permite un ingreso por dia", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            Toast.makeText(getActivity(), "Debe seleccionar al menos una emoción", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Solo se permite un ingreso por dia!!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(), "Debe ingresar la emoción en el horario configurado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void mostrarDialogo(){
        dialogoCargarEmocion d = new dialogoCargarEmocion();
        d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_cargar_emocion");
    }

    public void estadoInicial(){
        estadoAlegria = false;
        estadoTristeza = false;
        estadoIra = false;
        estadoMiedo = false;
        estadoAsco = false;
    }

    public void habilitarAlegria(){
        btnAlegia.setBackgroundResource(R.color.profesional);
        btnTristeza.setBackgroundResource(R.color.transparente);
        btnIra.setBackgroundResource(R.color.transparente);
        btnMiedo.setBackgroundResource(R.color.transparente);
        btnAsco.setBackgroundResource(R.color.transparente);

        estadoAlegria = true;
        estadoTristeza = false;
        estadoIra = false;
        estadoMiedo = false;
        estadoAsco = false;
    }

    public void habilitarTristeza(){
        btnTristeza.setBackgroundResource(R.color.profesional);
        btnAlegia.setBackgroundResource(R.color.transparente);
        btnIra.setBackgroundResource(R.color.transparente);
        btnMiedo.setBackgroundResource(R.color.transparente);
        btnAsco.setBackgroundResource(R.color.transparente);

        estadoTristeza = true;
        estadoAlegria = false;
        estadoIra = false;
        estadoMiedo = false;
        estadoAsco = false;
    }

    public void habilitarIra(){
        btnIra.setBackgroundResource(R.color.profesional);
        btnTristeza.setBackgroundResource(R.color.transparente);
        btnAlegia.setBackgroundResource(R.color.transparente);
        btnMiedo.setBackgroundResource(R.color.transparente);
        btnAsco.setBackgroundResource(R.color.transparente);

        estadoIra = true;
        estadoTristeza = false;
        estadoAlegria = false;
        estadoMiedo = false;
        estadoAsco = false;
    }

    public void habilitarMiedo(){
        btnMiedo.setBackgroundResource(R.color.profesional);
        btnIra.setBackgroundResource(R.color.transparente);
        btnTristeza.setBackgroundResource(R.color.transparente);
        btnAlegia.setBackgroundResource(R.color.transparente);
        btnAsco.setBackgroundResource(R.color.transparente);

        estadoMiedo = true;
        estadoIra = false;
        estadoTristeza = false;
        estadoAlegria = false;
        estadoAsco = false;
    }

    public void habilitarAsco(){
        btnAsco.setBackgroundResource(R.color.profesional);
        btnMiedo.setBackgroundResource(R.color.transparente);
        btnIra.setBackgroundResource(R.color.transparente);
        btnTristeza.setBackgroundResource(R.color.transparente);
        btnAlegia.setBackgroundResource(R.color.transparente);

        estadoAsco = true;
        estadoMiedo = false;
        estadoIra = false;
        estadoTristeza = false;
        estadoAlegria = false;
    }

    public Boolean buscarSeleccion(Boolean alegria, Boolean tristeza, Boolean ira, Boolean miedo, Boolean asco){
        if(alegria == false && tristeza == false && ira == false && miedo == false && asco == false){
            return false;
        }
        return true;
    }

    public void iniciarServicios(){
        try {
            emocionUserService= new EmocionUsuarioServiceImpl();
            usuarioService = new UsuarioServiceImpl();
            emocionService = new EmocionServiceImpl();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}