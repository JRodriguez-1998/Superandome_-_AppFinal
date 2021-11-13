package com.example.superandome_appfinal.Vistas.Director;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.superandome_appfinal.Dialogos.derivarRechazarConsejo;
import com.example.superandome_appfinal.Dialogos.dialogoAprobarConsejo;
import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.IServices.ConsejoService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.ConsejoServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class aprobarConsejo_director extends Fragment {
    RecyclerView recyclerViewContenido;
    ArrayList<Consejo> consejos;

    ConsejoService consejoService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aprobar_consejo, container, false);

        try {
            consejoService = new ConsejoServiceImpl();


        consejos = new ArrayList<Consejo>();
        recyclerViewContenido=(RecyclerView) view.findViewById(R.id.rvContenido);
        recyclerViewContenido.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewContenido.setHasFixedSize(true);

        obtenerContenido();

        adapterDirector adapterContenido = new adapterDirector(consejos);

        adapterContenido.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int idConsejo = (int) consejos.get(recyclerViewContenido.getChildAdapterPosition(view)).getIdConsejo();
                dialogoAprobarConsejo d = new dialogoAprobarConsejo(idConsejo);
                d.show(getActivity().getSupportFragmentManager(),"dialogo_aprobar_consejo");
                Toast.makeText(getActivity(),"IdConsejo: " + idConsejo, Toast.LENGTH_SHORT).show();
            }
        });

        recyclerViewContenido.setAdapter(adapterContenido);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Ha ocurrido un error al inicializar la pantalla", Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    public void obtenerContenido(){

        for(int i = 0; i< consejoService.getConsejosPendientesDIRECTOR().size(); i ++){
            consejos.add(consejoService.getConsejosPendientesDIRECTOR().get(i));
        }
    }
}
