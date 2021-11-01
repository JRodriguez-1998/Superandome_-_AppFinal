package com.example.superandome_appfinal;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class reporteRutina extends Fragment {

    private Spinner spMeses;

    private ProgressBar barRut1;
    private ProgressBar barRut2;
    private ProgressBar barRut3;
    private ProgressBar barRut4;
    private ProgressBar barRut5;
    private ProgressBar barRut6;
    private ProgressBar barRut7;
    private ProgressBar barRut8;
    private ProgressBar barRut9;
    private ProgressBar barRut10;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_reporte_rutina, container, false);

        FindViews(v);

        LoadProgressBars();
        LoadSpinner();

        return v;
    }

    private void FindViews(View v) {
        spMeses = v.findViewById(R.id.spMeses);

        barRut1 = v.findViewById(R.id.barRut1);
        barRut2 = v.findViewById(R.id.barRut2);
        barRut3 = v.findViewById(R.id.barRut3);
        barRut4 = v.findViewById(R.id.barRut4);
        barRut5 = v.findViewById(R.id.barRut5);
        barRut6 = v.findViewById(R.id.barRut6);
        barRut7 = v.findViewById(R.id.barRut7);
        barRut8 = v.findViewById(R.id.barRut8);
        barRut9 = v.findViewById(R.id.barRut9);
        barRut10 = v.findViewById(R.id.barRut10);
    }

    private void LoadProgressBars() {
        barRut1.setProgress(10);
        barRut2.setProgress(20);
        barRut3.setProgress(30);
        barRut4.setProgress(40);
        barRut5.setProgress(50);
        barRut6.setProgress(60);
        barRut7.setProgress(70);
        barRut8.setProgress(80);
        barRut9.setProgress(90);
        barRut10.setProgress(100);
    }

    private void LoadSpinner() {
        List<String> listMeses = Arrays.asList("Septiembre 2021", "Agosto 2021", "Julio 2021");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item_mes, listMeses);
        spMeses.setAdapter(adapter);
    }
}