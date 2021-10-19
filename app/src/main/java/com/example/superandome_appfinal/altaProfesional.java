package com.example.superandome_appfinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class altaProfesional extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    Spinner spinnerGenero;

    public altaProfesional() {
        // Required empty public constructor
    }

    public static altaProfesional newInstance(String param1, String param2) {
        altaProfesional fragment = new altaProfesional();
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
        spinnerGenero = (Spinner) getView().findViewById(R.id.spinnerGeneroProfe);

        String [] generos = {"Masculino","Femenino","Otros"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.spinner_generos, generos);
        spinnerGenero.setAdapter(adapter);

        return inflater.inflate(R.layout.fragment_alta_profesional, container, false);
    }
}