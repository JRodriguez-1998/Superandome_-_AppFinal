package com.example.superandome_appfinal.Vistas.Consultante;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.superandome_appfinal.Constantes.TipoEmocionEnum;
import com.example.superandome_appfinal.Dialogos.dialogoErrorFragment;
import com.example.superandome_appfinal.Dialogos.dialogoErrorInesperado;
import com.example.superandome_appfinal.Dialogos.dialogoNoHayDatos;
import com.example.superandome_appfinal.Helpers.SessionManager;
import com.example.superandome_appfinal.IServices.EmocionUsuarioService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.EmocionUsuarioServiceImpl;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class reporteEmocion extends Fragment {

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
        try {
            chart = v.findViewById(R.id.chart);
            StylePieChart();

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

            service = new EmocionUsuarioServiceImpl();

            idUsuario = SessionManager.obtenerUsuario(requireActivity()).getIdUsuario();

            LoadSpinner();
        } catch (Exception e) {
            e.printStackTrace();
            dialogoErrorFragment d = new dialogoErrorFragment();
            d.show(requireActivity().getSupportFragmentManager(), "fragment_dialogo_errorfragment");
        }

        return v;
    }

    private void StylePieChart() {
        chart.setNoDataText("No hay datos registrados para el período seleccionado");
        chart.setNoDataTextColor(getResources().getColor(R.color.profesional));
        chart.setNoDataTextTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

        chart.setUsePercentValues(true);
        chart.setEntryLabelColor(getResources().getColor(R.color.white));
        chart.setEntryLabelTextSize(18);

        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);

        chart.setHoleColor(getResources().getColor(R.color.consultante));
        chart.setTransparentCircleAlpha(45);
    }

    private void LoadSpinner() {
        List<String> listMeses = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, -i);

            Integer anio = c.get(Calendar.YEAR);
            String mes = c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());

            String anio_mes = anio + " - " + mes;
            listMeses.add(anio_mes);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item_mes, listMeses);
        spMeses.setAdapter(adapter);
    }

    private Calendar GetSelectedDate() {
        int pos = spMeses.getSelectedItemPosition();

        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -(pos));

        return c;
    }

    private void LoadPieChart() {
        try {
            Calendar fecha = GetSelectedDate();

            Map<Integer, Float> map = service.getReporteMensualEmocion(idUsuario, fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH) + 1);
            if (map == null) {
                chart.setNoDataText("Ha ocurrido un error inesperado");
                chart.clear();
                dialogoErrorInesperado d = new dialogoErrorInesperado();
                d.show(requireActivity().getSupportFragmentManager(), "fragment_dialogo_errorinesperado");
                return;
            }
            // TODO: Cachear los resultados
            if (map.size() == 0) {
                chart.setNoDataText("No hay datos registrados para el período seleccionado");
                chart.clear();
                dialogoNoHayDatos d = new dialogoNoHayDatos();
                d.show(requireActivity().getSupportFragmentManager(), "fragment_dialogo_nohaydatos");
                return;
            }

            List<Integer> colors = new ArrayList<>();
            List<PieEntry> entries = new ArrayList<>();
            LoadEntriesColors(map, entries, colors);

            PieDataSet dataSet = new PieDataSet(entries, "Torta");
            dataSet.setColors(colors);
            dataSet.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    // Pone el formato "12.25%"
                    return String.format(Locale.getDefault(), "%.2f%%", value);
                }
            });

            PieData data = new PieData(dataSet);
            data.setValueTextColor(getResources().getColor(R.color.white));
            data.setValueTextSize(18);

            chart.setData(data);
            chart.invalidate();
            chart.spin(1000, 0f, 270f, Easing.EaseInOutSine);
        } catch (Exception e) {
            dialogoErrorInesperado d = new dialogoErrorInesperado();
            d.show(requireActivity().getSupportFragmentManager(), "fragment_dialogo_errorinesperado");
            e.printStackTrace();
        }
    }

    @SuppressWarnings("ConstantConditions")
    private void LoadEntriesColors(Map<Integer, Float> map, List<PieEntry> entries, List<Integer> colors) {
        if (map.get(TipoEmocionEnum.ASCO.getTipo()) != null) {
            entries.add(new PieEntry(map.get(TipoEmocionEnum.ASCO.getTipo()), "Asco"));
            colors.add(getResources().getColor(R.color.asco));
        }
        if (map.get(TipoEmocionEnum.IRA.getTipo()) != null) {
            entries.add(new PieEntry(map.get(TipoEmocionEnum.IRA.getTipo()), "Ira"));
            colors.add(getResources().getColor(R.color.ira));
        }
        if (map.get(TipoEmocionEnum.FELICIDAD.getTipo()) != null) {
            entries.add(new PieEntry(map.get(TipoEmocionEnum.FELICIDAD.getTipo()), "Felicidad"));
            colors.add(getResources().getColor(R.color.felicidad));
        }
        if (map.get(TipoEmocionEnum.MIEDO.getTipo()) != null) {
            entries.add(new PieEntry(map.get(TipoEmocionEnum.MIEDO.getTipo()), "Miedo"));
            colors.add(getResources().getColor(R.color.miedo));
        }
        if (map.get(TipoEmocionEnum.TRISTERZA.getTipo()) != null) {
            entries.add(new PieEntry(map.get(TipoEmocionEnum.TRISTERZA.getTipo()), "Tristeza"));
            colors.add(getResources().getColor(R.color.tristeza));
        }
    }
}