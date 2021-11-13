package com.example.superandome_appfinal.Vistas;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.Contenido;
import com.example.superandome_appfinal.Entidades.Emocion;
import com.example.superandome_appfinal.Entidades.EmocionUsuario;
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
import com.example.superandome_appfinal.Helpers.DataDB;
import com.example.superandome_appfinal.IServices.UsuarioService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.UsuarioServiceImpl;
import com.example.superandome_appfinal.Vistas.Consultante.activity_altaConsultante;
import com.example.superandome_appfinal.Vistas.Consultante.navigationDrawer_consultante;
import com.example.superandome_appfinal.Vistas.Consultante.pregunta_seguridad;
import com.example.superandome_appfinal.Vistas.Consultante.recuperarPassword;
import com.j256.ormlite.table.TableUtils;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class activity_login extends AppCompatActivity {

    EditText txtnick, txtpass;
    Button btnEntrar;
    ImageView btnface, btninsta, btnwpp;
    UsuarioService userService;
    int REQUEST_CODE=200;

    //Creo Objeto SharedPreferences para utilizar para las Sesiones
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        creacionTablas();

        getSupportActionBar().hide();
        preferences = this.getSharedPreferences("sesiones",Context.MODE_PRIVATE);
        editor = preferences.edit();

        txtnick = findViewById(R.id.etNickname);
        txtpass = findViewById(R.id.etPassword);
        btnEntrar = findViewById(R.id.btnIngresar);
        btnface = findViewById(R.id.btnFacebook);
        btninsta = findViewById(R.id.btnInstagram);
        btnwpp = findViewById(R.id.btnWhatsapp);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            verificarPermisos();
        }

        //Verifico si hay una Sesion iniciada
        if(revisarSesion())
            startActivity(new Intent(this, navigationDrawer_consultante.class));
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
                    e.printStackTrace();
                }
            });
            f.get();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Clic en Crear Cuenta
    public void ir_crearCuenta(View view){
        Intent i= new Intent(this, activity_altaConsultante.class);
        startActivity(i);
    }

    //Clic en Olvidé Contraseña
    public void ir_RestablecerContrasenia(View view){
        if(txtnick.getText().toString().equals("")){
            Toast.makeText(this,"Para reestablecer contraseña, ingrese su Nickname", Toast.LENGTH_SHORT).show();
            return;
        }

        String nick = txtnick.getText().toString();

        try
        {
            userService = new UsuarioServiceImpl();
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        Usuario user = userService.getUsuario(nick);
        if(user==null){
            Toast.makeText(this,"El Usuario no existe", Toast.LENGTH_SHORT).show();
            return;
        }

        //                  VER ESTO SI LO USAMOS O NO
        if(user.getTipoUsuario().getIdTipoUsuario() == 2){
            Toast.makeText(this,"Usted es Usuario Profesional, consulte al Director.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(user.getTipoUsuario().getIdTipoUsuario() == 3){
            Toast.makeText(this,"Usted es Usuario Director.", Toast.LENGTH_SHORT).show();
            return;
        }

        //Corregir el acceso a la pantalla. No redirecciona a fragment
//        Intent intent =  new Intent(this, pregunta_seguridad.class);
//        intent.putExtra("nickname",user.getNickname());
//        intent.putExtra("idUser",user.getIdUsuario());
//        startActivity(intent);

        Intent intent =  new Intent(this, recuperarPassword.class);
        intent.putExtra("nickname",user.getNickname());
        startActivity(intent);

    }

    //Clic en Ingresar
    public void Entrar(View view) throws SQLException {
        if (txtnick.getText().toString().equals("") || txtpass.getText().toString().equals("")){
            Toast.makeText(this,"Complete todos los campos.", Toast.LENGTH_SHORT).show();
        }
        else{
            String nick = txtnick.getText().toString();
            String pass = txtpass.getText().toString();

            try
            {
                userService = new UsuarioServiceImpl();
            } catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }

            Usuario user = userService.getUsuario(nick, pass);
            if(user==null){
                Toast.makeText(this,"El nombre de usuario o la contraseña son incorrectos", Toast.LENGTH_SHORT).show();
                return;
            }

            guardarSesion(true, user.getNickname(), user.getIdUsuario(), user.getTipoUsuario().getIdTipoUsuario());
            Intent intent =  new Intent(this, navigationDrawer_consultante.class);
            startActivity(intent);
        }
    }

    public boolean revisarSesion(){
        return this.preferences.getBoolean("sesion",false);
    }

    public void guardarSesion(boolean iniciar, String nick, int idUsuario, int idTipoUsuario){
        editor.putBoolean("sesion",iniciar);
        editor.putString("nickname", nick);
        editor.putInt("idUser", idUsuario);
        editor.putInt("tipoUser", idTipoUsuario);
        editor.apply();
    }

    public void irFacebook(View view){
        String url = "https://www.facebook.com/Soyuz-Salud-Mental-102791508151767/";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

    public void irWhatsapp(View view){
        String url = "https://api.whatsapp.com/send/?phone=573202487093&text=Hola%2C+quiero+ponerme+en+contacto+con+Soyuz&app_absent=0";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

    public void irInstagram(View view){
        String url = "https://www.instagram.com/soyuzsaludmental/";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void verificarPermisos() {

        int PermisoLectura = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int PermisoEscritura = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int PermisoManageMedia = ContextCompat.checkSelfPermission(this,Manifest.permission.MANAGE_MEDIA);
        int PermisoManageStorage = ContextCompat.checkSelfPermission(this,Manifest.permission.MANAGE_EXTERNAL_STORAGE);



        if(PermisoLectura == PackageManager.PERMISSION_GRANTED && PermisoEscritura == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,"Permiso lectura concedido.",Toast.LENGTH_SHORT).show();
        }else{
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE);

        }

        if (PermisoManageMedia == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,"ManageMedia ok",Toast.LENGTH_SHORT).show();
        }
        else{
            requestPermissions(new String[]{Manifest.permission.MANAGE_MEDIA},REQUEST_CODE);
        }

        if (PermisoManageStorage == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,"ManageStorage ok",Toast.LENGTH_SHORT).show();
        }
        else{
            requestPermissions(new String[]{Manifest.permission.MANAGE_EXTERNAL_STORAGE},REQUEST_CODE);
        }


    }


}