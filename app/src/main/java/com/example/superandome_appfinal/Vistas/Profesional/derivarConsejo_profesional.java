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
import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.IServices.ConsejoService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.ConsejoServiceImpl;

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

        try {
            consejoService = new ConsejoServiceImpl();

            txtAviso = (TextView) view.findViewById(R.id.tvAprobarRechazar);
            consejos = new ArrayList<>();
            recyclerViewContenido=(RecyclerView) view.findViewById(R.id.rvContenido);
            recyclerViewContenido.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerViewContenido.setHasFixedSize(true);

            consejos.addAll(consejoService.getConsejosPendientes());

            if(consejoService.getConsejosPendientes().size() == 0){
                txtAviso.setText("Actualmente no hay consejos para derivar");
            }

            ReciclerViewAdapter_ConsejoProf adapterContenido = new ReciclerViewAdapter_ConsejoProf(consejos);

            adapterContenido.setOnClickListener(view1 -> {
                int idConsejo = (int) consejos.get(recyclerViewContenido.getChildAdapterPosition(view1)).getIdConsejo();

                derivarRechazarConsejo d = new derivarRechazarConsejo(idConsejo);
                d.show(requireActivity().getSupportFragmentManager(),"dialogo_derivar_rechazar");
            });

            recyclerViewContenido.setAdapter(adapterContenido);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Error al inicializar pantalla", Toast.LENGTH_SHORT).show();
        }
        return view;
    }
}