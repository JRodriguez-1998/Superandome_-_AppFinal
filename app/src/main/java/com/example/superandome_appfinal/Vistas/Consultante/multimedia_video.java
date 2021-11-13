package com.example.superandome_appfinal.Vistas.Consultante;

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
import android.widget.VideoView;

import com.example.superandome_appfinal.Entidades.Contenido;
import com.example.superandome_appfinal.IServices.ContenidoService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.ContenidoServiceImpl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Base64;


public class multimedia_video extends Fragment {
    VideoView videoView;

    public multimedia_video() {
        // Required empty public constructor
    }

   // @RequiresApi(api = Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        videoView = (VideoView) view.findViewById(R.id.videoView2);

        ContenidoService contenidoService = null;
        try {
            contenidoService = new ContenidoServiceImpl();



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Contenido contenido = contenidoService.getContenidoByID(21);

        byte[] decoder = Base64.getDecoder().decode(contenido.getArchivo());

        try (FileOutputStream fos = new FileOutputStream("/path/file")) {
            fos.write(decoder);
            //fos.close // no need, try-with-resources auto close
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        videoView.set









    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_multimedia_video, container, false);
    }
}