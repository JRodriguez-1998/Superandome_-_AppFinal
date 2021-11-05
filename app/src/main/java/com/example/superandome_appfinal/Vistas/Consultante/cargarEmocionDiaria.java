package com.example.superandome_appfinal.Vistas.Consultante;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.superandome_appfinal.Dialogos.dialogo;
import com.example.superandome_appfinal.R;

public class cargarEmocionDiaria extends Fragment {

    Button btnRegistrar;
    ImageButton btnAlegia, btnTristeza, btnIra, btnMiedo, btnAsco;
    Boolean estadoAlegria, estadoTristeza, estadoIra, estadoMiedo, estadoAsco;

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

        estadoInicial();

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
                if(buscarSeleccion(estadoAlegria, estadoTristeza, estadoIra, estadoMiedo, estadoAsco)){
                    if(estadoAlegria){
                        Toast.makeText(getActivity(),"HA SELECCIONADO ALEGRÍA", Toast.LENGTH_SHORT).show();
                    }else if(estadoTristeza){
                        Toast.makeText(getActivity(),"HA SELECCIONADO TRISTEZA", Toast.LENGTH_SHORT).show();
                    }else if(estadoIra){
                        Toast.makeText(getActivity(),"HA SELECCIONADO IRA", Toast.LENGTH_SHORT).show();
                    }else if(estadoMiedo){
                        Toast.makeText(getActivity(),"HA SELECCIONADO MIEDO", Toast.LENGTH_SHORT).show();
                    }else if(estadoAsco){
                        Toast.makeText(getActivity(),"HA SELECCIONADO ASCO", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(), "Debe seleccionar al menos una emoción", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
}