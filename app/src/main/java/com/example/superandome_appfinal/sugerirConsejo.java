package com.example.superandome_appfinal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class sugerirConsejo extends Fragment {

    public sugerirConsejo() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Spinner spinnerTipoConsejo = (Spinner) view.findViewById(R.id.spinnerTipoConsejo);
        String [] tipoConsejos = {"Seleccione Tipo de consejo", "Bronca","Alegria","Tristeza"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item_tipoconsejo, tipoConsejos);
        spinnerTipoConsejo.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sugerir_consejo, container, false);
    }
}