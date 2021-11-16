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
import android.widget.Button;
import android.widget.Toast;

import com.example.superandome_appfinal.Constantes.EncuestaEnum;
import com.example.superandome_appfinal.Entidades.Encuesta;
import com.example.superandome_appfinal.IServices.EncuestaService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.EncuestaServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class indexEncuestas extends Fragment {
    RecyclerView recyclerViewEncuestas;
    Button btnVerResultados;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    EncuestaService encuestaService;
    List<Encuesta> encuestas;

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

            encuestas = encuestaService.getEncuestas();

            preferences = requireActivity().getSharedPreferences("encuesta", Context.MODE_PRIVATE);
            editor = preferences.edit();

            recyclerViewEncuestas = v.findViewById(R.id.rvEncuestas);
            recyclerViewEncuestas.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerViewEncuestas.setHasFixedSize(true);

            btnVerResultados = v.findViewById(R.id.btnVerResultadosAnt);
            btnVerResultados.setOnClickListener(this::resultadosAnteriores);

            AdapterEncuesta adapter = new AdapterEncuesta(encuestas);
            adapter.setOnClickListener(this::encuestaClick);

            recyclerViewEncuestas.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Ha ocurrido un error al inicializar la pantalla", Toast.LENGTH_SHORT).show();
        }

        return v;
    }

    public void encuestaClick(View view) {
        int idEncuesta = encuestas.get(recyclerViewEncuestas.getChildAdapterPosition(view)).getIdEncuesta();

        switch(EncuestaEnum.getEncuestaEnum(idEncuesta))
        {
            case TEST_ANSIEDAD_BECK:
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_navigation_drawer_consultante);
                navController.navigate(R.id.nav_ingresarEncuesta);
                break;
            case NO_IMPLEMENTADO:
                Toast.makeText(getContext(), "¡Proximamente!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void resultadosAnteriores(View view) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_navigation_drawer_consultante);
        navController.navigate(R.id.nav_resultadosEncuestas);
    }
}