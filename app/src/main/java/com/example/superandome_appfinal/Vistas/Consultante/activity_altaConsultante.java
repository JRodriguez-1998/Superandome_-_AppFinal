package com.example.superandome_appfinal.Vistas.Consultante;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.superandome_appfinal.Entidades.Genero;
import com.example.superandome_appfinal.Entidades.PreguntaSeguridad;
import com.example.superandome_appfinal.Entidades.TipoUsuario;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class activity_altaConsultante extends AppCompatActivity {

    Spinner spinnerGenero, spinnerPreguntas;
    EditText txtNickname, txtFechaNac, txtRespuesta, txtPass, txtConfirmPass;
    Button btnGuardar;

    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_consultante);

        getSupportActionBar().hide();

        spinnerGenero = (Spinner) findViewById(R.id.spinnerGeneroConsultante);
        spinnerPreguntas = (Spinner) findViewById(R.id.spinnerPreguntas);

        String [] generos = {"Masculino","Femenino","Otros"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_generos, generos);
        spinnerGenero.setAdapter(adapter);

        String [] preguntas = {
                                "¿Cómo se llamaba tu primera mascota?",
                                "¿Cúal fue la primera película que viste en el cine?",
                                "¿Cuál es tu equipo de Fútbol favorito?",
                                "¿En qué Ciudad naciste?",
                                "¿En qué fecha descendió RiBer Plate?"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.spinner_generos, preguntas);
        spinnerPreguntas.setAdapter(adapter2);

        //Creación de Calendario en el EditText tipo Date
        txtFechaNac = (EditText) findViewById(R.id.editTextNacConsultante);

        Calendar calendar = Calendar.getInstance();
        final int anio = calendar.get(Calendar.YEAR);
        final int mes = calendar.get(Calendar.MONTH);
        final int dia = calendar.get(Calendar.DAY_OF_MONTH);

        txtFechaNac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(activity_altaConsultante.this,android.R.style.Theme_Holo_Dialog_MinWidth
                        ,setListener,anio,mes,dia);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
                mes = mes+1;
                String fecha = dia + "/" + mes + "/" + anio;
                txtFechaNac.setText(fecha);
            }
        };
    }

    //Clic en Registrar
    public void Registrar(View view) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        if(txtNickname.getText().toString().equals("") || txtFechaNac.getText().toString().equals("") || txtRespuesta.getText().toString().equals("") || txtPass.getText().toString().equals("") || txtConfirmPass.getText().toString().equals("")){
            Toast.makeText(this,"Campos incompletos, por favor ingrese todos los campos.", Toast.LENGTH_SHORT).show();
        }
        else{

            //Aqui consultar si el UsuarioExiste
            //Usuario user = verificarUsuario(txtNickname.getText().toString());

            if(txtPass.getText().toString().equals(txtConfirmPass.getText().toString())){
                Usuario user = new Usuario();
                TipoUsuario tUser = new TipoUsuario(1, "CONSULTANTE");
                    int genID = spinnerGenero.getSelectedItemPosition();
                    String gen = spinnerGenero.getSelectedItem().toString();
                Genero genero = new Genero(genID, gen);
                    int pregID = spinnerPreguntas.getSelectedItemPosition();
                    String preg = spinnerPreguntas.getSelectedItem().toString();
                PreguntaSeguridad pregSeg = new PreguntaSeguridad(pregID, preg);

                user.setNickname(txtNickname.getText().toString());
                user.setTipoUsuario(tUser);
                user.setPassword(txtPass.getText().toString());
                user.setHabilitado(true);
                user.setFechaAlta(formatter.parse(String.valueOf(new Date())));
                user.setFechaNac(formatter.parse(txtFechaNac.getText().toString()));
                user.setGenero(genero);
                user.setPreguntaSeguridad(pregSeg);
                user.setRespuesta(txtRespuesta.getText().toString());

                Toast.makeText(this,"Usuario guardado.", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,"Las Contraseñas no coinciden.", Toast.LENGTH_SHORT).show();
            }

        }
    }
}