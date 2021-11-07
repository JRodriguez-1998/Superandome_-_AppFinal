package com.example.superandome_appfinal.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Vistas.Consultante.navigationDrawer_consultante;

public class MainActivity extends AppCompatActivity {

    private Button btnAvanzar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAvanzar = (Button) findViewById(R.id.btnAvanzar);
    }

    public void avanzar(View v){
        Intent intent =  new Intent(this, navigationDrawer_consultante.class);
        startActivity(intent);
    }

}