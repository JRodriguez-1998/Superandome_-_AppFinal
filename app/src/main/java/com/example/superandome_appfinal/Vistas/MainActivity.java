package com.example.superandome_appfinal.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.util.FloatProperty;
import android.view.View;
import android.widget.Button;

import com.example.superandome_appfinal.Daos.EmocionUsuarioDaoImpl;
import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.EmocionUsuario;
import com.example.superandome_appfinal.IDaos.EmocionUsuarioDao;
import com.example.superandome_appfinal.IServices.EmocionUsuarioService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.EmocionUsuarioServiceImpl;
import com.example.superandome_appfinal.Vistas.Consultante.navigationDrawer_consultante;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RawRowMapper;
import com.j256.ormlite.field.DataType;

import java.io.IOException;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private Button btnAvanzar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAvanzar = (Button) findViewById(R.id.btnAvanzar);
    }

    public void avanzar(View v){
//        Intent intent =  new Intent(this, navigationDrawer_consultante.class);
//        startActivity(intent);
//        Test();
        Test2();
    }

    public void Test() {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future f = executor.submit(() -> {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void Test2() {
        try {
            EmocionUsuarioService service = new EmocionUsuarioServiceImpl();
            Map<Integer, Float> map = service.getReporteMensualEmocion(1, 1);
            int x = 1;
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

    }
}