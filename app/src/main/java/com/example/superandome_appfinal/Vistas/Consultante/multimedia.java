package com.example.superandome_appfinal.Vistas.Consultante;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.superandome_appfinal.Constantes.TipoArchivoEnum;
import com.example.superandome_appfinal.Dialogos.dialogoAprobarConsejo;
import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.Contenido;
import com.example.superandome_appfinal.Entidades.TipoArchivo;
import com.example.superandome_appfinal.IServices.ContenidoService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.ConsejoServiceImpl;
import com.example.superandome_appfinal.Services.ContenidoServiceImpl;
import com.example.superandome_appfinal.Vistas.Director.adapterDirector;
import com.github.barteksc.pdfviewer.PDFView;

import java.sql.SQLException;
import java.util.ArrayList;

public class multimedia extends Fragment {

    RecyclerView recyclerViewContenido;
    ArrayList<Contenido> contenidos;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    ContenidoService contenidoService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multimedia, container, false);

        try {
            contenidoService = new ContenidoServiceImpl();

        preferences = getActivity().getSharedPreferences("contenido", Context.MODE_PRIVATE);
        editor = preferences.edit();

        contenidos = new ArrayList<Contenido>();
        recyclerViewContenido=(RecyclerView) view.findViewById(R.id.rvContenido);
        recyclerViewContenido.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewContenido.setHasFixedSize(true);

        obtenerContenido();

        adapterMultimedia adapterContenido = new adapterMultimedia(contenidos);

        adapterContenido.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                int idContenido = (int) contenidos.get(recyclerViewContenido.getChildAdapterPosition(view)).getIdContenido();
                //Toast.makeText(getActivity(),"idContenido: " + idContenido, Toast.LENGTH_SHORT).show();

                if(contenidoService.getContenidoByID(idContenido).getTipoArchivo().getIdTipoArchivo() == TipoArchivoEnum.PDF.getTipo()){
                    guardarSesionContenido(true, idContenido);
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_navigation_drawer_consultante);
                    navController.navigate(R.id.nav_multimedia_text);
                }

                if(contenidoService.getContenidoByID(idContenido).getTipoArchivo().getIdTipoArchivo() == TipoArchivoEnum.VIDEO.getTipo()){
                    guardarSesionContenido(true, idContenido);
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_navigation_drawer_consultante);
                    navController.navigate(R.id.nav_multimedia_video);
                }

                if(contenidoService.getContenidoByID(idContenido).getTipoArchivo().getIdTipoArchivo() == TipoArchivoEnum.AUDIO.getTipo()){
                    guardarSesionContenido(true, idContenido);
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_navigation_drawer_consultante);
                    navController.navigate(R.id.nav_multimedia_audio);
                }
            }
        });

        recyclerViewContenido.setAdapter(adapterContenido);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Toast.makeText(getContext(), "Error añ inicializar pantalla", Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    public void guardarSesionContenido(boolean iniciar, int idContenido){
        editor.putBoolean("contenido",iniciar);
        editor.putInt("idContenido", idContenido);
        editor.apply();
    }

    public void obtenerContenido(){
        for(int i = 0; i< contenidoService.getContenidosAprobados().size(); i ++){
            contenidos.add(contenidoService.getContenidosAprobados().get(i));
        }
    }
}