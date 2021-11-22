package com.example.superandome_appfinal.Vistas.Consultante;

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
import java.util.List;

public class multimedia extends Fragment {

    RecyclerView recyclerViewContenido;
    List<Contenido> contenidos;

    ContenidoService contenidoService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multimedia, container, false);

        try {
            contenidoService = new ContenidoServiceImpl();

            contenidos = contenidoService.getContenidosAprobados();
            recyclerViewContenido = view.findViewById(R.id.rvContenido);
            recyclerViewContenido.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerViewContenido.setHasFixedSize(true);

            adapterMultimedia adapterContenido = new adapterMultimedia(contenidos);

            adapterContenido.setOnClickListener(view1 -> {

                int idContenido = contenidos.get(recyclerViewContenido.getChildAdapterPosition(view1)).getIdContenido();

                SessionManager.setIdContenido(requireActivity(), idContenido);
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_navigation_drawer_consultante);

                int idTipoArchivo = contenidoService.getContenidoByID(idContenido).getTipoArchivo().getIdTipoArchivo();
                switch (TipoArchivoEnum.getTipoArchivo(idTipoArchivo)) {
                    case PDF:
                        navController.navigate(R.id.nav_multimedia_text);
                        break;
                    case VIDEO:
                        navController.navigate(R.id.nav_multimedia_video);
                        break;
                    case AUDIO:
                        navController.navigate(R.id.nav_multimedia_audio);
                        break;
                }
            });

            recyclerViewContenido.setAdapter(adapterContenido);
        } catch (Exception throwables) {
            throwables.printStackTrace();
            Toast.makeText(getContext(), "Error al inicializar la pantalla", Toast.LENGTH_SHORT).show();
        }
        return view;
    }
}