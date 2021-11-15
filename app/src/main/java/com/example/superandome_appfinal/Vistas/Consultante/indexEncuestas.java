package com.example.superandome_appfinal.Vistas.Consultante;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.superandome_appfinal.Constantes.EncuestaEnum;
import com.example.superandome_appfinal.Entidades.Encuesta;
import com.example.superandome_appfinal.Entidades.EncuestaUsuario;
import com.example.superandome_appfinal.IServices.EncuestaService;
import com.example.superandome_appfinal.IServices.EncuestaUsuarioService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.EncuestaServiceImpl;
import com.example.superandome_appfinal.Services.EncuestaUsuarioServiceImpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class indexEncuestas extends Fragment {
    RecyclerView recyclerViewContenido;
    SharedPreferences preferences;
    SharedPreferences preferences2;

    SharedPreferences.Editor editor;

    EncuestaService encuestaService;
    EncuestaUsuarioService encuestaUsuService;

    Integer idUsuario;

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

            List<Encuesta> encuestas = encuestaService.getEncuestas();

            preferences2 = requireActivity().getSharedPreferences("sesiones", Context.MODE_PRIVATE);
            idUsuario = preferences2.getInt("idUser", 0);

            preferences = requireActivity().getSharedPreferences("encuesta", Context.MODE_PRIVATE);
            editor = preferences.edit();

            recyclerViewContenido = v.findViewById(R.id.rvEncuestas);
            recyclerViewContenido.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerViewContenido.setHasFixedSize(true);

            AdapterEncuesta adapter = new AdapterEncuesta(encuestas);
            adapter.setOnClickListener(view -> {

                try {
                    int idEncuesta = encuestas.get(recyclerViewContenido.getChildAdapterPosition(view)).getIdEncuesta();

                    if(!puedeContestar(idEncuesta)){
                        Toast.makeText(getContext(), "Se puede responder luego de 7 dias", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    switch(EncuestaEnum.getEncuestaEnum(idEncuesta))
                    {
                        case TEST_ANSIEDAD_BECK:
                            guardarSesionContenido(true, idEncuesta);
                            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_navigation_drawer_consultante);
                            navController.navigate(R.id.nav_ingresarEncuesta);
                            break;
                        case NO_IMPLEMENTADO:
                            Toast.makeText(getContext(), "¡Proximamente!", Toast.LENGTH_SHORT).show();
                            break;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "¡Ha ocurrido un error inesperado!", Toast.LENGTH_SHORT).show();
                }

            });

            recyclerViewContenido.setAdapter(adapter);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Ha ocurrido un error al inicializar la pantalla", Toast.LENGTH_SHORT).show();
        }

        return v;
    }

    public Boolean puedeContestar(Integer idEncuesta) throws Exception{

        List<EncuestaUsuario> listEncuestaUsuario = encuestaUsuService.getEncuestaUsuarioById(idEncuesta, 3);

        if(listEncuestaUsuario == null){
            return true;
        }

        EncuestaUsuario encuestaUsu = listEncuestaUsuario.get(listEncuestaUsuario.size() -1);

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date ultimaEncuestaFormat = formatter.parse(formatter.format(encuestaUsu.getFecha()));
        Date fechaActualFormat = formatter.parse(formatter.format(new Date()));

        long diff = fechaActualFormat.getTime() - ultimaEncuestaFormat.getTime();
        long dias = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        return dias >= 7;
    }

    public void guardarSesionContenido(boolean iniciar, int idEncuesta){
        editor.putBoolean("encuesta",iniciar);
        editor.putInt("idEncuesta", idEncuesta);
        editor.apply();
    }
}