package com.example.superandome_appfinal.Vistas.Consultante;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superandome_appfinal.Constantes.ItemRutinaEnum;
import com.example.superandome_appfinal.IServices.ItemUsuarioDiarioService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.ItemUsuarioDiarioServiceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

    private TextView txtResultado;

    Integer idUsuario;
    private ItemUsuarioDiarioService service;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_reporte_rutina, container, false);

        try {



        FindViews(v);

        spMeses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                LoadProgressBars();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                LoadProgressBars();
            }
        });

        try {
            service = new ItemUsuarioDiarioServiceImpl();

            SharedPreferences preferences = requireActivity().getSharedPreferences("sesiones", Context.MODE_PRIVATE);
            idUsuario = preferences.getInt("idUser", 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        LoadSpinner();
        }catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Ha ocurrido un error al inicializar la pantalla", Toast.LENGTH_SHORT).show();
            }
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

        txtResultado = v.findViewById(R.id.txtResultadoRutina);
    }

    private void LoadSpinner() {
        List<String> listMeses = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, -i);

            Integer anio = c.get(Calendar.YEAR);
            String mes = c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());

            String anio_mes = anio + " - " + mes;
            listMeses.add(anio_mes);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item_mes, listMeses);
        spMeses.setAdapter(adapter);
    }

    private Calendar GetSelectedDate() {
        int pos = spMeses.getSelectedItemPosition();

        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -(pos + 1));

        return c;
    }

    private void LoadProgressBars() {
        try {
            Calendar fecha = GetSelectedDate();

            Map<Integer, Float> map = service.getReporteMensualRutina(idUsuario, fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH) + 1);
            if (map == null) {
                Toast.makeText(getActivity(), "Ha ocurrido un error al obtener el reporte", Toast.LENGTH_SHORT).show();
                return;
            }
            if (map.size() == 0) {
                Toast.makeText(getActivity(), "No hay datos registrados para el período seleccionado", Toast.LENGTH_SHORT).show();
                return;
            }

            barRut1.setProgress(Math.round(map.get(ItemRutinaEnum.DIETA.getId())));
            barRut2.setProgress(Math.round(map.get(ItemRutinaEnum.CONSUMO.getId())));
            barRut3.setProgress(Math.round(map.get(ItemRutinaEnum.EJERCICIO.getId())));
            barRut4.setProgress(Math.round(map.get(ItemRutinaEnum.DESCANSO.getId())));
            barRut5.setProgress(Math.round(map.get(ItemRutinaEnum.AMBIENTE.getId())));
            barRut6.setProgress(Math.round(map.get(ItemRutinaEnum.HIGIENE.getId())));
            barRut7.setProgress(Math.round(map.get(ItemRutinaEnum.EQUILIBRIO.getId())));
            barRut8.setProgress(Math.round(map.get(ItemRutinaEnum.SOCIAL.getId())));
            barRut9.setProgress(Math.round(map.get(ItemRutinaEnum.OCIO.getId())));
            barRut10.setProgress(Math.round(map.get(ItemRutinaEnum.NATURALEZA.getId())));

            LoadResultado(map);

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Ha ocurrido un error al obtener el reporte", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void LoadResultado(Map<Integer, Float> map) {
        Float acu = 0f;
        for (Map.Entry<Integer, Float> entry : map.entrySet()) {
            acu += entry.getValue();
        }
        int promedio = Math.round(acu / map.size());

        String resultado;

        if (promedio >= 90)
            resultado = "¡Excelente autocuidado!";
        else if (promedio >= 60)
            resultado = "Buen autocuidado, seguí así";
        else
            resultado = "Se puede reforzar, ¡a seguir trabajando!";

        txtResultado.setText(resultado);
    }

}