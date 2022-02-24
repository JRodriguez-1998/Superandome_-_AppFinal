package com.example.superandome_appfinal.Vistas.Consultante;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.superandome_appfinal.R;

import java.util.Arrays;
import java.util.List;


public class pregunta_seguridad extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public pregunta_seguridad() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment pregunta_seguridad.
     */
    // TODO: Rename and change types and number of parameters

    private Spinner spinnerPreguntas;


    public static pregunta_seguridad newInstance(String param1, String param2) {
        pregunta_seguridad fragment = new pregunta_seguridad();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pregunta_seguridad,container,false);
        spinnerPreguntas = v.findViewById(R.id.spPreguntas);

        LoadSpinnerPreguntas();
        return v;


    }


    private void LoadSpinnerPreguntas() {
        List<String> listPreguntas = Arrays.asList("Pregunta 1", "Pregunta 2", "Pregunta 3");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item_preguntas, listPreguntas);
        spinnerPreguntas.setAdapter(adapter);
    }

}