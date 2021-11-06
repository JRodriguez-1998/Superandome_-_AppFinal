package com.example.superandome_appfinal.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.superandome_appfinal.Daos.EmocionDaoImpl;
import com.example.superandome_appfinal.Daos.EstadoDaoImpl;
import com.example.superandome_appfinal.Daos.ItemDaoImpl;
import com.example.superandome_appfinal.Daos.TipoArchivoDaoImpl;
import com.example.superandome_appfinal.Daos.TipoConsejoDaoImpl;
import com.example.superandome_appfinal.Helpers.DataDB;
import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.Contenido;
import com.example.superandome_appfinal.Entidades.EmocionUsuario;
import com.example.superandome_appfinal.Entidades.Emocion;
import com.example.superandome_appfinal.Entidades.Encuesta;
import com.example.superandome_appfinal.Entidades.EncuestaUsuario;
import com.example.superandome_appfinal.Entidades.Estado;
import com.example.superandome_appfinal.Entidades.Genero;
import com.example.superandome_appfinal.Entidades.Item;
import com.example.superandome_appfinal.Entidades.ItemUsuario;
import com.example.superandome_appfinal.Entidades.ItemUsuarioDiario;
import com.example.superandome_appfinal.Entidades.PreguntaSeguridad;
import com.example.superandome_appfinal.Entidades.TipoArchivo;
import com.example.superandome_appfinal.Entidades.TipoConsejo;
import com.example.superandome_appfinal.Entidades.TipoUsuario;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.IDaos.EmocionDao;
import com.example.superandome_appfinal.IDaos.EstadoDao;
import com.example.superandome_appfinal.IDaos.ItemDao;
import com.example.superandome_appfinal.IDaos.TipoArchivoDao;
import com.example.superandome_appfinal.IDaos.TipoConsejoDao;
import com.example.superandome_appfinal.R;
import com.j256.ormlite.table.TableUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    private Button btnAvanzar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        creacionTablas();

        btnAvanzar = (Button) findViewById(R.id.btnAvanzar);
    }

    private void creacionTablas() {
        try {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future f = executor.submit(() -> {
                try {
                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), Consejo.class);
                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), Contenido.class);
                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), Emocion.class);
                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), EmocionUsuario.class);
                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), Encuesta.class);
                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), EncuestaUsuario.class);
                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), Estado.class);
                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), Genero.class);
                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), Item.class);
                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), ItemUsuario.class);
                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), ItemUsuarioDiario.class);
                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), PreguntaSeguridad.class);
                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), TipoArchivo.class);
                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), TipoConsejo.class);
                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), TipoUsuario.class);
                    TableUtils.createTableIfNotExists(DataDB.getConnectionSource(), Usuario.class);
                } catch (Exception e) {
                    Log.e("creacionTablas", "Error al crear las tablas", e);
                }
            });

        } catch (Exception e) {
            Log.e("creacionTablas", "Error al crear las tablas", e);
        }
    }

    public void avanzar(View v){
//        Intent intent =  new Intent(this, navigationDrawer_consultante.class);
//        startActivity(intent);

        TestORM();
    }

    private void TestORM() {
        try {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Future<String> f = executor.submit(() -> {
                String result;
                try {
                    ItemDao dao = new ItemDaoImpl();
                    Item item;

                    item = new Item(getResources().getString(R.string.rutinaDieta));
                    dao.create(item);
                    item = new Item(getResources().getString(R.string.rutinaConsumo));
                    dao.create(item);
                    item = new Item(getResources().getString(R.string.rutinaEjercicio));
                    dao.create(item);
                    item = new Item(getResources().getString(R.string.rutinaDescanso));
                    dao.create(item);
                    item = new Item(getResources().getString(R.string.rutinaAmbiente));
                    dao.create(item);
                    item = new Item(getResources().getString(R.string.rutinaHigiene));
                    dao.create(item);
                    item = new Item(getResources().getString(R.string.rutinaEquilibrio));
                    dao.create(item);
                    item = new Item(getResources().getString(R.string.rutinaSocial));
                    dao.create(item);
                    item = new Item(getResources().getString(R.string.rutinaOcio));
                    dao.create(item);
                    item = new Item(getResources().getString(R.string.rutinaNaturaleza));
                    dao.create(item);

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
    }
}