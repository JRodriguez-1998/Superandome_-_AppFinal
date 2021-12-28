package com.example.superandome_appfinal.Vistas.Consultante;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superandome_appfinal.Dialogos.dialogoCambiarPass;
import com.example.superandome_appfinal.Dialogos.dialogoErrorFragment;
import com.example.superandome_appfinal.Entidades.PreguntaSeguridad;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.PreguntaSeguridadServiceImpl;
import com.example.superandome_appfinal.Services.UsuarioServiceImpl;

import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class recuperarPassword extends AppCompatActivity {

    Button btnAceptar;
    EditText txtRespuesta,txtUsuario;
    Spinner spinnerPreguntas;
    TextView txtTiulo;
    PreguntaSeguridadServiceImpl preguntaService;
    UsuarioServiceImpl usuarioService;
    List<PreguntaSeguridad> listaPreguntas = new ArrayList<PreguntaSeguridad>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        String nickName = (String) getIntent().getSerializableExtra("nickname");

        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.consultante));

        //getSupportActionBar().hide();


        btnAceptar = (Button) findViewById(R.id.btnAceptarRespuesta);
        txtRespuesta = (EditText) findViewById(R.id.txtRespuesta);
        spinnerPreguntas = (Spinner) findViewById(R.id.spPregunta);
        txtTiulo = (TextView) findViewById(R.id.tvTituloPreguntaSeguridad);
        txtUsuario= (EditText) findViewById(R.id.txtUsuario);
        txtTiulo.setText("Complete con sus datos");
        if(nickName== null){
            txtUsuario.setText("");
        }
        else{
            txtUsuario.setText(nickName);
        }



        try {


        iniciarServicios();

        PreguntaSeguridad pregunta = new PreguntaSeguridad();
        pregunta.setIdPreguntaSeguridad(0);
        pregunta.setDescripcion("Seleccione su pregunta de seguridad");

        listaPreguntas.add(pregunta);

        for(int i = 0; i < preguntaService.getPreguntas().size(); i++){
            listaPreguntas.add(preguntaService.getPreguntas().get(i));
        }

        ArrayAdapter<PreguntaSeguridad> adapter = new ArrayAdapter<PreguntaSeguridad>(this, R.layout.spinner_item_tipoconsejo, listaPreguntas);
        spinnerPreguntas.setAdapter(adapter);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nickName = (String) getIntent().getSerializableExtra("nickname");
                Usuario usuario = usuarioService.getUsuario(txtUsuario.getText().toString());
                PreguntaSeguridad pregunta = (PreguntaSeguridad) spinnerPreguntas.getSelectedItem();

                if(usuario==null){
                    Toast.makeText(recuperarPassword.this,"El Usuario no existe", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(spinnerPreguntas.getSelectedItemPosition() == 0){
                    Toast.makeText(recuperarPassword.this, "Seleccione una pregunta de seguridad", Toast.LENGTH_LONG).show();
                    return;
                }
                if(txtRespuesta.getText().toString().isEmpty()){
                    Toast.makeText(recuperarPassword.this, "Debe responder la respuesta", Toast.LENGTH_LONG).show();
                    return;
                }

                if(pregunta.getIdPreguntaSeguridad() == usuario.getPreguntaSeguridad().getIdPreguntaSeguridad() && txtUsuario.getText().toString().equals(usuario.getNickname().toString()) && txtRespuesta.getText().toString().toLowerCase().equals(usuario.getRespuesta().toString().toLowerCase())){
                        dialogoCambiarPass d = new dialogoCambiarPass(usuario.getNickname().toString());
                        d.show(recuperarPassword.this.getSupportFragmentManager(), "fragment_dialogo_cambiar_pass");

                }else{
                    Toast.makeText(recuperarPassword.this, "Datos incorrectos.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }catch (Exception e) {
            e.printStackTrace();
            dialogoErrorFragment d = new dialogoErrorFragment();
            d.show(this.getSupportFragmentManager(), "fragment_dialogo_errorfragment");
        }}

    public void iniciarServicios(){
        try {
            preguntaService = new PreguntaSeguridadServiceImpl();
            usuarioService = new UsuarioServiceImpl();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            dialogoErrorFragment d = new dialogoErrorFragment();
            d.show(this.getSupportFragmentManager(), "fragment_dialogo_errorfragment");
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

}