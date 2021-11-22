package com.example.superandome_appfinal.Vistas.Consultante;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superandome_appfinal.Constantes.EstadoEnum;
import com.example.superandome_appfinal.Constantes.TipoConsejoEnum;
import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.Helpers.SessionManager;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.ConsejoServiceImpl;
import com.example.superandome_appfinal.Services.UsuarioServiceImpl;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class homeConsultante extends Fragment {

    TextView txtConsejoDiario;
    List<Consejo> listaConsejos;
    ConsejoServiceImpl consejoService;
    UsuarioServiceImpl usuarioService;

    Integer idUsuario;

    ImageView btnface, btninsta, btnwpp;

    public homeConsultante() {}

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            iniciarServicios();

            idUsuario = SessionManager.obtenerUsuario(requireActivity()).getIdUsuario();

            txtConsejoDiario = (TextView) view.findViewById(R.id.txtConsejoDia);

            listaConsejos = consejoService.getConsejosByEstadoAndTipo(EstadoEnum.APROBADO_DIRECTOR.getId(), TipoConsejoEnum.GENERAL.getTipo());
            Usuario usuario = usuarioService.getUsuarioById(idUsuario);

            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            Integer fechaAct = cal.get(Calendar.DATE);

            Random numAleatorio = new Random(fechaAct + usuario.getIdUsuario());
            int n = numAleatorio.nextInt(listaConsejos.size() -1 +1);

            txtConsejoDiario.setText(listaConsejos.get(n).getTexto());

            btnface = view.findViewById(R.id.btnFacebook);
            btninsta = view.findViewById(R.id.btnInstagram);
            btnwpp = view.findViewById(R.id.btnWhatsapp);

            btnface.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = "https://www.facebook.com/Soyuz-Salud-Mental-102791508151767/";
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(intent);
                }
            });

            btninsta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = "https://www.instagram.com/soyuzsaludmental/";
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(intent);
                }
            });

            btnwpp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = "https://api.whatsapp.com/send/?phone=573202487093&text=Hola%2C+quiero+ponerme+en+contacto+con+Soyuz&app_absent=0";
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Ha ocurrido un error al inicializar la pantalla", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_consultante, container, false);
    }

    public void iniciarServicios(){
        try {
            consejoService = new ConsejoServiceImpl();
            usuarioService = new UsuarioServiceImpl();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Toast.makeText(getContext(), "Error al inicializar servicios", Toast.LENGTH_SHORT).show();
        }
    }
}