package com.example.superandome_appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class activity_altaConsultante extends AppCompatActivity {

    Spinner spinnerGenero, spinnerPreguntas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_consultante);

        getSupportActionBar().hide();

        spinnerGenero = (Spinner) findViewById(R.id.spinnerGeneroConsultante);
        spinnerPreguntas = (Spinner) findViewById(R.id.spinnerPreguntas);

        String [] generos = {"Seleccione Género","Masculino","Femenino","Otros"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_generos, generos);
        spinnerGenero.setAdapter(adapter);

        String [] preguntas = {
                                "Seleccione Pregunta de Seguridad",
                                "¿Cómo se llamaba tu primera mascota?",
                                "¿Cúal fue la primera película que viste en el cine?",
                                "¿Cuál es tu equipo de Fútbol favorito?",
                                "¿En qué Ciudad naciste?",
                                "¿En qué fecha descendió RiBer Plate?"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.spinner_generos, preguntas);
        spinnerPreguntas.setAdapter(adapter2);
    }
}