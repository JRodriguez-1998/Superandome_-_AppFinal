package com.example.superandome_appfinal.Vistas.Consultante;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.superandome_appfinal.Constantes.TipoUsuarioEnum;
import com.example.superandome_appfinal.Dialogos.dialogoUserConsultanteRegistrado;
import com.example.superandome_appfinal.Entidades.Genero;
import com.example.superandome_appfinal.Entidades.PreguntaSeguridad;
import com.example.superandome_appfinal.Entidades.TipoUsuario;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.IServices.GeneroService;
import com.example.superandome_appfinal.IServices.PreguntaSeguridadService;
import com.example.superandome_appfinal.IServices.TipoUsuarioService;
import com.example.superandome_appfinal.IServices.UsuarioService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.GeneroServiceImpl;
import com.example.superandome_appfinal.Services.PreguntaSeguridadServiceImpl;
import com.example.superandome_appfinal.Services.TipoUsuarioServiceImpl;
import com.example.superandome_appfinal.Services.UsuarioServiceImpl;
import com.example.superandome_appfinal.Vistas.activity_login;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class activity_altaConsultante extends AppCompatActivity {

    Spinner spinnerGenero, spinnerPreguntas;
    EditText txtNickname, txtFechaNac, txtRespuesta, txtPass, txtConfirmPass;

    UsuarioService usuarioService;
    TipoUsuarioService tipoUsuarioService;
    GeneroService generoService;
    PreguntaSeguridadService preguntaSeguridadService;

    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_consultante);

        getSupportActionBar().hide();

        txtNickname = (EditText) findViewById(R.id.editTextNameConsultante);
        txtRespuesta = (EditText) findViewById(R.id.editTextRespuestaSeguridad);
        txtPass = (EditText) findViewById(R.id.editTextPassConsultante);
        txtConfirmPass = (EditText) findViewById(R.id.editTextConfPassConsultante);
        spinnerGenero = (Spinner) findViewById(R.id.spinnerGeneroConsultante);
        spinnerPreguntas = (Spinner) findViewById(R.id.spinnerPreguntas);

        try{
            usuarioService = new UsuarioServiceImpl();
            tipoUsuarioService = new TipoUsuarioServiceImpl();
            generoService = new GeneroServiceImpl();
            preguntaSeguridadService = new PreguntaSeguridadServiceImpl();

            //CARGA DE SPINNER DE GENEROS
            Genero gen = new Genero(0,"Seleccione Género");
            List<Genero> listGeneros = new ArrayList<Genero>();
            listGeneros.add(gen);
            for(int i = 0; i< generoService.getGeneros().size(); i ++){
                listGeneros.add(generoService.getGeneros().get(i));
            }
            ArrayAdapter<Genero> adapter = new ArrayAdapter<Genero>(this, R.layout.spinner_generos, listGeneros);
            spinnerGenero.setAdapter(adapter);

            //CARGA DE SPINNER DE Preguntas
            PreguntaSeguridad pregunta = new PreguntaSeguridad(0,"Seleccione Pregunta de Seguridad");
            List<PreguntaSeguridad> listPreguntas = new ArrayList<PreguntaSeguridad>();
            listPreguntas.add(pregunta);
            for(int i = 0; i< preguntaSeguridadService.getPreguntas().size(); i ++){
                listPreguntas.add(preguntaSeguridadService.getPreguntas().get(i));
            }
            ArrayAdapter<PreguntaSeguridad> adapter2 = new ArrayAdapter<PreguntaSeguridad>(this, R.layout.spinner_generos, listPreguntas);
            spinnerPreguntas.setAdapter(adapter2);

            //Creación de Calendario en el EditText tipo Date
            txtFechaNac = (EditText) findViewById(R.id.editTextNacConsultante);

            Calendar calendar = Calendar.getInstance();
            final int anio = calendar.get(Calendar.YEAR);
            final int mes = calendar.get(Calendar.MONTH);
            final int dia = calendar.get(Calendar.DAY_OF_MONTH);

            //Seteo un maximo de Fecha para que sean solamente mayores de edad
            calendar.add(Calendar.YEAR,-18);

            txtFechaNac.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(activity_altaConsultante.this,android.R.style.Theme_Holo_Dialog_MinWidth
                            ,setListener,anio,mes,dia);
                    datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis()-1000);
                    datePickerDialog.show();
                }
            });
            setListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
                    mes = mes+1;
                    //String fecha = dia + "/" + mes + "/" + anio;
                    String fecha = anio + "-" + mes + "-" + dia;
                    txtFechaNac.setText(fecha);
                }
            };

        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
            Toast.makeText(this, "Ha ocurrido un error al inicializar la pantalla", Toast.LENGTH_SHORT).show();
        }
    }

    //Clic en Registrar
    public void Registrar(View view) throws ParseException {

        //Verifico que los campos no esten vacios
        if(txtNickname.getText().toString().equals("") || txtFechaNac.getText().toString().equals("") || txtRespuesta.getText().toString().equals("") || txtPass.getText().toString().equals("") || txtConfirmPass.getText().toString().equals("")){
            Toast.makeText(this,"Campos incompletos, por favor ingrese todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        //Consulto si el Usuario ya existe
        String nick = txtNickname.getText().toString();
        Usuario user = usuarioService.getUsuario(nick);
        if(user!=null){
            Toast.makeText(this,"El Usuario ya existe", Toast.LENGTH_SHORT).show();
            return;
        }

        //Verifico si las contraseñas coinciden
        if(!txtPass.getText().toString().equals(txtConfirmPass.getText().toString())){
            Toast.makeText(this,"Las Contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        //Verifico que haya seleccionado el Genero
        if(spinnerGenero.getSelectedItemPosition() == 0){
            Toast.makeText(this,"Seleccione el Genero", Toast.LENGTH_LONG).show();
            return;
        }

        //Verifico que haya elegido la pregunta
        if(spinnerPreguntas.getSelectedItemPosition() == 0){
            Toast.makeText(this,"Elija una pregunta de Seguridad", Toast.LENGTH_LONG).show();
            return;
        }

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Usuario usuarioNuevo = new Usuario();

        TipoUsuario tUser = tipoUsuarioService.getTipoUsuarioById(TipoUsuarioEnum.CONSULTANTE.getTipo());
        Genero genero = (Genero) spinnerGenero.getSelectedItem();
        PreguntaSeguridad pregSeg = (PreguntaSeguridad)spinnerPreguntas.getSelectedItem();

        usuarioNuevo.setNickname(txtNickname.getText().toString());
        usuarioNuevo.setPassword(doHash(txtPass.getText().toString()));
        usuarioNuevo.setFechaNac(formatter.parse(txtFechaNac.getText().toString()));


        usuarioNuevo.setRespuesta(txtRespuesta.getText().toString());
        usuarioNuevo.setTipoUsuario(tUser);
        usuarioNuevo.setGenero(genero);
        usuarioNuevo.setPreguntaSeguridad(pregSeg);


        if(usuarioService.guardar(usuarioNuevo)){
            dialogoUserConsultanteRegistrado d = new dialogoUserConsultanteRegistrado();
            d.show(this.getSupportFragmentManager(), "fragment_dialogo_usuario_registrado");
        }else{
            Toast.makeText(this,"ERROR", Toast.LENGTH_LONG).show();
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