package com.example.superandome_appfinal.Dialogos;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.superandome_appfinal.R;


public class dialogoConfHorario extends DialogFragment {

    public dialogoConfHorario() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialogo_conf_horario, container, false);
    }
}