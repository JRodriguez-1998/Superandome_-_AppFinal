package com.example.superandome_appfinal.Dialogos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
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
import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.Estado;
import com.example.superandome_appfinal.IServices.ConsejoService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.ConsejoServiceImpl;

import java.sql.SQLException;

public class derivarRechazarConsejo extends DialogFragment {

    Activity actividad;
    TextView txtEstatus, txtMensaje, btnDerivar, btnRechazar;
    int idConsejo;

    ConsejoService consejoService;

    public derivarRechazarConsejo(int idConsejo) {
        this.idConsejo = idConsejo;
    }

    @NonNull
    @Override
    public AlertDialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        try {
            return crearDialogo();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Toast.makeText(getContext(),"Error al crear dialogo",Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    private AlertDialog crearDialogo() throws SQLException {
        AlertDialog.Builder builder  = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialogo_derivar_rechazar,null);
        builder.setView(v);

        btnDerivar = (TextView) v.findViewById(R.id.btnDerivar);
        btnRechazar = (TextView) v.findViewById(R.id.btnRechazar);

        eventoBotones();

        return builder.create();
    }

    private void eventoBotones(){

        btnDerivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarServicios();
                Consejo consejo = consejoService.getConsejoById(idConsejo);
                Estado estado = new Estado(EstadoEnum.APROBADO_PROFESIONAL.getId());
                consejo.setEstado(estado);
                if(consejoService.actualizar(consejo)){
                    Toast.makeText(getActivity(), "Consejo Derivado", Toast.LENGTH_SHORT).show();
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_navigation_drawer_consultante);
                    navController.navigate(R.id.nav_derivarConsejo_profesional);
                    dismiss();
                }
            }
        });
        btnRechazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarServicios();
                Consejo consejo = consejoService.getConsejoById(idConsejo);
                Estado estado = new Estado(EstadoEnum.RECHAZADO_PROFESIONAL.getId());
                consejo.setEstado(estado);
                if(consejoService.actualizar(consejo)){
                    Toast.makeText(getActivity(), "Consejo Rechazado", Toast.LENGTH_SHORT).show();
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_navigation_drawer_consultante);
                    navController.navigate(R.id.nav_derivarConsejo_profesional);
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
            consejoService = new ConsejoServiceImpl();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Toast.makeText(getContext(),"Error al iniciar servicios",Toast.LENGTH_SHORT).show();
        }
    }
}
