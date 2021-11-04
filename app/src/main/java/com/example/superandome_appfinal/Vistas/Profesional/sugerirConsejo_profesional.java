package com.example.superandome_appfinal.Vistas.Profesional;

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

import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.R;

import java.util.Date;

public class sugerirConsejo_profesional extends Fragment {

    Button btnEnviar;
    EditText txtConsejo;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnEnviar = (Button) view.findViewById(R.id.btnEnviarSugerenciaProf);
        txtConsejo = (EditText) view.findViewById(R.id.txtConsejoSugeridoProf);

        Spinner spinnerTipoConsejo = (Spinner) view.findViewById(R.id.spinnerTipoConsejoProf);
        String [] tipoConsejos = {"Seleccione Tipo de consejo", "Bronca","Alegria","Tristeza"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item_tipoconsejo, tipoConsejos);
        spinnerTipoConsejo.setAdapter(adapter);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtConsejo.getText().toString().isEmpty()){
                    if(spinnerTipoConsejo.getSelectedItemPosition() != 0){
                        Toast.makeText(getActivity(),"Listo para enviar", Toast.LENGTH_LONG).show();
                        Consejo consejo = new Consejo(txtConsejo.getText().toString(),null,null,null, new Date());
                    }else{
                        Toast.makeText(getActivity(),"Seleccionar tipo de consejo", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getActivity(),"Completar campos para continuar", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sugerir_consejo_profesional, container, false);
    }
}