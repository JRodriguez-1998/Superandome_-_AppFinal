package com.example.superandome_appfinal.Vistas.Consultante;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.superandome_appfinal.Dialogos.dialogoErrorFragment;
import com.example.superandome_appfinal.Entidades.Contenido;
import com.example.superandome_appfinal.Helpers.SessionManager;
import com.example.superandome_appfinal.IServices.ContenidoService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.ContenidoServiceImpl;
import com.github.barteksc.pdfviewer.PDFView;


public class multimedia_text extends Fragment {
    PDFView pdf;
    Integer idContenido;

    public multimedia_text(){};

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            idContenido = SessionManager.getIdContenido(requireActivity());

            ContenidoService contenidoService = new ContenidoServiceImpl();

            Contenido contenido = contenidoService.getContenidoByID(idContenido);

            // Borra todos los espacios del base64.
            String base64 = contenido.getArchivo().replaceAll("\\s+","");

            byte[] decoder = Base64.getDecoder().decode(base64);

            pdf = view.findViewById(R.id.pdfView);
            pdf.fromBytes(decoder).load();
        } catch (Exception e) {
            e.printStackTrace();
            dialogoErrorFragment d = new dialogoErrorFragment();
            d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_errorfragment");
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_multimedia_text, container, false);
    }

}



