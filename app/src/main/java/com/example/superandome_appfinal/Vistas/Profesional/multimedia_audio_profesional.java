package com.example.superandome_appfinal.Vistas.Profesional;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioTrack;
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
import com.example.superandome_appfinal.IServices.ContenidoService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.ContenidoServiceImpl;

import java.nio.file.Files;
import java.nio.file.Path;

public class multimedia_audio_profesional extends Fragment {
    MediaPlayer mp = null;
    AudioTrack audioTrack;
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


            SharedPreferences preferences = requireActivity().getSharedPreferences("contenido", Context.MODE_PRIVATE);
            idContenido = preferences.getInt("idContenido", 0);

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

            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    seekBar.setMax(mp.getDuration());
                    mediaPlayer.start();
                    updateSeekbar();
                }
            });

            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer player) {
                    mp.seekTo(0);
                    mp.pause();
                    btnPause.setImageResource(R.drawable.btnplay);
                }
            });

            btnPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mp.isPlaying()){
                        mp.pause();
                        btnPause.setImageResource(R.drawable.btnplay);
                        Toast.makeText(getContext(),"Audio pausado",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        mp.start();
                        btnPause.setImageResource(R.drawable.btnpause);
                        Toast.makeText(getContext(),"Audio reanudado",Toast.LENGTH_SHORT).show();
                    }
                }
            });

            btnStop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mp.seekTo(0);
                    mp.pause();
                    Toast.makeText(getContext(),"Audio detenido",Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Ha ocurrido un error al inicializar la pantalla", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateSeekbar() {
        int currpos = mp.getCurrentPosition();
        seekBar.setProgress(currpos);
        runnable= new Runnable() {
            @Override
            public void run() {
                updateSeekbar();
            }
        };
        handler.postDelayed(runnable,1000);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_multimedia_audio_profesional, container, false);
    }
}
