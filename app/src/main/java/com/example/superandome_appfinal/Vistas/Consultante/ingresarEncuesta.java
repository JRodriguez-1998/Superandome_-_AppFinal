package com.example.superandome_appfinal.Vistas.Consultante;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.superandome_appfinal.Constantes.EncuestaEnum;
import com.example.superandome_appfinal.Dialogos.dialogoConfHorario;
import com.example.superandome_appfinal.Dialogos.dialogoEncuesta;
import com.example.superandome_appfinal.Entidades.Encuesta;
import com.example.superandome_appfinal.Entidades.EncuestaUsuario;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.IServices.EncuestaService;
import com.example.superandome_appfinal.IServices.EncuestaUsuarioService;
import com.example.superandome_appfinal.IServices.UsuarioService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.EncuestaServiceImpl;
import com.example.superandome_appfinal.Services.EncuestaUsuarioServiceImpl;
import com.example.superandome_appfinal.Services.UsuarioServiceImpl;

import java.sql.SQLException;

public class ingresarEncuesta extends Fragment {

    Button btnAceptar;
    RadioGroup rgroup1, rgroup2, rgroup3, rgroup4, rgroup5, rgroup6, rgroup7, rgroup8, rgroup9, rgroup10,
            rgroup11, rgroup12, rgroup13, rgroup14, rgroup15, rgroup16, rgroup17, rgroup18, rgroup19, rgroup20, rgroup21;

    EncuestaService encuestaService;
    EncuestaUsuarioService encuestaUsuarioService;
    UsuarioService usuarioService;
    Integer idUsuario;

    public ingresarEncuesta() {}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iniciarRadio(view);
        iniciarServicios();

        SharedPreferences preferences = requireActivity().getSharedPreferences("sesiones", Context.MODE_PRIVATE);
        idUsuario = preferences.getInt("idUser", 0);

        btnAceptar = (Button) view.findViewById(R.id.btnAceptarEncuesta);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int total = analizarRadio();

                if (total == -1){
                    Toast.makeText(getActivity(), "Debe completar toda la encuesta", Toast.LENGTH_LONG).show();
                    return;
                }

                Encuesta encuesta = encuestaService.getEncuestaById(EncuestaEnum.TEST_ANSIEDAD_BECK.getId());
                Usuario usuario = usuarioService.getUsuarioById(idUsuario);
                EncuestaUsuario encuestaUsuario = new EncuestaUsuario(total, encuesta, usuario);

                if(encuestaUsuarioService.guardar(encuestaUsuario)){
                    dialogoEncuesta d = new dialogoEncuesta(total);
                    d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_encuesta");
                }else{
                    Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void iniciarRadio(View view){
        rgroup1 = (RadioGroup) view.findViewById(R.id.rgroup1);
        rgroup2 = (RadioGroup) view.findViewById(R.id.rgroup2);
        rgroup3 = (RadioGroup) view.findViewById(R.id.rgroup3);
        rgroup4 = (RadioGroup) view.findViewById(R.id.rgroup4);
        rgroup5 = (RadioGroup) view.findViewById(R.id.rgroup5);
        rgroup6 = (RadioGroup) view.findViewById(R.id.rgroup6);
        rgroup7 = (RadioGroup) view.findViewById(R.id.rgroup7);
        rgroup8 = (RadioGroup) view.findViewById(R.id.rgroup8);
        rgroup9 = (RadioGroup) view.findViewById(R.id.rgroup9);
        rgroup10 = (RadioGroup) view.findViewById(R.id.rgroup10);
        rgroup11 = (RadioGroup) view.findViewById(R.id.rgroup11);
        rgroup12 = (RadioGroup) view.findViewById(R.id.rgroup12);
        rgroup13 = (RadioGroup) view.findViewById(R.id.rgroup13);
        rgroup14 = (RadioGroup) view.findViewById(R.id.rgroup14);
        rgroup15 = (RadioGroup) view.findViewById(R.id.rgroup15);
        rgroup16 = (RadioGroup) view.findViewById(R.id.rgroup16);
        rgroup17 = (RadioGroup) view.findViewById(R.id.rgroup17);
        rgroup18 = (RadioGroup) view.findViewById(R.id.rgroup18);
        rgroup19 = (RadioGroup) view.findViewById(R.id.rgroup19);
        rgroup20 = (RadioGroup) view.findViewById(R.id.rgroup20);
        rgroup21 = (RadioGroup) view.findViewById(R.id.rgroup21);
    }

    public int analizarRadio(){

        int rgroup1Idx = obtenerSeleccion(rgroup1);
        if (rgroup1Idx == -1)
            return -1;

        int rgroup2Idx = obtenerSeleccion(rgroup2);
        if (rgroup2Idx == -1)
            return -1;

        int rgroup3Idx = obtenerSeleccion(rgroup3);
        if (rgroup3Idx == -1)
            return -1;

        int rgroup4Idx = obtenerSeleccion(rgroup4);
        if (rgroup4Idx == -1)
            return -1;

        int rgroup5Idx = obtenerSeleccion(rgroup5);
        if (rgroup5Idx == -1)
            return -1;

        int rgroup6Idx = obtenerSeleccion(rgroup6);
        if (rgroup6Idx == -1)
            return -1;

        int rgroup7Idx = obtenerSeleccion(rgroup7);
        if (rgroup7Idx == -1)
            return -1;

        int rgroup8Idx = obtenerSeleccion(rgroup8);
        if (rgroup8Idx == -1)
            return -1;

        int rgroup9Idx = obtenerSeleccion(rgroup9);
        if (rgroup9Idx == -1)
            return -1;

        int rgroup10Idx = obtenerSeleccion(rgroup10);
        if (rgroup10Idx == -1)
            return -1;

        int rgroup11Idx = obtenerSeleccion(rgroup11);
        if (rgroup11Idx == -1)
            return -1;

        int rgroup12Idx = obtenerSeleccion(rgroup12);
        if (rgroup12Idx == -1)
            return -1;

        int rgroup13Idx = obtenerSeleccion(rgroup13);
        if (rgroup13Idx == -1)
            return -1;

        int rgroup14Idx = obtenerSeleccion(rgroup14);
        if (rgroup14Idx == -1)
            return -1;

        int rgroup15Idx = obtenerSeleccion(rgroup15);
        if (rgroup15Idx == -1)
            return -1;

        int rgroup16Idx = obtenerSeleccion(rgroup16);
        if (rgroup16Idx == -1)
            return -1;

        int rgroup17Idx = obtenerSeleccion(rgroup17);
        if (rgroup17Idx == -1)
            return -1;

        int rgroup18Idx = obtenerSeleccion(rgroup18);
        if (rgroup18Idx == -1)
            return -1;

        int rgroup19Idx = obtenerSeleccion(rgroup19);
        if (rgroup19Idx == -1)
            return -1;

        int rgroup20Idx = obtenerSeleccion(rgroup20);
        if (rgroup20Idx == -1)
            return -1;

        int rgroup21Idx = obtenerSeleccion(rgroup21);
        if (rgroup21Idx == -1)
            return -1;

        int result = rgroup1Idx + rgroup2Idx + rgroup3Idx + rgroup4Idx + rgroup5Idx + rgroup6Idx + rgroup7Idx +
                rgroup8Idx + rgroup9Idx + rgroup10Idx + rgroup11Idx + rgroup12Idx + rgroup13Idx + rgroup14Idx +
                rgroup15Idx + rgroup16Idx + rgroup17Idx + rgroup18Idx + rgroup19Idx + rgroup20Idx + rgroup21Idx;

        return result;
    }

    public int obtenerSeleccion(RadioGroup radioGroup){
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        View radioButton = radioGroup.findViewById(radioButtonID);
        int idx = radioGroup.indexOfChild(radioButton);
        return idx;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ingresar_encuesta, container, false);
    }

    public void iniciarServicios(){
        try {
            encuestaService = new EncuestaServiceImpl();
            encuestaUsuarioService = new EncuestaUsuarioServiceImpl();
            usuarioService = new UsuarioServiceImpl();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}