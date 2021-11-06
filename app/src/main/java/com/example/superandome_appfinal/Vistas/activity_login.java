package com.example.superandome_appfinal.Vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Vistas.Consultante.activity_altaConsultante;
import com.example.superandome_appfinal.Vistas.Consultante.navigationDrawer_consultante;
import com.example.superandome_appfinal.Vistas.Consultante.pregunta_seguridad;

public class activity_login extends AppCompatActivity {

    EditText txtnick, txtpass;
    Button btnEntrar;
    ImageView btnface, btninsta, btnwpp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        txtnick = findViewById(R.id.etNickname);
        txtpass = findViewById(R.id.etPassword);
        btnEntrar = findViewById(R.id.btnIngresar);
        btnface = findViewById(R.id.btnFacebook);
        btninsta = findViewById(R.id.btnInstagram);
        btnwpp = findViewById(R.id.btnWhatsapp);
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
        }
        else{
            //Buscar Usuario si existe y luego enviarlo al siguiente activity para reestablecer contraseña
            //Usuario user = verificarUsuario(nick);
            Intent i= new Intent(this, pregunta_seguridad.class);
            //intent.putExtra("usuario", user);
            startActivity(i);
        }
    }

    //Clic en Ingresar
    public void Entrar(View view){
        if (txtnick.getText().toString().equals("") || txtpass.getText().toString().equals("")){
            Toast.makeText(this,"Complete todos los campos.", Toast.LENGTH_SHORT).show();
        }
        else{
                String nick = txtnick.getText().toString();
                String pass = txtpass.getText().toString();

                    //ACÁ CREAR MÉTODO PARA VERIFICAR USUARIO
                //Usuario user = verificarUsuario(nick, pass);

                    //Si los datos son correctos, prosigo
                if(nick.equals("joseMaster") && pass.equals("1905")){
                    Toast.makeText(this, "Usuario Correcto", Toast.LENGTH_SHORT).show();

                    Intent intent =  new Intent(this, navigationDrawer_consultante.class);
                        //CUANDO TENGAMOS EL OBJETO USUARIO OBTENIDO, PODEMOS PASAR EL OBJETO
                    //intent.putExtra("usuario", user);
                    intent.putExtra("tipoUsuario",1);
                    startActivity(intent);
                }
                else if(nick.equals("mauriCrack") && pass.equals("La12")){
                    Toast.makeText(this, "Usuario Correcto", Toast.LENGTH_SHORT).show();

                    Intent intent =  new Intent(this, navigationDrawer_consultante.class);
                    //CUANDO TENGAMOS EL OBJETO USUARIO OBTENIDO, PODEMOS PASAR EL OBJETO
                    //intent.putExtra("usuario", user);
                    intent.putExtra("tipoUsuario",2);
                    startActivity(intent);
                }
                else if(nick.equals("juanHacker") && pass.equals("1234")){
                    Toast.makeText(this, "Usuario Correcto", Toast.LENGTH_SHORT).show();

                    Intent intent =  new Intent(this, navigationDrawer_consultante.class);
                    //CUANDO TENGAMOS EL OBJETO USUARIO OBTENIDO, PODEMOS PASAR EL OBJETO
                    //intent.putExtra("usuario", user);
                    intent.putExtra("tipoUsuario",3);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(this, "Usuario y/o Contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
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
        String url = "https://www.instagram.com/soyuzsaludmental/";
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
}