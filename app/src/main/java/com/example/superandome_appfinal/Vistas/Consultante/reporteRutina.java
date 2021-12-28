package com.example.superandome_appfinal.Vistas.Consultante;

import android.animation.ObjectAnimator;
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

import com.example.superandome_appfinal.Constantes.ItemRutinaEnum;
import com.example.superandome_appfinal.Dialogos.dialogoErrorFragment;
import com.example.superandome_appfinal.Dialogos.dialogoErrorInesperado;
import com.example.superandome_appfinal.Helpers.SessionManager;
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

    private ProgressBar barDieta;
    private ProgressBar barConsumo;
    private ProgressBar barEjercicio;
    private ProgressBar barDescanso;
    private ProgressBar barAmbiente;
    private ProgressBar barHigiene;
    private ProgressBar barEquilibrio;
    private ProgressBar barSocial;
    private ProgressBar barOcio;
    private ProgressBar barNaturaleza;

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

            service = new ItemUsuarioDiarioServiceImpl();

            idUsuario = SessionManager.obtenerUsuario(requireActivity()).getIdUsuario();

            LoadSpinner();
        } catch (Exception e) {
            e.printStackTrace();
            dialogoErrorFragment d = new dialogoErrorFragment();
            d.show(requireActivity().getSupportFragmentManager(), "fragment_dialogo_errorfragment");
        }
        return v;
    }

    private void FindViews(View v) {
        spMeses = v.findViewById(R.id.spMeses);

        barDieta = v.findViewById(R.id.barDieta);
        barConsumo = v.findViewById(R.id.barConsumo);
        barEjercicio = v.findViewById(R.id.barEjercicio);
        barDescanso = v.findViewById(R.id.barDescanso);
        barAmbiente = v.findViewById(R.id.barAmbiente);
        barHigiene = v.findViewById(R.id.barHigiene);
        barEquilibrio = v.findViewById(R.id.barEquilibrio);
        barSocial = v.findViewById(R.id.barSocial);
        barOcio = v.findViewById(R.id.barOcio);
        barNaturaleza = v.findViewById(R.id.barNaturaleza);

        txtResultado = v.findViewById(R.id.txtResultadoRutina);
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

    @SuppressWarnings("ConstantConditions")
    private void LoadProgressBars() {
        try {
            Calendar fecha = GetSelectedDate();

            Map<Integer, Float> map = service.getReporteMensualRutina(idUsuario, fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH) + 1);
            if (map == null) {
                dialogoErrorInesperado d = new dialogoErrorInesperado();
                d.show(requireActivity().getSupportFragmentManager(), "fragment_dialogo_errorinesperado");
                return;
            }
            // TODO: Cachear los resultados
//            if (map.size() == 0) {
//                dialogoNoHayDatos d = new dialogoNoHayDatos();
//                d.show(requireActivity().getSupportFragmentManager(), "fragment_dialogo_nohaydatos");
//                return;
//            }


//            barDieta.setProgress(Math.round(map.get(ItemRutinaEnum.DIETA.getId())));
//            barConsumo.setProgress(Math.round(map.get(ItemRutinaEnum.CONSUMO.getId())));
//            barEjercicio.setProgress(Math.round(map.get(ItemRutinaEnum.EJERCICIO.getId())));
//            barDescanso.setProgress(Math.round(map.get(ItemRutinaEnum.DESCANSO.getId())));
//            barAmbiente.setProgress(Math.round(map.get(ItemRutinaEnum.AMBIENTE.getId())));
//            barHigiene.setProgress(Math.round(map.get(ItemRutinaEnum.HIGIENE.getId())));
//            barEquilibrio.setProgress(Math.round(map.get(ItemRutinaEnum.EQUILIBRIO.getId())));
//            barSocial.setProgress(Math.round(map.get(ItemRutinaEnum.SOCIAL.getId())));
//            barOcio.setProgress(Math.round(map.get(ItemRutinaEnum.OCIO.getId())));
//            barNaturaleza.setProgress(Math.round(map.get(ItemRutinaEnum.NATURALEZA.getId())));

            // Seteo todos los progress bar con el porcentaje correspondiente, mediante animaciones
            final int animationDuration = 500;
            ObjectAnimator.ofInt(barDieta, "progress", Math.round(map.get(ItemRutinaEnum.DIETA.getId())))
                    .setDuration(animationDuration)
                    .start();
            ObjectAnimator.ofInt(barConsumo, "progress", Math.round(map.get(ItemRutinaEnum.CONSUMO.getId())))
                    .setDuration(animationDuration)
                    .start();
            ObjectAnimator.ofInt(barEjercicio, "progress", Math.round(map.get(ItemRutinaEnum.EJERCICIO.getId())))
                    .setDuration(animationDuration)
                    .start();
            ObjectAnimator.ofInt(barDescanso, "progress", Math.round(map.get(ItemRutinaEnum.DESCANSO.getId())))
                    .setDuration(animationDuration)
                    .start();
            ObjectAnimator.ofInt(barAmbiente, "progress", Math.round(map.get(ItemRutinaEnum.AMBIENTE.getId())))
                    .setDuration(animationDuration)
                    .start();
            ObjectAnimator.ofInt(barHigiene, "progress", Math.round(map.get(ItemRutinaEnum.HIGIENE.getId())))
                    .setDuration(animationDuration)
                    .start();
            ObjectAnimator.ofInt(barEquilibrio, "progress", Math.round(map.get(ItemRutinaEnum.EQUILIBRIO.getId())))
                    .setDuration(animationDuration)
                    .start();
            ObjectAnimator.ofInt(barSocial, "progress", Math.round(map.get(ItemRutinaEnum.SOCIAL.getId())))
                    .setDuration(animationDuration)
                    .start();
            ObjectAnimator.ofInt(barOcio, "progress", Math.round(map.get(ItemRutinaEnum.OCIO.getId())))
                    .setDuration(animationDuration)
                    .start();
            ObjectAnimator.ofInt(barNaturaleza, "progress", Math.round(map.get(ItemRutinaEnum.NATURALEZA.getId())))
                    .setDuration(animationDuration)
                    .start();

            LoadResultado(map);

        } catch (Exception e) {
            dialogoErrorInesperado d = new dialogoErrorInesperado();
            d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_errorinesperado");
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