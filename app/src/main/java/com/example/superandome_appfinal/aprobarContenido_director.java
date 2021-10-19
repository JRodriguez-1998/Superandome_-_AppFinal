package com.example.superandome_appfinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class aprobarContenido_director extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerViewContenido;
    private ReciclerViewAdapter adapterContenido;

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

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        recyclerViewContenido=(RecyclerView) getView().findViewById(R.id.rvContenido);
        recyclerViewContenido.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        adapterContenido = new ReciclerViewAdapter(obtenerContenido());
        recyclerViewContenido.setAdapter(adapterContenido);
    }

    public List<ClasePrueba> obtenerContenido(){
        List<ClasePrueba> contenido=new ArrayList<>();
        contenido.add(new ClasePrueba("Guia Salud Mental","Inés","PDF"));
        contenido.add(new ClasePrueba("Video Motivador","Marcos","Video"));

        return contenido;
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_aprobar_contenido_director, container, false);
    }
}