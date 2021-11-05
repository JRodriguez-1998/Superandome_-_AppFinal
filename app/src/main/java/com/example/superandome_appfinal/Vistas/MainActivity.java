package com.example.superandome_appfinal.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.superandome_appfinal.Daos.DataDB;
import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.Estado;
import com.example.superandome_appfinal.Entidades.TipoConsejo;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Vistas.Consultante.navigationDrawer_consultante;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    private Button btnAvanzar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAvanzar = (Button) findViewById(R.id.btnAvanzar);
    }

    public  void avanzar(View v){
        try {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<String> f = executor.submit(() -> {
                String result;
                try {
                    JdbcConnectionSource connectionSource = new JdbcConnectionSource(DataDB.urlMySQL, DataDB.user, DataDB.pass);
                    Dao<Consejo, Integer> dao = DaoManager.createDao(connectionSource, Consejo.class);
                    Dao<TipoConsejo, Integer> daoTipoConsejo = DaoManager.createDao(connectionSource, TipoConsejo.class);
                    Dao<Estado, Integer> daoEstado = DaoManager.createDao(connectionSource, Estado.class);
//                    TableUtils.createTable(dao);
                    TipoConsejo tipoConsejo;
                    Consejo consejo;
                    Estado estado = daoEstado.queryForId(1);

                    tipoConsejo = daoTipoConsejo.queryForId(1);
                    consejo = new Consejo("Consejo general", tipoConsejo);
                    consejo.setEstado(estado);
                    dao.create(consejo);

                    tipoConsejo = daoTipoConsejo.queryForId(2);
                    consejo = new Consejo("Consejo asco", tipoConsejo);
                    consejo.setEstado(estado);
                    dao.create(consejo);

                    result = "Consejo guardado con éxito";
                    connectionSource.close();
                } catch (Exception e) {
                    result ="Ha ocurrido un error al guardar el consejo";
                }
                return result;
            });
            String mensaje = f.get();
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this,"Ha ocurrido un error", Toast.LENGTH_LONG).show();
        }

//        Intent intent =  new Intent(this, navigationDrawer_consultante.class);
//        startActivity(intent);
    }
}