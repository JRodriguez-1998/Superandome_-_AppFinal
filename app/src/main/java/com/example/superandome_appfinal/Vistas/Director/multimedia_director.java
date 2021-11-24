package com.example.superandome_appfinal.Vistas.Director;

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
import com.example.superandome_appfinal.Entidades.Contenido;
import com.example.superandome_appfinal.Helpers.SessionManager;
import com.example.superandome_appfinal.IServices.ContenidoService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.ContenidoServiceImpl;
import com.example.superandome_appfinal.Vistas.Consultante.adapterMultimedia;

import java.sql.SQLException;
import java.util.ArrayList;

public class multimedia_director extends Fragment {

    RecyclerView recyclerViewContenido;
    ArrayList<Contenido> contenidos;

    ContenidoService contenidoService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multimedia_director, container, false);

        try {
            contenidoService = new ContenidoServiceImpl();

            contenidos = new ArrayList<Contenido>();
            recyclerViewContenido=(RecyclerView) view.findViewById(R.id.rvContenido);
            recyclerViewContenido.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerViewContenido.setHasFixedSize(true);

            obtenerContenido();

            adapterMultimedia adapterContenido = new adapterMultimedia(contenidos);

            adapterContenido.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){

                    int idContenido = contenidos.get(recyclerViewContenido.getChildAdapterPosition(view)).getIdContenido();

                    SessionManager.setIdContenido(requireActivity(), idContenido);
                    NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_navigation_drawer_consultante);

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
                }
            });

            recyclerViewContenido.setAdapter(adapterContenido);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Toast.makeText(getContext(), "Error al inicializar pantalla", Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    public void obtenerContenido(){
        for(int i = 0; i< contenidoService.getContenidosAprobados().size(); i ++){
            contenidos.add(contenidoService.getContenidosAprobados().get(i));
        }
    }
}
