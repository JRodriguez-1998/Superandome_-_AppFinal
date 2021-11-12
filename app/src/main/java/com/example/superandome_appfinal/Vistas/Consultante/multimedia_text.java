package com.example.superandome_appfinal.Vistas.Consultante;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Base64.*;


import android.os.Environment;
import android.os.FileUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.superandome_appfinal.R;
import com.github.barteksc.pdfviewer.PDFView;


public class multimedia_text extends Fragment {
    PDFView pdf;
    TextView txt;

    public multimedia_text(){};

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        String path = Environment.getExternalStorageDirectory()+"/Download/HOLA.pdf";
        // String filename = "HOLA.pdf";

        File file = new File(path);

//        Path ruta = file.toPath();


        //FUNCA
//        File file = new File(requireContext().getExternalFilesDir(null),"HOLA.pdf");
        byte[] bytesDato= new byte[0];
        try {


            bytesDato = Files.readAllBytes(file.toPath());


        } catch (IOException e) {
            e.printStackTrace();
        }

        String resultado = Base64.getEncoder().encodeToString(bytesDato);


        txt = (TextView) view.findViewById(R.id.txtPrueba);
        txt.setText(resultado);


        byte[] decoder = Base64.getDecoder().decode(resultado);
        //byte[] decodedString



        pdf = (PDFView) view.findViewById(R.id.pdfView);
        pdf.fromBytes(decoder).load();


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_multimedia_text, container, false);
    }
}



