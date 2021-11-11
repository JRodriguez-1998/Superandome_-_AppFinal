package com.example.superandome_appfinal.Vistas.Consultante;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.superandome_appfinal.IServices.EmocionUsuarioService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.EmocionUsuarioServiceImpl;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class reporteEmocion extends Fragment {

    private final int COLOR_ASCO = ColorTemplate.rgb("23a44f");
    private final int COLOR_IRA = ColorTemplate.rgb("dc3221");
    private final int COLOR_FELICIDAD = ColorTemplate.rgb("ef971b");
    private final int COLOR_MIEDO = ColorTemplate.rgb("922580");
    private final int COLOR_TRISTEZA = ColorTemplate.rgb("0477ba");

    private PieChart chart;
    private Spinner spMeses;

    EmocionUsuarioService service;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_reporte_emocion, container, false);
        chart = v.findViewById(R.id.chart);
        spMeses = v.findViewById(R.id.spMeses);

        spMeses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                LoadPieChart();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                LoadPieChart();
            }
        });

        try {
            service = new EmocionUsuarioServiceImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }

        LoadSpinner();

        return v;
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

    private void LoadPieChart() {
        try {
            Calendar fecha = GetSelectedDate();

            Map<Integer, Float> map = service.getReporteMensualEmocion(1, fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH));
            if (map == null || map.size() == 0) {
                Toast.makeText(getActivity(), "No hay datos registrados para el período seleccionado", Toast.LENGTH_LONG).show();
                return;
            }

            List<PieEntry> entries = new ArrayList<>();
            entries.add(new PieEntry(map.get(1), "Asco"));
            entries.add(new PieEntry(map.get(2), "Ira"));
            entries.add(new PieEntry(map.get(3), "Felicidad"));
            entries.add(new PieEntry(map.get(4), "Miedo"));
            entries.add(new PieEntry(map.get(5), "Tristeza"));

            PieDataSet dataSet = new PieDataSet(entries, "Torta");
            dataSet.setColors(COLOR_ASCO, COLOR_IRA, COLOR_FELICIDAD, COLOR_MIEDO, COLOR_TRISTEZA);

            PieData data = new PieData(dataSet);
            data.setValueTextColor(R.color.white); // TODO: No funca
            data.setValueTextSize(18);

            chart.setUsePercentValues(true);
            chart.setEntryLabelColor(R.color.white); // TODO: No funca
            chart.setEntryLabelTextSize(18);

            chart.getDescription().setEnabled(false);
            chart.getLegend().setEnabled(false);
    //        chart.setHoleRadius(5f);
            chart.setDrawHoleEnabled(false);
    //        chart.setHoleColor(R.color.consultante);
    //        chart.setAlpha(0.50f);
    //        chart.setTransparentCircleAlpha(90);

            chart.setData(data);
            chart.invalidate();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Ha ocurrido un error al obtener el reporte", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}