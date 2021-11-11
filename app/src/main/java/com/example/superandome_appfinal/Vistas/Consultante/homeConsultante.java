package com.example.superandome_appfinal.Vistas.Consultante;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.superandome_appfinal.R;

public class homeConsultante extends Fragment {

    public homeConsultante() {}

    public static homeConsultante newInstance(){
        return new homeConsultante();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_consultante, container, false);
    }
}