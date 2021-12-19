package com.example.superandome_appfinal.Vistas;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.work.Data;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.example.superandome_appfinal.Helpers.SessionManager;
import com.example.superandome_appfinal.IServices.UsuarioService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.UsuarioServiceImpl;
import com.example.superandome_appfinal.Vistas.Consultante.activity_altaConsultante;
import com.example.superandome_appfinal.Vistas.Consultante.navigationDrawer_consultante;
import com.example.superandome_appfinal.Vistas.Consultante.recuperarPassword;
import com.j256.ormlite.table.TableUtils;


import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class activity_login extends AppCompatActivity {

    EditText txtnick, txtpass;
    Button btnEntrar;
    ImageView btnface, btninsta, btnwpp;
    UsuarioService userService;
    int REQUEST_CODE=200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.profesional));

        try {
            setContentView(R.layout.activity_login);

//            creacionTablas();

            getSupportActionBar().hide();

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
            if (SessionManager.tieneSesion(this))
                startActivity(new Intent(this, navigationDrawer_consultante.class));

            //Captura el botón Enter para que active la "función del botón"
            txtpass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                    if(actionId == EditorInfo.IME_ACTION_DONE
                            || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                            || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER) {

                        try {
                            Entrar();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        return true;
                    }
                    return false;
                }
            });

            btnEntrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Entrar();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Ha ocurrido un error al inicializar la pantalla", Toast.LENGTH_SHORT).show();
        }
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
                    Toast.makeText(this, "Error al crear tabla", Toast.LENGTH_SHORT).show();
                }
            });
            f.get();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error en creacion de tablas", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, "Error al inicializar usuarioService", Toast.LENGTH_SHORT).show();
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
    public void Entrar() throws SQLException {
        if (txtnick.getText().toString().equals("") || txtpass.getText().toString().equals("")){
            Toast.makeText(this,"Complete todos los campos.", Toast.LENGTH_SHORT).show();
        }
        else{
            String nick = txtnick.getText().toString();
            String pass = doHash(txtpass.getText().toString());

            try
            {
                userService = new UsuarioServiceImpl();
            } catch (SQLException throwables)
            {
                throwables.printStackTrace();
                Toast.makeText(this, "Error al inicializar usuarioService", Toast.LENGTH_SHORT).show();
            }

            Usuario user = userService.getUsuario(nick, pass);
            if(user==null){
                Toast.makeText(this,"El nombre de usuario o la contraseña son incorrectos", Toast.LENGTH_SHORT).show();
                return;
            }

            SessionManager.guardarUsuario(this, user);

            Intent intent =  new Intent(this, navigationDrawer_consultante.class);
            startActivity(intent);
        }
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
        String url = "https://instagram.com/psicologajulietvelez";
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
            //Toast.makeText(this,"Permiso lectura concedido.",Toast.LENGTH_SHORT).show();
        }else{
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_CODE);

        }

        if (PermisoManageMedia == PackageManager.PERMISSION_GRANTED){
           // Toast.makeText(this,"ManageMedia ok",Toast.LENGTH_SHORT).show();
        }
        else{
            requestPermissions(new String[]{Manifest.permission.MANAGE_MEDIA},REQUEST_CODE);
        }

        if (PermisoManageStorage == PackageManager.PERMISSION_GRANTED){
           // Toast.makeText(this,"ManageStorage ok",Toast.LENGTH_SHORT).show();
        }
        else{
            requestPermissions(new String[]{Manifest.permission.MANAGE_EXTERNAL_STORAGE},REQUEST_CODE);
        }


    }

    public String doHash(String password){
        try {
            MessageDigest msgDiggest = MessageDigest.getInstance("MD5");
            msgDiggest.update(password.getBytes());
            byte[] resultado = msgDiggest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : resultado){
                sb.append(String.format("%02x",b));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this,"Error al hashear", Toast.LENGTH_SHORT).show();
        }
        return "";
    }

    private String geneteKey(){
        return UUID.randomUUID().toString();
    }

    private Data GuardarData(String titulo, String detalle, int idNoti){
        return new Data.Builder()
                .putString("titulo", titulo)
                .putString("detalle", detalle)
                .putInt("idNoti", idNoti).build();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode==event.KEYCODE_BACK) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        String tag = geneteKey();
//        int random = (int) (Math.random() * 50 + 1);
//
//        Data data = GuardarData("¡Nueva notificación superandome!", "Ingresar diariamente para una mejor seguimiento", random);
//        Workmanagernoti.GuardarNoti(10 * 1000,  data, tag);
//    }
}