package com.example.superandome_appfinal.Vistas.Consultante;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.superandome_appfinal.Constantes.EncuestaEnum;
import com.example.superandome_appfinal.Dialogos.dialogoErrorFragment;
import com.example.superandome_appfinal.Dialogos.dialogoErrorInesperado;
import com.example.superandome_appfinal.Dialogos.dialogoProximamente;
import com.example.superandome_appfinal.Dialogos.dialogoEsperarXDias;
import com.example.superandome_appfinal.Entidades.Encuesta;
import com.example.superandome_appfinal.Entidades.EncuestaUsuario;
import com.example.superandome_appfinal.Helpers.SessionManager;
import com.example.superandome_appfinal.IServices.EncuestaService;
import com.example.superandome_appfinal.IServices.EncuestaUsuarioService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.EncuestaServiceImpl;
import com.example.superandome_appfinal.Services.EncuestaUsuarioServiceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class indexEncuestas extends Fragment {
    RecyclerView recyclerViewEncuestas;
    Button btnVerResultados;

    EncuestaService encuestaService;
    List<Encuesta> encuestas;
    EncuestaUsuarioService encuestaUsuService;

    Integer idUsuario;
    long dias;

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
            encuestaUsuService = new EncuestaUsuarioServiceImpl();

            encuestas = encuestaService.getEncuestas();
            idUsuario = SessionManager.obtenerUsuario(requireActivity()).getIdUsuario();

            recyclerViewEncuestas = v.findViewById(R.id.rvEncuestas);
            recyclerViewEncuestas.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerViewEncuestas.setHasFixedSize(true);

            btnVerResultados = v.findViewById(R.id.btnVerResultadosAnt);
            btnVerResultados.setOnClickListener(this::resultadosAnteriores);

            AdapterEncuesta adapter = new AdapterEncuesta(encuestas);
            adapter.setOnClickListener(this::encuestaClick);

            recyclerViewEncuestas.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
            dialogoErrorFragment d = new dialogoErrorFragment();
            d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_errorfragment");
        }

        return v;
    }

    public void encuestaClick(View view) {

        try {
            int idEncuesta = encuestas.get(recyclerViewEncuestas.getChildAdapterPosition(view)).getIdEncuesta();

            if (!puedeContestar(idEncuesta)) {
                dialogoEsperarXDias d = new dialogoEsperarXDias(dias);
                d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_esperar7dias");
                return;
            }

            switch (EncuestaEnum.getEncuestaEnum(idEncuesta))
            {
                case TEST_ANSIEDAD_BECK:
                    NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_navigation_drawer_consultante);
                    navController.navigate(R.id.nav_ingresarEncuesta);
                    break;
                case NO_IMPLEMENTADO:
                    dialogoProximamente d = new dialogoProximamente();
                    d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_proximamente");
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            dialogoErrorInesperado d = new dialogoErrorInesperado();
            d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_errorinesperado");
        }
    }

    public void resultadosAnteriores(View view) {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_navigation_drawer_consultante);
        navController.navigate(R.id.nav_resultadosEncuestas);
    }

    public Boolean puedeContestar(Integer idEncuesta) throws Exception {

        List<EncuestaUsuario> listEncuestaUsuario = encuestaUsuService.getEncuestaUsuarioById(idEncuesta, idUsuario);

        if (listEncuestaUsuario == null)
            throw new Exception("Ha ocurrido un error al buscar las encuestas del usuario");
        if (listEncuestaUsuario.size() == 0)
            return true;

        EncuestaUsuario encuestaUsu = listEncuestaUsuario.get(listEncuestaUsuario.size() -1);

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date ultimaEncuestaFormat = formatter.parse(formatter.format(encuestaUsu.getFecha()));
        Date fechaActualFormat = formatter.parse(formatter.format(new Date()));

        long diff = fechaActualFormat.getTime() - ultimaEncuestaFormat.getTime();
        long diferenciaDias = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        dias = 7 - diferenciaDias;

        return diferenciaDias >= 7;
    }
}