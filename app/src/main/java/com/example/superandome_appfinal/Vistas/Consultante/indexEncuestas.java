package com.example.superandome_appfinal.Vistas.Consultante;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.superandome_appfinal.Entidades.Encuesta;
import com.example.superandome_appfinal.IServices.EncuestaService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.EncuestaServiceImpl;

import java.util.List;

public class indexEncuestas extends Fragment {
    RecyclerView recyclerViewContenido;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    EncuestaService encuestaService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_index_encuestas, container, false);

        try {
            encuestaService = new EncuestaServiceImpl();

            List<Encuesta> encuestas = encuestaService.getEncuestas();

            preferences = getActivity().getSharedPreferences("encuesta", Context.MODE_PRIVATE);
            editor = preferences.edit();

            recyclerViewContenido = v.findViewById(R.id.rvEncuestas);
            recyclerViewContenido.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerViewContenido.setHasFixedSize(true);

            AdapterEncuesta adapter = new AdapterEncuesta(encuestas);
            adapter.setOnClickListener(view -> {
                int idEncuesta = encuestas.get(recyclerViewContenido.getChildAdapterPosition(view)).getIdEncuesta();

                guardarSesionContenido(true, idEncuesta);
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_navigation_drawer_consultante);
                navController.navigate(R.id.nav_ingresarEncuesta);
            });

            recyclerViewContenido.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Ha ocurrido un error al inicializar la pantalla", Toast.LENGTH_SHORT).show();
        }

        return v;
    }

    public void guardarSesionContenido(boolean iniciar, int idEncuesta){
        editor.putBoolean("encuesta",iniciar);
        editor.putInt("idEncuesta", idEncuesta);
        editor.apply();
    }
}