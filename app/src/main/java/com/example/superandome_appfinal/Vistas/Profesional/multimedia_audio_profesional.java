package com.example.superandome_appfinal.Vistas.Profesional;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.superandome_appfinal.Entidades.Contenido;
import com.example.superandome_appfinal.Helpers.SessionManager;
import com.example.superandome_appfinal.IServices.ContenidoService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.ContenidoServiceImpl;

import java.nio.file.Files;
import java.nio.file.Path;

public class multimedia_audio_profesional extends Fragment {
    MediaPlayer mp = null;
    ImageButton btnPause,btnStop;
    SeekBar seekBar;
    Runnable runnable;
    Handler handler;
    Integer idContenido;

    public multimedia_audio_profesional() {
        // Required empty public constructor
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        try {
            btnPause = (ImageButton)view.findViewById(R.id.btnPause);
            btnStop = (ImageButton)view.findViewById(R.id.btnStop);
            seekBar = (SeekBar)view.findViewById(R.id.progresoAudio);
            btnPause.setImageResource(R.drawable.btnpause);
            btnStop.setImageResource(R.drawable.btnstop);


            idContenido = SessionManager.getIdContenido(requireActivity());

            ContenidoService contenidoService = new ContenidoServiceImpl();
            Contenido contenido = contenidoService.getContenidoByID(idContenido);
            byte[] decoder = Base64.decode(contenido.getArchivo(), Base64.DEFAULT);
            Path tempfile = Files.createTempFile(null,null);
            Files.write(tempfile,decoder);
            mp = new MediaPlayer();
            mp.setDataSource(tempfile.toString());
            mp.prepareAsync();
            mp.setVolume(100f,100f);
            mp.setLooping(false);
            handler = new Handler();
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    if (b){
                        mp.seekTo(i);
                        seekBar.setProgress(i);
                    }
                }
                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }
                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });

            mp.setOnPreparedListener(mediaPlayer -> {
                seekBar.setMax(mp.getDuration());
                mediaPlayer.start();
                updateSeekbar();
            });

            mp.setOnCompletionListener(player -> parar());

            btnPause.setOnClickListener(view1 -> pausar());

            btnStop.setOnClickListener(view2 -> parar());

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Ha ocurrido un error al inicializar la pantalla", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_multimedia_audio_profesional, container, false);
    }

    private void updateSeekbar() {
        int currpos = mp.getCurrentPosition();
        seekBar.setProgress(currpos);
        runnable = new Runnable() {
            @Override
            public void run() {
                updateSeekbar();
            }
        };
        handler.postDelayed(runnable,1000);
    }

    private void pausar() {
        if (mp.isPlaying()) {
            mp.pause();
            btnPause.setImageResource(R.drawable.btnplay);
        }
        else {
            mp.start();
            btnPause.setImageResource(R.drawable.btnpause);
        }
    }

    private void parar() {
        mp.seekTo(0);
        mp.pause();
        btnPause.setImageResource(R.drawable.btnplay);
    }

    @Override
    public void onPause() {
        super.onPause();
        parar();
    }
}
