package com.example.superandome_appfinal.Vistas.Consultante;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.superandome_appfinal.IServices.EmocionUsuarioService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.EmocionUsuarioServiceImpl;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class reporteEmocion extends Fragment {

    private final int COLOR_ASCO = ColorTemplate.rgb("23a44f");
    private final int COLOR_IRA = ColorTemplate.rgb("dc3221");
    private final int COLOR_FELICIDAD = ColorTemplate.rgb("ef971b");
    private final int COLOR_MIEDO = ColorTemplate.rgb("922580");
    private final int COLOR_TRISTEZA = ColorTemplate.rgb("0477ba");

    private PieChart chart;
    private Spinner spMeses;

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

        LoadPieChart();
        LoadSpinner();

        return v;
    }

    private void LoadPieChart() {
        try {
            EmocionUsuarioService service = new EmocionUsuarioServiceImpl();
            Map<Integer, Float> map = service.getReporteMensualEmocion(1, 2021, 10);
            int x = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(20, "Asco"));
        entries.add(new PieEntry(13, "Ira"));
        entries.add(new PieEntry(17, "Felicidad"));
        entries.add(new PieEntry(33, "Miedo"));
        entries.add(new PieEntry(17, "Tristeza"));

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
    }

    private void LoadSpinner() {
        List<String> listMeses = Arrays.asList("Septiembre 2021", "Agosto 2021", "Julio 2021");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item_mes, listMeses);
        spMeses.setAdapter(adapter);
    }
}