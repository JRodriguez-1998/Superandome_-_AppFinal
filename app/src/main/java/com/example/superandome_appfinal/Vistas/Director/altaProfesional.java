package com.example.superandome_appfinal.Vistas.Director;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superandome_appfinal.Entidades.Genero;
import com.example.superandome_appfinal.Entidades.TipoUsuario;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.IServices.GeneroService;
import com.example.superandome_appfinal.IServices.UsuarioService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.GeneroServiceImpl;
import com.example.superandome_appfinal.Services.UsuarioServiceImpl;


import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class altaProfesional extends Fragment {

    EditText nickname, fechaNac, pass, confirPass;
    TextView idAlta, fechaHoy;
    Button btnRegistrar;
    Spinner spinnerGenero;

    UsuarioService usuarioService;
    GeneroService generoService;

    DatePickerDialog.OnDateSetListener setListener;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinnerGenero = (Spinner) view.findViewById(R.id.spinnerGeneroProfe);
        //idAlta = (TextView) view.findViewById(R.id.tvIdUsuario);
        fechaHoy = (TextView) view.findViewById(R.id.tvFechaHOY);

        nickname = (EditText) view.findViewById(R.id.editTextNicknameProf);
        fechaNac = (EditText) view.findViewById(R.id.editTextNaciProf);
        pass = (EditText) view.findViewById(R.id.editTextPassProf);
        confirPass = (EditText) view.findViewById(R.id.editTextConfPassProf);

        btnRegistrar = (Button) view.findViewById(R.id.btnAceptarAlta);
        try {


        mostrarFechaHoy();
        cargarCalendario();
        cargarSpinner();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    RegistrarProfesional();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Ha ocurrido un error al inicializar la pantalla", Toast.LENGTH_SHORT).show();
        }}

    public void mostrarFechaHoy(){
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        //String fecha = formatter.format(new Date());
        fechaHoy.setText(formatter.format(new Date()));
    }

    public void cargarCalendario(){
        //Creación de Calendario en el EditText tipo Date
        Calendar calendar = Calendar.getInstance();
        final int anio = calendar.get(Calendar.YEAR);
        final int mes = calendar.get(Calendar.MONTH);
        final int dia = calendar.get(Calendar.DAY_OF_MONTH);

        //Seteo un maximo de Fecha para que sean solamente mayores de edad
        calendar.add(Calendar.YEAR,-18);

        fechaNac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),android.R.style.Theme_Holo_Dialog_MinWidth
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
                fechaNac.setText(fecha);
            }
        };
    }

    public void cargarSpinner(){
        try{
            generoService = new GeneroServiceImpl();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //CARGA DE SPINNER DE GENEROS
        Genero gen = new Genero(0,"Seleccione Género");
        List<Genero> listGeneros = new ArrayList<Genero>();
        listGeneros.add(gen);
        for(int i = 0; i< generoService.getGeneros().size(); i ++){
            listGeneros.add(generoService.getGeneros().get(i));
        }
        ArrayAdapter<Genero> adapter = new ArrayAdapter<Genero>(getActivity(), R.layout.spinner_generos, listGeneros);
        spinnerGenero.setAdapter(adapter);
    }

    //Clic en Registrar
    public void RegistrarProfesional() throws ParseException {

        //Verifico que los campos no esten vacios
        if(nickname.getText().toString().equals("") || fechaNac.getText().toString().equals("") || pass.getText().toString().equals("") || confirPass.getText().toString().equals("")){
            Toast.makeText(getActivity(),"Campos incompletos, por favor ingrese todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        //Instancio el Servicio del Usuario
        try
        {
            usuarioService = new UsuarioServiceImpl();
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        //Consulto si el Usuario ya existe
        String nick = nickname.getText().toString();
        Usuario user = usuarioService.getUsuario(nick);
        if(user!=null){
            Toast.makeText(getActivity(),"El Usuario ya existe", Toast.LENGTH_SHORT).show();
            return;
        }

        //Verifico si las contraseñas coinciden
        if(!pass.getText().toString().equals(confirPass.getText().toString())){
            Toast.makeText(getActivity(),"Las Contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        //Verifico que haya seleccionado el Genero
        if(spinnerGenero.getSelectedItemPosition() == 0){
            Toast.makeText(getActivity(),"Seleccione el Genero", Toast.LENGTH_LONG).show();
            return;
        }

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Usuario usuarioNuevo = new Usuario();

        TipoUsuario tUser = new TipoUsuario(2, "Profesional");
        Genero genero = (Genero) spinnerGenero.getSelectedItem();

        usuarioNuevo.setNickname(nickname.getText().toString());
        usuarioNuevo.setPassword(pass.getText().toString());
        usuarioNuevo.setHabilitado(true);
        usuarioNuevo.setFechaAlta(new Date());
        usuarioNuevo.setFechaNac(formatter.parse(fechaNac.getText().toString()));
        usuarioNuevo.setTipoUsuario(tUser);
        usuarioNuevo.setGenero(genero);


        if(usuarioService.guardar(usuarioNuevo)){
            Toast.makeText(getActivity(),"Usuario Profesional Registrado", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getActivity(),"ERROR", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alta_profesional, container, false);
    }
}