package com.example.superandome_appfinal.Vistas.Consultante;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.superandome_appfinal.Constantes.TipoConsejoEnum;
import com.example.superandome_appfinal.Constantes.TipoEmocionEnum;
import com.example.superandome_appfinal.Dialogos.dialogoCargarEmocion;

import com.example.superandome_appfinal.Dialogos.dialogoEmocionhorario;
import com.example.superandome_appfinal.Dialogos.dialogoEmocionxdia;
import com.example.superandome_appfinal.Dialogos.dialogoErrorFragment;
import com.example.superandome_appfinal.Entidades.Emocion;
import com.example.superandome_appfinal.Entidades.EmocionUsuario;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.Helpers.SessionManager;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.EmocionServiceImpl;
import com.example.superandome_appfinal.Services.EmocionUsuarioServiceImpl;
import com.example.superandome_appfinal.Services.UsuarioServiceImpl;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class cargarEmocionDiaria extends Fragment {

    Button btnRegistrar;
    ImageButton btnAlegia, btnTristeza, btnIra, btnMiedo, btnAsco;
    Boolean estadoAlegria, estadoTristeza, estadoIra, estadoMiedo, estadoAsco;

    UsuarioServiceImpl usuarioService;
    EmocionUsuarioServiceImpl emocionUserService;
    EmocionServiceImpl emocionService;
    Integer idUsuario;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cargar_emocion_diaria, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnAlegia = (ImageButton) view.findViewById(R.id.btnAlegria);
        btnTristeza = (ImageButton) view.findViewById(R.id.btnTristeza);
        btnIra = (ImageButton) view.findViewById(R.id.btnIra);
        btnMiedo = (ImageButton) view.findViewById(R.id.btnMiedo);
        btnAsco = (ImageButton) view.findViewById(R.id.btnAsco);
        btnRegistrar = (Button) view.findViewById(R.id.btnRegistrar);
        try {
            estadoInicial();
            iniciarServicios();

            idUsuario = SessionManager.obtenerUsuario(requireActivity()).getIdUsuario();

            Usuario usuario = usuarioService.getUsuarioById(idUsuario);



            Date horaUsuario = usuario.getHorarioEmocion();

            Calendar calHoraActual = Calendar.getInstance();
            Calendar calHoraUsuario = Calendar.getInstance();
            calHoraUsuario.setTime(horaUsuario);
            calHoraUsuario.set(Calendar.YEAR, calHoraUsuario.get(Calendar.YEAR));
            calHoraUsuario.set(Calendar.MONTH, calHoraUsuario.get(Calendar.MONTH));
            calHoraUsuario.set(Calendar.DAY_OF_MONTH, calHoraUsuario.get(Calendar.DAY_OF_MONTH));

            horaUsuario = calHoraUsuario.getTime();
            Date horaActual = new Date();

            DateFormat formatHora = new SimpleDateFormat("HH");
            DateFormat formatMinutos = new SimpleDateFormat("mm");



//            LocalTime horaActualConver = LocalTime.of(Integer.parseInt(formatHora.format(horaActual).toString()),
//                    Integer.parseInt(formatMinutos.format(horaActual).toString()));
//
//            LocalTime horaUserConver = LocalTime.of(Integer.parseInt(formatHora.format(horaUsuario).toString()),
//                    Integer.parseInt(formatMinutos.format(horaUsuario).toString()));

            btnAlegia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (estadoAlegria) {
                        btnAlegia.setBackgroundResource(R.color.transparente);
                        estadoAlegria = false;
                    } else {
                        habilitarAlegria();
                    }
                }
            });

            btnTristeza.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (estadoTristeza) {
                        btnTristeza.setBackgroundResource(R.color.transparente);
                        estadoTristeza = false;
                    } else {
                        habilitarTristeza();
                    }
                }
            });

            btnIra.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (estadoIra) {
                        btnIra.setBackgroundResource(R.color.transparente);
                        estadoIra = false;
                    } else {
                        habilitarIra();
                    }
                }
            });

            btnMiedo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (estadoMiedo) {
                        btnMiedo.setBackgroundResource(R.color.transparente);
                        estadoMiedo = false;
                    } else {
                        habilitarMiedo();
                    }
                }
            });

            btnAsco.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (estadoAsco) {
                        btnAsco.setBackgroundResource(R.color.transparente);
                        estadoAsco = false;
                    } else {
                        habilitarAsco();
                    }
                }
            });

            Date finalHoraUsuario = horaUsuario;

            btnRegistrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (emocionUserService.getEmocionByFechaAndId(idUsuario, new Date()) == null) {
                        if (horaActual.after(finalHoraUsuario) || horaActual.equals(finalHoraUsuario)) {

                            if (buscarSeleccion(estadoAlegria, estadoTristeza, estadoIra, estadoMiedo, estadoAsco)) {

                                EmocionUsuario emocionUsuario = new EmocionUsuario();
                                emocionUsuario.setUsuario(usuario);

                                if (estadoAlegria) {
                                    Emocion emocion = emocionService.getEmocionById(TipoEmocionEnum.FELICIDAD.getTipo());
                                    emocionUsuario.setEmocion(emocion);

                                    if (emocionUserService.guardar(emocionUsuario)) {
                                        mostrarDialogo(TipoConsejoEnum.FELICIDAD.getTipo());
                                    } else {
                                        dialogoEmocionxdia d = new dialogoEmocionxdia();
                                        d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_emocionxdia");
                                    }

                                } else if (estadoTristeza) {
                                    Emocion emocion = emocionService.getEmocionById(TipoEmocionEnum.TRISTERZA.getTipo());
                                    emocionUsuario.setEmocion(emocion);

                                    if (emocionUserService.guardar(emocionUsuario)) {
                                        mostrarDialogo(TipoConsejoEnum.TRISTEZA.getTipo());
                                    } else {
                                        dialogoEmocionxdia d = new dialogoEmocionxdia();
                                        d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_emocionxdia");
                                    }

                                } else if (estadoIra) {
                                    Emocion emocion = emocionService.getEmocionById(TipoEmocionEnum.IRA.getTipo());
                                    emocionUsuario.setEmocion(emocion);

                                    if (emocionUserService.guardar(emocionUsuario)) {
                                        mostrarDialogo(TipoConsejoEnum.IRA.getTipo());
                                    } else {
                                        dialogoEmocionxdia d = new dialogoEmocionxdia();
                                        d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_emocionxdia");
                                    }

                                } else if (estadoMiedo) {
                                    Emocion emocion = emocionService.getEmocionById(TipoEmocionEnum.MIEDO.getTipo());
                                    emocionUsuario.setEmocion(emocion);

                                    if (emocionUserService.guardar(emocionUsuario)) {
                                        mostrarDialogo(TipoConsejoEnum.MIEDO.getTipo());
                                    } else {
                                        dialogoEmocionxdia d = new dialogoEmocionxdia();
                                        d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_emocionxdia");
                                    }
                                } else if (estadoAsco) {
                                    Emocion emocion = emocionService.getEmocionById(TipoEmocionEnum.ASCO.getTipo());
                                    emocionUsuario.setEmocion(emocion);

                                    if (emocionUserService.guardar(emocionUsuario)) {
                                        mostrarDialogo(TipoConsejoEnum.ASCO.getTipo());
                                    } else {
                                        dialogoEmocionxdia d = new dialogoEmocionxdia();
                                        d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_emocionxdia");
                                    }
                                }
                            } else {
                                Toast.makeText(getActivity(), "Recuerda seleccionar la Emoción de hoy", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            dialogoEmocionhorario d = new dialogoEmocionhorario();
                            d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_emocionhorario");
                        }
                    } else {
                        dialogoEmocionxdia d = new dialogoEmocionxdia();
                        d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_emocionxdia");
                    }
                }
            });
        } catch (Exception e){

            e.printStackTrace();
            dialogoErrorFragment d = new dialogoErrorFragment();
            d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_errorfragment");
        }
    }

    public void mostrarDialogo(Integer idTipoEmocion){
        dialogoCargarEmocion d = new dialogoCargarEmocion(idTipoEmocion);
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
            dialogoErrorFragment d = new dialogoErrorFragment();
            d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_errorfragment");
        }
    }

}