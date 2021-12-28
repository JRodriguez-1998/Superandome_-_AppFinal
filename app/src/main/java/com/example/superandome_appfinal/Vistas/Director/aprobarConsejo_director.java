package com.example.superandome_appfinal.Vistas.Director;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.superandome_appfinal.Dialogos.dialogoAprobarConsejo;
import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.IServices.ConsejoService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.ConsejoServiceImpl;

import java.util.ArrayList;

public class aprobarConsejo_director extends Fragment {
    RecyclerView recyclerViewContenido;
    ArrayList<Consejo> consejos;
    TextView txtAviso;

    ConsejoService consejoService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aprobar_consejo, container, false);

        try {
            consejoService = new ConsejoServiceImpl();

            txtAviso = (TextView) view.findViewById(R.id.tvAprobarRechazarDir);
            consejos = new ArrayList<>();
            recyclerViewContenido=(RecyclerView) view.findViewById(R.id.rvContenido);
            recyclerViewContenido.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerViewContenido.setHasFixedSize(true);

            consejos.addAll(consejoService.getConsejosPendientesDIRECTOR());

            if(consejoService.getConsejosPendientesDIRECTOR().size() == 0){
                txtAviso.setText("Actualmente no hay consejos para aprobar");
            }

            adapterDirector adapterContenido = new adapterDirector(consejos);

            adapterContenido.setOnClickListener(view1 -> {
                int idConsejo = (int) consejos.get(recyclerViewContenido.getChildAdapterPosition(view1)).getIdConsejo();
                dialogoAprobarConsejo d = new dialogoAprobarConsejo(idConsejo);
                d.show(requireActivity().getSupportFragmentManager(),"dialogo_aprobar_consejo");
            });

            recyclerViewContenido.setAdapter(adapterContenido);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Ha ocurrido un error al inicializar la pantalla", Toast.LENGTH_SHORT).show();
        }
        return view;
    }

}
