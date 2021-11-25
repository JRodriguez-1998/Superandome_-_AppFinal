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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.superandome_appfinal.Constantes.TipoEmocionEnum;
import com.example.superandome_appfinal.Dialogos.dialogoNoHayDatos;
import com.example.superandome_appfinal.Dialogos.dialogoProximamente;
import com.example.superandome_appfinal.Helpers.SessionManager;
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
import java.util.Objects;

public class reporteEmocion extends Fragment {

    private final int COLOR_ASCO = ColorTemplate.rgb("23a44f");
    private final int COLOR_IRA = ColorTemplate.rgb("dc3221");
    private final int COLOR_FELICIDAD = ColorTemplate.rgb("ef971b");
    private final int COLOR_MIEDO = ColorTemplate.rgb("922580");
    private final int COLOR_TRISTEZA = ColorTemplate.rgb("0477ba");

    private PieChart chart;
    private Spinner spMeses;

    Integer idUsuario;
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
        try {
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


            service = new EmocionUsuarioServiceImpl();

            idUsuario = SessionManager.obtenerUsuario(requireActivity()).getIdUsuario();


            LoadSpinner();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Ha ocurrido un error al inicializar la pantalla", Toast.LENGTH_SHORT).show();
        }
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

            Map<Integer, Float> map = service.getReporteMensualEmocion(idUsuario, fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH) + 1);
            if (map == null) {
                Toast.makeText(getActivity(), "Ha ocurrido un error al obtener el reporte", Toast.LENGTH_SHORT).show();
                return;
            }
            if (map.size() == 0) {
                dialogoNoHayDatos d = new dialogoNoHayDatos();
                d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_nohaydatos");
                return;
            }

            List<Integer> colors = new ArrayList<>();
            List<PieEntry> entries = new ArrayList<>();
            LoadEntriesColors(map, entries, colors);

            PieDataSet dataSet = new PieDataSet(entries, "Torta");
            dataSet.setColors(colors);

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

    private void LoadEntriesColors(Map<Integer, Float> map, List<PieEntry> entries, List<Integer> colors) {
        if (map.get(TipoEmocionEnum.ASCO.getTipo()) != null) {
            entries.add(new PieEntry(map.get(TipoEmocionEnum.ASCO.getTipo()), "Asco"));
            colors.add(COLOR_ASCO);
        }
        if (map.get(TipoEmocionEnum.IRA.getTipo()) != null) {
            entries.add(new PieEntry(map.get(TipoEmocionEnum.IRA.getTipo()), "Ira"));
            colors.add(COLOR_IRA);
        }
        if (map.get(TipoEmocionEnum.FELICIDAD.getTipo()) != null) {
            entries.add(new PieEntry(map.get(TipoEmocionEnum.FELICIDAD.getTipo()), "Felicidad"));
            colors.add(COLOR_FELICIDAD);
        }
        if (map.get(TipoEmocionEnum.MIEDO.getTipo()) != null) {
            entries.add(new PieEntry(map.get(TipoEmocionEnum.MIEDO.getTipo()), "Miedo"));
            colors.add(COLOR_MIEDO);
        }
        if (map.get(TipoEmocionEnum.TRISTERZA.getTipo()) != null) {
            entries.add(new PieEntry(map.get(TipoEmocionEnum.TRISTERZA.getTipo()), "Tristeza"));
            colors.add(COLOR_TRISTEZA);
        }
    }
}