package com.example.superandome_appfinal.Vistas.Consultante;

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

import com.example.superandome_appfinal.Constantes.EstadoEnum;
import com.example.superandome_appfinal.Dialogos.dialogoCompletarSugerir;
import com.example.superandome_appfinal.Dialogos.dialogoErrorFragment;
import com.example.superandome_appfinal.Dialogos.dialogoErrorInesperado;
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
import com.example.superandome_appfinal.Dialogos.dialogoSugerirConsejo;
import com.example.superandome_appfinal.Services.UsuarioServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class sugerirConsejo extends Fragment {

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

        btnEnviar = (Button) view.findViewById(R.id.btnEnviarSugerencia);
        txtConsejo = (EditText) view.findViewById(R.id.txtConsejoSugerido);

        try {
            tipoConsejoService = new TipoConsejoServiceImpl();
            consejoService = new ConsejoServiceImpl();
            usuarioService = new UsuarioServiceImpl();

            nameUsuario = SessionManager.obtenerUsuario(requireActivity()).getNickname();

            TipoConsejo consejo = new TipoConsejo(0, "Seleccionar tipo consejo");

            List<TipoConsejo> listTipoConsejo = new ArrayList<>();
            listTipoConsejo.add(consejo);
            listTipoConsejo.addAll(tipoConsejoService.getTipoConsejos());

            Spinner spinnerTipoConsejo = (Spinner) view.findViewById(R.id.spinnerTipoConsejo);
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

            btnEnviar.setOnClickListener(view12 -> {
                if (txtConsejo.getText().toString().isEmpty()){
                    dialogoCompletarSugerir d = new dialogoCompletarSugerir();
                    d.show(requireActivity().getSupportFragmentManager(), "fragment_dialogo_completarsugerir");
                    return;
                }
                if (spinnerTipoConsejo.getSelectedItemPosition() == 0){
                    dialogoCompletarSugerir d = new dialogoCompletarSugerir();
                    d.show(requireActivity().getSupportFragmentManager(), "fragment_dialogo_completarsugerir");
                    return;
                }

                user = usuarioService.getUsuario(nameUsuario);

                Estado estado = new Estado(EstadoEnum.PENDIENTE.getId());

                TipoConsejo tipoConsejo = (TipoConsejo)spinnerTipoConsejo.getSelectedItem();
                Consejo consejo1 = new Consejo(txtConsejo.getText().toString(), tipoConsejo, estado, user);

                if (consejoService.guardar(consejo1)){
                    dialogoSugerirConsejo d = new dialogoSugerirConsejo();
                    d.show(requireActivity().getSupportFragmentManager(), "fragment_dialogo_sugerir_consejo");
                    txtConsejo.setText("");
                } else{
                    dialogoErrorInesperado d = new dialogoErrorInesperado();
                    d.show(requireActivity().getSupportFragmentManager(), "fragment_dialogo_errorinesperado");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            dialogoErrorFragment d = new dialogoErrorFragment();
            d.show(requireActivity().getSupportFragmentManager(), "fragment_dialogo_errorfragment");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sugerir_consejo, container, false);
    }
}