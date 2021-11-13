package com.example.superandome_appfinal.Vistas.Consultante;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
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

            recyclerViewContenido = v.findViewById(R.id.rvEncuestas);
            recyclerViewContenido.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerViewContenido.setHasFixedSize(true);

            AdapterEncuesta adapter = new AdapterEncuesta(encuestas);
            adapter.setOnClickListener(view -> {
                int idEncuesta = encuestas.get(recyclerViewContenido.getChildAdapterPosition(view)).getIdEncuesta();
                Toast.makeText(getActivity(),"IdEncuesta: " + idEncuesta, Toast.LENGTH_SHORT).show();
            });

            recyclerViewContenido.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Ha ocurrido un error al inicializar la pantalla", Toast.LENGTH_SHORT).show();
        }

        return v;
    }
}