package com.example.superandome_appfinal.Dialogos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.superandome_appfinal.Constantes.EstadoEnum;
import com.example.superandome_appfinal.Entidades.Contenido;
import com.example.superandome_appfinal.Entidades.Estado;
import com.example.superandome_appfinal.IServices.ContenidoService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.ContenidoServiceImpl;

import java.sql.SQLException;

public class dialogoAprobarContenido extends DialogFragment {
    Activity actividad;
    TextView btnVer, btnDerivar, btnRechazar;
    int idContenido;

    ContenidoService contenidoService;

    //Creo Objeto SharedPreferences para utilizar para las Sesiones
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public dialogoAprobarContenido(int idContenido) {
        this.idContenido = idContenido;
    }

    @NonNull
    @Override
    public AlertDialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        try {
            return crearDialogo();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private AlertDialog crearDialogo() throws SQLException {
        AlertDialog.Builder builder  = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialogo_aprobar_contenido,null);
        builder.setView(v);

        preferences = actividad.getSharedPreferences("sesiones", Context.MODE_PRIVATE);
        editor = preferences.edit();

        btnVer = (TextView) v.findViewById(R.id.btnVer);
        btnDerivar = (TextView) v.findViewById(R.id.btnDerivar);
        btnRechazar = (TextView) v.findViewById(R.id.btnRechazar);

        eventoBotones();

        return builder.create();
    }

    private void eventoBotones(){

        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Acá hay que hacer que abra el archivo", Toast.LENGTH_SHORT).show();
            }
        });
        btnDerivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarServicios();
                Contenido contenido = contenidoService.getContenidoByID(idContenido);
                Estado estado = new Estado(EstadoEnum.APROBADO_DIRECTOR.getId());
                contenido.setEstado(estado);
                if(contenidoService.actualizar(contenido)){
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_navigation_drawer_consultante);
                    navController.navigate(R.id.nav_aprobarContenido_director);
                    dismiss();
                }
            }
        });
        btnRechazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarServicios();
                Contenido contenido = contenidoService.getContenidoByID(idContenido);
                Estado estado = new Estado(EstadoEnum.RECHAZADO_DIRECTOR.getId());
                contenido.setEstado(estado);
                if(contenidoService.actualizar(contenido)){
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_navigation_drawer_consultante);
                    navController.navigate(R.id.nav_aprobarContenido_director);
                    dismiss();
                }
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.actividad  = (Activity) context;
        }else{
            throw new RuntimeException(context.toString());
        }
    }

    public void iniciarServicios(){
        try {
            contenidoService = new ContenidoServiceImpl();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
