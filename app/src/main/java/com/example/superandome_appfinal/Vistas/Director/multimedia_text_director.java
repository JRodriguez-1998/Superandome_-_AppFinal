package com.example.superandome_appfinal.Vistas.Director;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.superandome_appfinal.Entidades.Contenido;
import com.example.superandome_appfinal.IServices.ContenidoService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.ContenidoServiceImpl;
import com.github.barteksc.pdfviewer.PDFView;

import java.util.Base64;

public class multimedia_text_director extends Fragment {
    PDFView pdf;
    TextView txt;
    Integer idContenido;

    public multimedia_text_director(){};

    @RequiresApi(api = Build.VERSION_CODES.O)
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

            byte[] decoder = Base64.getDecoder().decode(contenido.getArchivo());
            //byte[] decodedString



            pdf = (PDFView) view.findViewById(R.id.pdfView);
            pdf.fromBytes(decoder).load();
        }catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Ha ocurrido un error al inicializar la pantalla", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_multimedia_text_director, container, false);
    }
}
