package com.example.superandome_appfinal.Dialogos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.UsuarioServiceImpl;
import com.example.superandome_appfinal.Vistas.Consultante.homeConsultante;
import com.example.superandome_appfinal.Vistas.activity_login;

import java.security.MessageDigest;
import java.sql.SQLException;

public class dialogoCambiarPass extends DialogFragment {

    Activity actividad;
    EditText txtPassword, txtRepetirPass;
    TextView btnAceptar;
    FragmentTransaction transaction;
    Fragment fragmentHome;
    String nickName;
    UsuarioServiceImpl usuarioService;

    public dialogoCambiarPass() {}

    public dialogoCambiarPass(String nickName) {
        this.nickName = nickName;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return crearDialogo();
    }

    private AlertDialog crearDialogo(){
        AlertDialog.Builder builder  = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_dialogo_cambiar_pass,null);
        builder.setView(v);

        txtPassword = (EditText) v.findViewById(R.id.txtPass);
        txtRepetirPass = (EditText) v.findViewById(R.id.txtRepitaPass);
        btnAceptar = (TextView) v.findViewById(R.id.btnAceptarDialogCambioPass);


        eventoBotones();


        return builder.create();
    }

    private void eventoBotones(){
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarServicios();
                Usuario usuario = usuarioService.getUsuario(nickName);

                if(txtPassword.getText().toString().isEmpty() || txtRepetirPass.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Debe completar ambos campos.", Toast.LENGTH_LONG).show();
                    return;
                }

                if(txtPassword.getText().toString().equals(usuario.getPassword().toString())){
                    Toast.makeText(getActivity(), "La contraseña debe ser distinta.", Toast.LENGTH_LONG).show();
                    return;
                }

                if(txtPassword.getText().toString().equals(txtRepetirPass.getText().toString())){
                    usuario.setPassword(doHash(txtPassword.getText().toString()));

                    if(usuarioService.actualizar(usuario)){
                        Toast.makeText(getActivity(), "Contraseña actualizada", Toast.LENGTH_LONG).show();
                        dismiss();
                        Intent intent = new Intent(getActivity(), activity_login.class);
                        getActivity().startActivity(intent);
                    }

                }else{
                    Toast.makeText(getActivity(), "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.actividad  = (Activity) context;
        }else{
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialogo_cambiar_pass, container, false);
    }

    public void iniciarServicios(){
        try {
            usuarioService = new UsuarioServiceImpl();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public String doHash(String password){
        try {
            MessageDigest msgDiggest = MessageDigest.getInstance("MD5");
            msgDiggest.update(password.getBytes());
            byte[] resultado = msgDiggest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : resultado){
                sb.append(String.format("%02x",b));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(),"Error al hashear", Toast.LENGTH_SHORT).show();
        }
        return "";
    }
}