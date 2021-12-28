package com.example.superandome_appfinal.Vistas.Profesional;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.superandome_appfinal.Constantes.EstadoEnum;
import com.example.superandome_appfinal.Dialogos.dialogoSugerirConsejo;
import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.Estado;
import com.example.superandome_appfinal.Entidades.TipoConsejo;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.Helpers.SessionManager;
import com.example.superandome_appfinal.IServices.ConsejoService;
import com.example.superandome_appfinal.IServices.UsuarioService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.ConsejoServiceImpl;
import com.example.superandome_appfinal.Services.TipoConsejoServiceImpl;
import com.example.superandome_appfinal.Services.UsuarioServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class sugerirConsejo_profesional extends Fragment {

    Button btnEnviar;
    EditText txtConsejo;
    TipoConsejoServiceImpl tipoConsejoService;
    ConsejoService consejoService;

    String nameUsuario;

    UsuarioService usuarioService;

    Usuario user;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnEnviar = (Button) view.findViewById(R.id.btnEnviarSugerenciaProf);
        txtConsejo = (EditText) view.findViewById(R.id.txtConsejoSugeridoProf);

        try {
            tipoConsejoService = new TipoConsejoServiceImpl();
            consejoService = new ConsejoServiceImpl();
            usuarioService = new UsuarioServiceImpl();

            nameUsuario = SessionManager.obtenerUsuario(requireActivity()).getNickname();

            TipoConsejo consejo = new TipoConsejo(0, "Seleccionar tipo consejo");

            List<TipoConsejo> listTipoConsejo = new ArrayList<>();
            listTipoConsejo.add(consejo);
            listTipoConsejo.addAll(tipoConsejoService.getTipoConsejos());

            Spinner spinnerTipoConsejo = (Spinner) view.findViewById(R.id.spinnerTipoConsejoProf);
            ArrayAdapter<TipoConsejo> adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item_tipoconsejo, listTipoConsejo);
            spinnerTipoConsejo.setAdapter(adapter);

            // Código para poder scrollear dentro del EditText
            txtConsejo.setOnTouchListener((v, motionEvent) -> {
                if (!txtConsejo.hasFocus())
                    return false;

                v.getParent().requestDisallowInterceptTouchEvent(true);
                if ((motionEvent.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_SCROLL) {
                    v.getParent().requestDisallowInterceptTouchEvent(false);
                    return true;
                }

                return false;
            });

            btnEnviar.setOnClickListener(view1 -> {
                if (txtConsejo.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"Completar campos para continuar", Toast.LENGTH_LONG).show();
                    return;
                }
                if (spinnerTipoConsejo.getSelectedItemPosition() == 0){
                    Toast.makeText(getActivity(),"Seleccionar tipo de consejo", Toast.LENGTH_LONG).show();
                    return;
                }

                user = usuarioService.getUsuario(nameUsuario);

                Estado estado = new Estado(EstadoEnum.APROBADO_PROFESIONAL.getId());
                TipoConsejo tipoConsejo = (TipoConsejo)spinnerTipoConsejo.getSelectedItem();
                Consejo consejo1 = new Consejo(txtConsejo.getText().toString(), tipoConsejo, estado, user);

                if (consejoService.guardar(consejo1)){
                    dialogoSugerirConsejo d = new dialogoSugerirConsejo();
                    d.show(requireActivity().getSupportFragmentManager(), "fragment_dialogo_sugerir_consejo");
                    txtConsejo.setText("");
                } else{
                    Toast.makeText(getActivity(),"ERROR", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Error al cargar", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sugerir_consejo_profesional, container, false);
    }
}