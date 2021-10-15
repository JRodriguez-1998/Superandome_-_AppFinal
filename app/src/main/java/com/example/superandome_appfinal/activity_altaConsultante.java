package com.example.superandome_appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class activity_altaConsultante extends AppCompatActivity {

    Spinner spinnerGenero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_consultante);

        getSupportActionBar().hide();

        spinnerGenero = (Spinner) findViewById(R.id.spinnerGeneroConsultante);

        String [] generos = {"Masculino","Femenino","Otros"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_generos, generos);
        spinnerGenero.setAdapter(adapter);
    }
}