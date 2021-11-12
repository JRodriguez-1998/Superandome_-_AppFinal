package com.example.superandome_appfinal.Vistas.Consultante;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.superandome_appfinal.Dialogos.dialogo;
import com.example.superandome_appfinal.Dialogos.dialogoCambiarPass;
import com.example.superandome_appfinal.Dialogos.dialogoConfHorario;
import com.example.superandome_appfinal.Entidades.PreguntaSeguridad;
import com.example.superandome_appfinal.Entidades.TipoConsejo;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.IServices.PreguntaSeguridadService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.EmocionServiceImpl;
import com.example.superandome_appfinal.Services.EmocionUsuarioServiceImpl;
import com.example.superandome_appfinal.Services.PreguntaSeguridadServiceImpl;
import com.example.superandome_appfinal.Services.UsuarioServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class recuperarPassword extends AppCompatActivity {

    Button btnAceptar;
    EditText txtRespuesta;
    Spinner spinnerPreguntas;
    PreguntaSeguridadServiceImpl preguntaService;
    UsuarioServiceImpl usuarioService;
    List<PreguntaSeguridad> listaPreguntas = new ArrayList<PreguntaSeguridad>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_password);

        btnAceptar = (Button) findViewById(R.id.btnAceptarRespuesta);
        txtRespuesta = (EditText) findViewById(R.id.txtRespuesta);
        spinnerPreguntas = (Spinner) findViewById(R.id.spPregunta);

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
                Usuario usuario = usuarioService.getUsuario(nickName);
                PreguntaSeguridad pregunta = (PreguntaSeguridad) spinnerPreguntas.getSelectedItem();

                if(spinnerPreguntas.getSelectedItemPosition() == 0){
                    Toast.makeText(recuperarPassword.this, "Seleccione una pregunta de seguridad", Toast.LENGTH_LONG).show();
                    return;
                }
                if(txtRespuesta.getText().toString().isEmpty()){
                    Toast.makeText(recuperarPassword.this, "Debe responder la respuesta", Toast.LENGTH_LONG).show();
                    return;
                }

                if(pregunta.getIdPreguntaSeguridad() == usuario.getPreguntaSeguridad().getIdPreguntaSeguridad()){
                    if(txtRespuesta.getText().toString().toLowerCase().equals(usuario.getRespuesta().toString().toLowerCase())){

                        dialogoCambiarPass d = new dialogoCambiarPass(usuario.getNickname().toString());
                        d.show(recuperarPassword.this.getSupportFragmentManager(), "fragment_dialogo_cambiar_pass");
                    }else{
                        Toast.makeText(recuperarPassword.this, "RESPUESTA INCORRECTA", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(recuperarPassword.this, "Revise la pregunta seleccionada", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void iniciarServicios(){
        try {
            preguntaService = new PreguntaSeguridadServiceImpl();
            usuarioService = new UsuarioServiceImpl();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}