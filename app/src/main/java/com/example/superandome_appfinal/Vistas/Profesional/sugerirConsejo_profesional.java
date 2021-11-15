package com.example.superandome_appfinal.Vistas.Profesional;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superandome_appfinal.Constantes.EstadoEnum;
import com.example.superandome_appfinal.Dialogos.dialogoSugerirConsejo;
import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.Estado;
import com.example.superandome_appfinal.Entidades.TipoConsejo;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.IServices.ConsejoService;
import com.example.superandome_appfinal.IServices.UsuarioService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.ConsejoServiceImpl;
import com.example.superandome_appfinal.Services.TipoConsejoServiceImpl;
import com.example.superandome_appfinal.Services.UsuarioServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class sugerirConsejo_profesional extends Fragment {

    Button btnEnviar;
    EditText txtConsejo;
    TipoConsejoServiceImpl tipoConsejoService;
    ConsejoService consejoService;

    //Creo Objeto SharedPreferences para utilizar para las Sesiones
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    String nameUsuario;

    UsuarioService usuarioService;

    Usuario user;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnEnviar = (Button) view.findViewById(R.id.btnEnviarSugerenciaProf);
        txtConsejo = (EditText) view.findViewById(R.id.txtConsejoSugeridoProf);

        try {



        preferences = this.getActivity().getSharedPreferences("sesiones", Context.MODE_PRIVATE);
        editor = preferences.edit();

        nameUsuario = preferences.getString("nickname",null);


            tipoConsejoService = new TipoConsejoServiceImpl();
            consejoService = new ConsejoServiceImpl();
            usuarioService = new UsuarioServiceImpl();


        TipoConsejo consejo = new TipoConsejo(0, "Seleccionar tipo consejo");

        List<TipoConsejo> listTipoConsejo = new ArrayList<TipoConsejo>();
        listTipoConsejo.add(consejo);

        for(int i = 0; i< tipoConsejoService.getTipoConsejos().size(); i ++){
            listTipoConsejo.add(tipoConsejoService.getTipoConsejos().get(i));
        }

        Spinner spinnerTipoConsejo = (Spinner) view.findViewById(R.id.spinnerTipoConsejoProf);
        ArrayAdapter<TipoConsejo> adapter = new ArrayAdapter<TipoConsejo>(getContext(), R.layout.spinner_item_tipoconsejo, listTipoConsejo);
        spinnerTipoConsejo.setAdapter(adapter);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtConsejo.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"Completar campos para continuar", Toast.LENGTH_LONG).show();
                    return;
                }
                if(spinnerTipoConsejo.getSelectedItemPosition() == 0){
                    Toast.makeText(getActivity(),"Seleccionar tipo de consejo", Toast.LENGTH_LONG).show();
                    return;
                }

                //Usuario usuario = new Usuario();
                //usuario.setIdUsuario(99);
                user = usuarioService.getUsuario(nameUsuario);

                Estado estado = new Estado(EstadoEnum.APROBADO_PROFESIONAL.getId());
                TipoConsejo tipoConsejo = (TipoConsejo)spinnerTipoConsejo.getSelectedItem();
                Consejo consejo = new Consejo(txtConsejo.getText().toString(), tipoConsejo, estado, user);

                if(consejoService.guardar(consejo)){
                    dialogoSugerirConsejo d = new dialogoSugerirConsejo();
                    d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_sugerir_consejo");
                    txtConsejo.setText("");
                }else{
                    Toast.makeText(getActivity(),"ERROR", Toast.LENGTH_LONG).show();
                }
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