package com.example.superandome_appfinal.Vistas.Director;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Vistas.ReciclerViewAdapter;
import com.example.superandome_appfinal.Vistas.ClasePrueba;

import java.util.ArrayList;

public class aprobarContenido_director extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    RecyclerView recyclerViewContenido;
    ArrayList <ClasePrueba> contenido;

    public aprobarContenido_director() {
        // Required empty public constructor
    }

    public static aprobarContenido_director newInstance(String param1, String param2) {
        aprobarContenido_director fragment = new aprobarContenido_director();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aprobar_contenido_director, container, false);

        contenido=new ArrayList<>();
        recyclerViewContenido=(RecyclerView) view.findViewById(R.id.rvContenido);
        recyclerViewContenido.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewContenido.setHasFixedSize(true);

        obtenerContenido();

        ReciclerViewAdapter adapterContenido = new ReciclerViewAdapter(contenido);
        recyclerViewContenido.setAdapter(adapterContenido);

        return view;
    }

    public void obtenerContenido(){

        contenido.add(new ClasePrueba("Guia Salud Mental","Inés","PDF"));
        contenido.add(new ClasePrueba("Video Motivador","Marcos","Video"));
        contenido.add(new ClasePrueba("No estes triste xd","Juan","Consejo"));
        contenido.add(new ClasePrueba("Canciones de la 12","Mauricio","Video"));
        contenido.add(new ClasePrueba("Hola Juan Carlos","José","Video"));
    }
}