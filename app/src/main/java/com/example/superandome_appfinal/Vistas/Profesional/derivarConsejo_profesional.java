package com.example.superandome_appfinal.Vistas.Profesional;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.superandome_appfinal.Dialogos.derivarRechazarConsejo;
import com.example.superandome_appfinal.Dialogos.dialogoCambiarPass;
import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.IServices.ConsejoService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.ConsejoServiceImpl;
import com.example.superandome_appfinal.Vistas.ClasePrueba;
import com.example.superandome_appfinal.Vistas.Consultante.recuperarPassword;

import java.sql.SQLException;
import java.util.ArrayList;

public class derivarConsejo_profesional extends Fragment {

    RecyclerView recyclerViewContenido;
    ArrayList<Consejo> consejos;
    TextView txtAviso;

    ConsejoService consejoService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_derivar_consejo, container, false);

        txtAviso = (TextView) view.findViewById(R.id.tvAprobarRechazar);
        consejos = new ArrayList<Consejo>();
        recyclerViewContenido=(RecyclerView) view.findViewById(R.id.rvContenido);
        recyclerViewContenido.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewContenido.setHasFixedSize(true);

        iniciarServicios();
        obtenerContenido();

        if(consejoService.getConsejosPendientes().size() == 0){
            txtAviso.setText("Actualmente no hay consejos para derivar");
        }

        ReciclerViewAdapter_ConsejoProf adapterContenido = new ReciclerViewAdapter_ConsejoProf(consejos);

        adapterContenido.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int idConsejo = (int) consejos.get(recyclerViewContenido.getChildAdapterPosition(view)).getIdConsejo();

                derivarRechazarConsejo d = new derivarRechazarConsejo(idConsejo);
                d.show(getActivity().getSupportFragmentManager(),"dialogo_derivar_rechazar");
            }
        });

        recyclerViewContenido.setAdapter(adapterContenido);

        return view;
    }

    public void obtenerContenido(){
        for(int i = 0; i< consejoService.getConsejosPendientes().size(); i ++){
            consejos.add(consejoService.getConsejosPendientes().get(i));
        }
    }

    public void iniciarServicios(){
        try {
            consejoService = new ConsejoServiceImpl();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}