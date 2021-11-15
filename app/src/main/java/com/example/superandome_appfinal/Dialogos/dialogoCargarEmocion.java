package com.example.superandome_appfinal.Dialogos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superandome_appfinal.Constantes.EstadoEnum;
import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.ConsejoServiceImpl;
import com.example.superandome_appfinal.Vistas.Consultante.homeConsultante;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class dialogoCargarEmocion extends DialogFragment {

    Activity actividad;
    TextView txtEstatus, txtMensaje, btnAceptar;
    FragmentTransaction transaction;
    Fragment fragmentHome;
    Integer tipoEmocion;
    ConsejoServiceImpl consejoService;
    List<Consejo> listaConsejos;

    public dialogoCargarEmocion() { }

    public dialogoCargarEmocion(Integer tipoEmocion) {
        this.tipoEmocion = tipoEmocion;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return crearDialogo();
    }

    private AlertDialog crearDialogo(){
        AlertDialog.Builder builder  = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_dialogo_cargar_emocion,null);
        builder.setView(v);

        txtEstatus = (TextView) v.findViewById(R.id.txtEstatus);
        txtMensaje = (TextView) v.findViewById(R.id.txtMensajeDialogo);
        btnAceptar = (TextView) v.findViewById(R.id.btnAceptarDialogCambioPass);

        eventoBotones();
        iniciarServicios();

        listaConsejos = consejoService.getConsejosByEstadoAndTipo(EstadoEnum.APROBADO_DIRECTOR.getId(), tipoEmocion);

        Random numAleatorio = new Random(new Date().getTime());
        int n = numAleatorio.nextInt(listaConsejos.size() -1 +1);

        txtMensaje.setText(listaConsejos.get(n).getTexto().toString());

        return builder.create();
    }

    private void eventoBotones(){
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_navigation_drawer_consultante);
                navController.navigate(R.id.nav_homeConsultante);
                dismiss();
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
            consejoService = new ConsejoServiceImpl();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Toast.makeText(getContext(),"Error al crear servicio",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialogo_cargar_emocion, container, false);
    }
}