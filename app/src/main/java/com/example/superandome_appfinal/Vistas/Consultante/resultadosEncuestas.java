package com.example.superandome_appfinal.Vistas.Consultante;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.superandome_appfinal.Dialogos.dialogoErrorFragment;
import com.example.superandome_appfinal.Entidades.EncuestaUsuario;
import com.example.superandome_appfinal.Helpers.SessionManager;
import com.example.superandome_appfinal.IServices.EncuestaUsuarioService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.EncuestaUsuarioServiceImpl;

import java.util.List;

public class resultadosEncuestas extends Fragment {

    RecyclerView recyclerViewResultados;

    Integer idUsuario;
    EncuestaUsuarioService encuestaUsuarioService;
    List<EncuestaUsuario> resultados;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_resultados_encuestas, container, false);

        try {
            encuestaUsuarioService = new EncuestaUsuarioServiceImpl();

            idUsuario = SessionManager.obtenerUsuario(requireActivity()).getIdUsuario();

            resultados = encuestaUsuarioService.getEncuestaUsuarioByUsuario(idUsuario);

            recyclerViewResultados = v.findViewById(R.id.rvResultadoEncuestas);
            recyclerViewResultados.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerViewResultados.setHasFixedSize(true);

            AdapterResultados adapter = new AdapterResultados(resultados);
            recyclerViewResultados.setAdapter(adapter);

            adapter.setOnClickListener(view -> {
//                int idEncuesta = resultados.get(recyclerViewResultados.getChildAdapterPosition(view)).getIdEncuestaUsuario();
//                Toast.makeText(getContext(), "Hola " + idEncuesta, Toast.LENGTH_SHORT).show();
            });

        } catch (Exception e) {
            e.printStackTrace();
            dialogoErrorFragment d = new dialogoErrorFragment();
            d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_errorfragment");
        }

        return v;
    }
}