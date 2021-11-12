package com.example.superandome_appfinal.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.IServices.UsuarioService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.UsuarioServiceImpl;
import com.example.superandome_appfinal.Vistas.Consultante.activity_altaConsultante;
import com.example.superandome_appfinal.Vistas.Consultante.navigationDrawer_consultante;
import com.example.superandome_appfinal.Vistas.Consultante.pregunta_seguridad;
import com.example.superandome_appfinal.Vistas.Consultante.recuperarPassword;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class activity_login extends AppCompatActivity {

    EditText txtnick, txtpass;
    Button btnEntrar;
    ImageView btnface, btninsta, btnwpp;
    UsuarioService userService;

    //Creo Objeto SharedPreferences para utilizar para las Sesiones
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        preferences = this.getSharedPreferences("sesiones",Context.MODE_PRIVATE);
        editor = preferences.edit();

        txtnick = findViewById(R.id.etNickname);
        txtpass = findViewById(R.id.etPassword);
        btnEntrar = findViewById(R.id.btnIngresar);
        btnface = findViewById(R.id.btnFacebook);
        btninsta = findViewById(R.id.btnInstagram);
        btnwpp = findViewById(R.id.btnWhatsapp);

        //Verifico si hay una Sesion iniciada
        if(revisarSesion())
            startActivity(new Intent(this, navigationDrawer_consultante.class));
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
}