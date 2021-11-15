package com.example.superandome_appfinal.Vistas.Director;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superandome_appfinal.Dialogos.dialogoAprobarContenido;
import com.example.superandome_appfinal.Dialogos.dialogoDerivarContenido;
import com.example.superandome_appfinal.Entidades.Contenido;
import com.example.superandome_appfinal.IServices.ContenidoService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.ContenidoServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class aprobarContenido_director extends Fragment {

    RecyclerView recyclerViewContenido;
    ArrayList<Contenido> contenidos;
    TextView txtAviso;

    ContenidoService contenidoService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aprobar_contenido_director, container, false);

        txtAviso = (TextView) view.findViewById(R.id.tvAprobarRechazar);
        contenidos = new ArrayList<Contenido>();
        recyclerViewContenido=(RecyclerView) view.findViewById(R.id.rvContenido);
        recyclerViewContenido.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewContenido.setHasFixedSize(true);

        iniciarServicios();
        obtenerContenido();

        if(contenidoService.getContenidosDerivados().size() == 0){
            txtAviso.setText("Actualmente no hay Contenido Multimedia por aprobar");
        }

        ReciclerViewAdapter adapterContenido = new ReciclerViewAdapter(contenidos);

        adapterContenido.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int idContenido = (int) contenidos.get(recyclerViewContenido.getChildAdapterPosition(view)).getIdContenido();
                Toast.makeText(getActivity(), "IdContenido: " + idContenido, Toast.LENGTH_SHORT).show();
                dialogoAprobarContenido d = new dialogoAprobarContenido(idContenido);
                d.show(getActivity().getSupportFragmentManager(),"dialogo_aprobar_contenido");
            }
        });

        recyclerViewContenido.setAdapter(adapterContenido);

        return view;
    }

    public void obtenerContenido(){
        for(int i = 0; i< contenidoService.getContenidosDerivados().size(); i ++){
            contenidos.add(contenidoService.getContenidosDerivados().get(i));
        }
    }

    public void iniciarServicios(){
        try {
            contenidoService = new ContenidoServiceImpl();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}