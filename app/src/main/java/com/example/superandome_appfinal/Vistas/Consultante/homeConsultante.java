package com.example.superandome_appfinal.Vistas.Consultante;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.ConsejoServiceImpl;
import com.example.superandome_appfinal.Services.EmocionServiceImpl;
import com.example.superandome_appfinal.Services.EmocionUsuarioServiceImpl;
import com.example.superandome_appfinal.Services.UsuarioServiceImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class homeConsultante extends Fragment {

    TextView txtConsejoDiario;
    List<Consejo> listaConsejos;
    ConsejoServiceImpl consejoService;

    public homeConsultante() {}

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iniciarServicios();
        txtConsejoDiario = (TextView) view.findViewById(R.id.txtConsejoDia);

        listaConsejos = consejoService.getConsejosByEstado(1);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        Integer fechaAct = cal.get(Calendar.DATE);

        Random numAleatorio = new Random(fechaAct);
        int n = numAleatorio.nextInt(listaConsejos.size() -1 +1);

        txtConsejoDiario.setText(listaConsejos.get(n).getTexto());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_consultante, container, false);
    }

    public void iniciarServicios(){
        try {
            consejoService = new ConsejoServiceImpl();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}