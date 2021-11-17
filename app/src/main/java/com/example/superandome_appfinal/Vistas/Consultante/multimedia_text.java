package com.example.superandome_appfinal.Vistas.Consultante;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superandome_appfinal.Entidades.Contenido;
import com.example.superandome_appfinal.IServices.ContenidoService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.ContenidoServiceImpl;
import com.github.barteksc.pdfviewer.PDFView;


public class multimedia_text extends Fragment {
    PDFView pdf;
    TextView txt;
    Integer idContenido;

    public multimedia_text(){};


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            SharedPreferences preferences = requireActivity().getSharedPreferences("contenido", Context.MODE_PRIVATE);
            idContenido = preferences.getInt("idContenido", 0);

//        String path = Environment.getExternalStorageDirectory()+"/Download/HOLA.pdf";
//        // String filename = "HOLA.pdf";
//
//        File file = new File(path);
//
////        Path ruta = file.toPath();
//
//
//        //FUNCA
////        File file = new File(requireContext().getExternalFilesDir(null),"HOLA.pdf");
//        byte[] bytesDato= new byte[0];
//        try {
//
//
//            bytesDato = Files.readAllBytes(file.toPath());
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        String resultado = Base64.getEncoder().encodeToString(bytesDato);
//
//
//        txt = (TextView) view.findViewById(R.id.txtPrueba);
//        txt.setText(resultado);
            ContenidoService contenidoService = null;

            contenidoService = new ContenidoServiceImpl();

            Contenido contenido = contenidoService.getContenidoByID(idContenido);

            byte[] decoder = Base64.decode(contenido.getArchivo(), Base64.DEFAULT);
            //byte[] decodedString

            pdf = (PDFView) view.findViewById(R.id.pdfView);
            pdf.fromBytes(decoder).load();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Ha ocurrido un error al inicializar la pantalla", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_multimedia_text, container, false);
    }

}



