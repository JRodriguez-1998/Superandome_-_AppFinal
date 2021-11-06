package com.example.superandome_appfinal.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.superandome_appfinal.Daos.DataDB;
import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.EmocionUsuario;
import com.example.superandome_appfinal.Entidades.Emocion;
import com.example.superandome_appfinal.Entidades.Estado;
import com.example.superandome_appfinal.Entidades.Genero;
import com.example.superandome_appfinal.Entidades.PreguntaSeguridad;
import com.example.superandome_appfinal.Entidades.TipoConsejo;
import com.example.superandome_appfinal.Entidades.TipoUsuario;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.R;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.table.TableUtils;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    private Button btnAvanzar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        creacionTablas();

        btnAvanzar = (Button) findViewById(R.id.btnAvanzar);
    }

    private void creacionTablas() {
        try {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future f = executor.submit(() -> {
                try {
                    Log.i("creacionTablas", "inicio creación tablas");

                    TableUtils.dropTable(DataDB.getConnectionSource(), Consejo.class, true);
                    TableUtils.dropTable(DataDB.getConnectionSource(), Estado.class, true);
                    TableUtils.dropTable(DataDB.getConnectionSource(), Genero.class, true);
                    TableUtils.dropTable(DataDB.getConnectionSource(), PreguntaSeguridad.class, true);
                    TableUtils.dropTable(DataDB.getConnectionSource(), TipoConsejo.class, true);
                    TableUtils.dropTable(DataDB.getConnectionSource(), TipoUsuario.class, true);
                    TableUtils.dropTable(DataDB.getConnectionSource(), Usuario.class, true);

//                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), Contenido.class);
//                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), EmocionUsuario.class);
//                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), Emocion.class);
//                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), EmocionUsuario.class);
//                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), Encuesta.class);
//                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), EncuestaUsuario.class);
//                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), Item.class);
//                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), ItemUsuarioDiario.class);
//                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), TipoArchivo.class);

                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), Consejo.class);
                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), Estado.class);
                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), Genero.class);
                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), PreguntaSeguridad.class);
                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), TipoConsejo.class);
                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), TipoUsuario.class);
                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), Usuario.class);
                } catch (Exception e) {
                    Log.e("creacionTablas", "Error al crear las tablas", e);
                }
            });

            Log.i("creacionTablas", "fin creación tablas");
        } catch (Exception e) {
            Log.e("creacionTablas", "Error al crear las tablas", e);
        }
    }

    public  void avanzar(View v){
        try {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<String> f = executor.submit(() -> {
                String result;
                try {
                    Dao<EmocionUsuario, Integer> dao = DaoManager.createDao(DataDB.getConnectionSource(), EmocionUsuario.class);
                    TableUtils.createTable(dao);

                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(1);
                    Emocion emocion = new Emocion();
                    emocion.setIdEmocion(1);

                    EmocionUsuario emocionUsuario;
                    emocionUsuario = new EmocionUsuario(usuario, new Date(), emocion);
                    dao.create(emocionUsuario);
                    emocionUsuario = new EmocionUsuario(usuario, new Date(), emocion);
                    dao.create(emocionUsuario);

                    result = "Consejo guardado con éxito";
                } catch (Exception e) {
                    result = "Ha ocurrido un error al guardar el consejo";
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