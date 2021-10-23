package com.example.superandome_appfinal.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Vistas.Consultante.activity_altaConsultante;

public class activity_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
    }

    //Clic en Crear Cuenta
    public void ir_crearCuenta(View view){
        Intent i= new Intent(this, activity_altaConsultante.class);
        startActivity(i);
    }

    //Clic en Olvidé Contraseña
    public void ir_RestablecerContrasenia(View view){
        //Intent i= new Intent(this,.class);
        //startActivity(i);
    }
}