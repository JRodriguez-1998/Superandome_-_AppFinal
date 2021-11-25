package com.example.superandome_appfinal.Vistas.Consultante;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.superandome_appfinal.Constantes.EncuestaEnum;
import com.example.superandome_appfinal.Dialogos.dialogoEncuesta;
import com.example.superandome_appfinal.Dialogos.dialogoErrorFragment;
import com.example.superandome_appfinal.Dialogos.dialogoErrorInesperado;
import com.example.superandome_appfinal.Entidades.Encuesta;
import com.example.superandome_appfinal.Entidades.EncuestaUsuario;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.Helpers.SessionManager;
import com.example.superandome_appfinal.IServices.EncuestaService;
import com.example.superandome_appfinal.IServices.EncuestaUsuarioService;
import com.example.superandome_appfinal.IServices.UsuarioService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.EncuestaServiceImpl;
import com.example.superandome_appfinal.Services.EncuestaUsuarioServiceImpl;
import com.example.superandome_appfinal.Services.UsuarioServiceImpl;

import java.util.ArrayList;
import java.util.List;

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
        try {
            iniciarRadio(view);
            encuestaService = new EncuestaServiceImpl();
            encuestaUsuarioService = new EncuestaUsuarioServiceImpl();
            usuarioService = new UsuarioServiceImpl();

            idUsuario = SessionManager.obtenerUsuario(requireActivity()).getIdUsuario();

            btnAceptar = (Button) view.findViewById(R.id.btnAceptarEncuesta);

            btnAceptar.setOnClickListener(view1 -> {
                int total = analizarRadio();

                if (total == -1){
                    Toast.makeText(getActivity(), "Debe completar toda la encuesta", Toast.LENGTH_LONG).show();
                    return;
                }

                Encuesta encuesta = encuestaService.getEncuestaById(EncuestaEnum.TEST_ANSIEDAD_BECK.getId());
                Usuario usuario = usuarioService.getUsuarioById(idUsuario);
                EncuestaUsuario encuestaUsuario = new EncuestaUsuario(total, encuesta, usuario);

                if (encuestaUsuarioService.guardar(encuestaUsuario)) {
                    dialogoEncuesta d = new dialogoEncuesta(total);
                    d.show(requireActivity().getSupportFragmentManager(), "fragment_dialogo_encuesta");
                } else {
                    dialogoErrorInesperado d = new dialogoErrorInesperado();
                    d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_errorinesperado");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            dialogoErrorFragment d = new dialogoErrorFragment();
            d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_errorfragment");
        }

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

    public int analizarRadio() {
        List<Integer> rgroupList = new ArrayList<>();
        rgroupList.add(obtenerSeleccion(rgroup1));
        rgroupList.add(obtenerSeleccion(rgroup2));
        rgroupList.add(obtenerSeleccion(rgroup3));
        rgroupList.add(obtenerSeleccion(rgroup4));
        rgroupList.add(obtenerSeleccion(rgroup5));
        rgroupList.add(obtenerSeleccion(rgroup6));
        rgroupList.add(obtenerSeleccion(rgroup7));
        rgroupList.add(obtenerSeleccion(rgroup8));
        rgroupList.add(obtenerSeleccion(rgroup9));
        rgroupList.add(obtenerSeleccion(rgroup10));
        rgroupList.add(obtenerSeleccion(rgroup11));
        rgroupList.add(obtenerSeleccion(rgroup12));
        rgroupList.add(obtenerSeleccion(rgroup13));
        rgroupList.add(obtenerSeleccion(rgroup14));
        rgroupList.add(obtenerSeleccion(rgroup15));
        rgroupList.add(obtenerSeleccion(rgroup16));
        rgroupList.add(obtenerSeleccion(rgroup17));
        rgroupList.add(obtenerSeleccion(rgroup18));
        rgroupList.add(obtenerSeleccion(rgroup19));
        rgroupList.add(obtenerSeleccion(rgroup20));
        rgroupList.add(obtenerSeleccion(rgroup21));

        if (rgroupList.contains(-1))
            return -1;

        int result = 0;
        for (int rgroupIdx : rgroupList)
            result += rgroupIdx;

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
}