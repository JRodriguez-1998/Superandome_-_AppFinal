package com.example.superandome_appfinal.Vistas.Consultante;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.superandome_appfinal.Dialogos.dialogoErrorFragment;
import com.example.superandome_appfinal.Entidades.Contenido;
import com.example.superandome_appfinal.Helpers.SessionManager;
import com.example.superandome_appfinal.IServices.ContenidoService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.ContenidoServiceImpl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;


public class multimedia_video extends Fragment {
    VideoView videoView;
    MediaController mediaController;
    InputStream inputStream;
    Integer idContenido;

    public multimedia_video() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        try {
            idContenido = SessionManager.getIdContenido(requireActivity());

            videoView = view.findViewById(R.id.videoView2);

            ContenidoService contenidoService = new ContenidoServiceImpl();
            Contenido contenido = contenidoService.getContenidoByID(idContenido);

            byte[] decoder = Base64.decode(contenido.getArchivo(), Base64.DEFAULT);
            inputStream = new ByteArrayInputStream(decoder);

            mediaController = new MediaController(getContext());

            mediaController.setMediaPlayer(videoView);
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
            videoView.requestFocus();

            run();

        } catch (Exception e) {
            e.printStackTrace();
            dialogoErrorFragment d = new dialogoErrorFragment();
            d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_errorfragment");
        }
    }


    public void run() {
        try {
            File temp = File.createTempFile("test", "mp4");
            temp.deleteOnExit();

            File bufferFile = File.createTempFile("test", "mp4");
            BufferedOutputStream bufferOS = new BufferedOutputStream(new FileOutputStream(bufferFile));


            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 2048);

            byte[] buffer = new byte[16384];
            long totalRead = 0;
            boolean started = false;
            int numRead = bufferedInputStream.read(buffer);
            while (numRead != -1) {
                bufferOS.write(buffer, 0, numRead);
                bufferOS.flush();

                totalRead += numRead;
                if (totalRead > 120000 && !started) {
                    setSourceAndStartPlay(bufferFile);
                    started = true;
                }

                numRead = bufferedInputStream.read(buffer);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void setSourceAndStartPlay(File bufferFile) {
        try {
//            videoView.getCurrentPosition();
            videoView.setVideoPath(bufferFile.getAbsolutePath());

//            videoView.start();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_multimedia_video, container, false);
    }
}