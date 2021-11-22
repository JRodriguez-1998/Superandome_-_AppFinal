package com.example.superandome_appfinal.Vistas.Director;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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

import com.example.superandome_appfinal.Constantes.TipoUsuarioEnum;
import com.example.superandome_appfinal.Entidades.Genero;
import com.example.superandome_appfinal.Entidades.TipoUsuario;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.IServices.GeneroService;
import com.example.superandome_appfinal.IServices.TipoUsuarioService;
import com.example.superandome_appfinal.IServices.UsuarioService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.GeneroServiceImpl;
import com.example.superandome_appfinal.Services.TipoUsuarioServiceImpl;
import com.example.superandome_appfinal.Services.UsuarioServiceImpl;


import java.security.MessageDigest;
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
    TipoUsuarioService tipoUsuarioService;
    GeneroService generoService;

    DatePickerDialog.OnDateSetListener setListener;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            spinnerGenero = (Spinner) view.findViewById(R.id.spinnerGeneroProfe);
            //idAlta = (TextView) view.findViewById(R.id.tvIdUsuario);
            fechaHoy = (TextView) view.findViewById(R.id.tvFechaHOY);

            nickname = (EditText) view.findViewById(R.id.editTextNicknameProf);
            fechaNac = (EditText) view.findViewById(R.id.editTextNaciProf);
            pass = (EditText) view.findViewById(R.id.editTextPassProf);
            confirPass = (EditText) view.findViewById(R.id.editTextConfPassProf);

            btnRegistrar = (Button) view.findViewById(R.id.btnAceptarAlta);

            usuarioService = new UsuarioServiceImpl();
            tipoUsuarioService = new TipoUsuarioServiceImpl();
            generoService = new GeneroServiceImpl();

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
                        Toast.makeText(getContext(), "Error al registrar profesional", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Ha ocurrido un error al inicializar la pantalla", Toast.LENGTH_SHORT).show();
        }
    }

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

        TipoUsuario tUser = tipoUsuarioService.getTipoUsuarioById(TipoUsuarioEnum.PROFESIONAL.getTipo());
        Genero genero = (Genero) spinnerGenero.getSelectedItem();

        usuarioNuevo.setNickname(nickname.getText().toString());
        usuarioNuevo.setPassword(doHash(pass.getText().toString()));
        usuarioNuevo.setHabilitado(true);
        usuarioNuevo.setFechaAlta(new Date());
        usuarioNuevo.setFechaNac(formatter.parse(fechaNac.getText().toString()));
        usuarioNuevo.setTipoUsuario(tUser);
        usuarioNuevo.setGenero(genero);


        if(usuarioService.guardar(usuarioNuevo)){
            Toast.makeText(getActivity(),"Usuario Profesional Registrado", Toast.LENGTH_LONG).show();
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_navigation_drawer_consultante);
            navController.navigate(R.id.nav_homeDirector);
        }else{
            Toast.makeText(getActivity(),"ERROR", Toast.LENGTH_LONG).show();
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
            Toast.makeText(getContext(),"Error al hashear", Toast.LENGTH_SHORT).show();
        }
        return "";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alta_profesional, container, false);
    }
}