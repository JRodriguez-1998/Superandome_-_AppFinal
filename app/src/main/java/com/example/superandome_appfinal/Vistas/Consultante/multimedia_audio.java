package com.example.superandome_appfinal.Vistas.Consultante;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.AudioTrack.Builder;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.superandome_appfinal.Entidades.Contenido;
import com.example.superandome_appfinal.IServices.ContenidoService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.ContenidoServiceImpl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;


public class multimedia_audio extends Fragment {
    MediaPlayer mp = null;
    AudioTrack audioTrack;

    public multimedia_audio() {
        // Required empty public constructor
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
    try {


        ContenidoService contenidoService = new ContenidoServiceImpl();
        Contenido contenido = contenidoService.getContenidoByID(7);
        byte[] decoder = Base64.decode(contenido.getArchivo(), Base64.DEFAULT);

        Path tempfile = Files.createTempFile(null,null);
        Files.write(tempfile,decoder);

        mp = new MediaPlayer();
        mp.setDataSource(tempfile.toString());
        mp.prepareAsync();
        mp.setVolume(100f,100f);
        mp.setLooping(false);

        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer player) {
                player.stop();
                player.release();
            }
        });




//        final byte[] bytesAudioDataDecrypted = YourUtils.decryptFileToteArray(bytesAudioData);
//
//        ByteArrayMediaDataSource bds = new ByteArrayMediaDataSource(bytesAudioDataDecrypted) ;
//        mediaPlayer.setDataSource(bds);


//        audioTrack = AudioTrack(new AudioAttributes.Builder()
//                .setUsage(AudioAttributes.USAGE_MEDIA)
//                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
//                .build(),
//                new AudioFormat.Builder()
//                .setChannelMask(AudioFormat.CHANNEL_IN_DEFAULT)
//                .setSampleRate(AudioFormat.SAMPLE_RATE_UNSPECIFIED)
//                .setEncoding(AudioFormat.ENCODING_MP3)
//                .build(),
//                ,
//                AudioTrack.MODE_STREAM,
//                AudioManager.AUDIO_SESSION_ID_GENERATE);
//
//
//
//        );








       // AudioTrack audio =  new AudioTrack();





    } catch (Exception e) {
        e.printStackTrace();
        Toast.makeText(getActivity(), "Ha ocurrido un error al inicializar la pantalla", Toast.LENGTH_SHORT).show();
    }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_multimedia_audio, container, false);
    }
}