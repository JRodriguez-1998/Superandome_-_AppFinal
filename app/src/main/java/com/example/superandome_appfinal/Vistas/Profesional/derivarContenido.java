package com.example.superandome_appfinal.Vistas.Profesional;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.superandome_appfinal.Dialogos.dialogoAprobarConsejo;
import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.Contenido;

import com.example.superandome_appfinal.IServices.ContenidoService;
import com.example.superandome_appfinal.R;

import com.example.superandome_appfinal.Services.ContenidoServiceImpl;
import com.example.superandome_appfinal.Vistas.Profesional.adapterMultimedia;


import java.sql.SQLException;
import java.util.ArrayList;

public class derivarContenido extends Fragment {
    RecyclerView recyclerViewContenido;
    ArrayList<Contenido> contenidos;

    ContenidoService contenidoService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_derivar_multimedia, container, false);

        try {
            contenidoService = new ContenidoServiceImpl();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        contenidos = new ArrayList<Contenido>();
        recyclerViewContenido=(RecyclerView) view.findViewById(R.id.rvContenido);
        recyclerViewContenido.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewContenido.setHasFixedSize(true);

        obtenerContenido();

        com.example.superandome_appfinal.Vistas.Profesional.adapterMultimedia adapterContenido = new com.example.superandome_appfinal.Vistas.Profesional.adapterMultimedia(contenidos);

        adapterContenido.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //Capturar IdContenido y enviarlo a su fragment
                int idContenido = (int) contenidos.get(recyclerViewContenido.getChildAdapterPosition(view)).getIdContenido();
                //dialogoAprobarConsejo d = new dialogoAprobarConsejo(idContenido);
                //d.show(getActivity().getSupportFragmentManager(),"dialogo_aprobar_consejo");
                Toast.makeText(getActivity(),"IdConsejo: " + idContenido, Toast.LENGTH_SHORT).show();
            }
        });

        recyclerViewContenido.setAdapter(adapterContenido);

        return view;
    }

    public void obtenerContenido(){

        for(int i = 0; i< contenidoService.getContenidosDERIVAR().size(); i ++){
            contenidos.add(contenidoService.getContenidosDERIVAR().get(i));
        }
    }
}
