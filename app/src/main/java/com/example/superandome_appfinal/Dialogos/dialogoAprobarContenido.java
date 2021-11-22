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
import com.example.superandome_appfinal.Constantes.TipoArchivoEnum;
import com.example.superandome_appfinal.Entidades.Contenido;
import com.example.superandome_appfinal.Entidades.Estado;
import com.example.superandome_appfinal.Helpers.SessionManager;
import com.example.superandome_appfinal.IServices.ContenidoService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.ContenidoServiceImpl;

import java.sql.SQLException;
import java.util.Date;

public class dialogoAprobarContenido extends DialogFragment {
    Activity actividad;
    TextView btnVer, btnDerivar, btnRechazar;
    int idContenido;

    ContenidoService contenidoService;

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
        contenidoService = new ContenidoServiceImpl();

        AlertDialog.Builder builder  = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialogo_aprobar_contenido,null);
        builder.setView(v);

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
                SessionManager.setIdContenido(requireActivity(), idContenido);

                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_navigation_drawer_consultante);

                int idTipoArchivo = contenidoService.getContenidoByID(idContenido).getTipoArchivo().getIdTipoArchivo();
                switch (TipoArchivoEnum.getTipoArchivo(idTipoArchivo)) {
                    case PDF:
                        navController.navigate(R.id.nav_multimedia_text_director);
                        break;
                    case VIDEO:
                        navController.navigate(R.id.nav_multimedia_video_director);
                        break;
                    case AUDIO:
                        navController.navigate(R.id.nav_multimedia_audio_director);
                        break;
                }
                dismiss();
            }
        });
        btnDerivar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contenido contenido = contenidoService.getContenidoByID(idContenido);
                Estado estado = new Estado(EstadoEnum.APROBADO_DIRECTOR.getId());
                contenido.setEstado(estado);
                contenido.setFechaAprobacion(new Date());
                if(contenidoService.actualizar(contenido)){
                    Toast.makeText(getActivity(), "Contenido Aprobado", Toast.LENGTH_SHORT).show();
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_navigation_drawer_consultante);
                    navController.navigate(R.id.nav_aprobarContenido_director);
                    dismiss();
                }
            }
        });
        btnRechazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contenido contenido = contenidoService.getContenidoByID(idContenido);
                Estado estado = new Estado(EstadoEnum.RECHAZADO_DIRECTOR.getId());
                contenido.setEstado(estado);
                if(contenidoService.actualizar(contenido)){
                    Toast.makeText(getActivity(), "Contenido Rechazado", Toast.LENGTH_SHORT).show();
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
}
