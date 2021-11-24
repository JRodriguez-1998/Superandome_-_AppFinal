package com.example.superandome_appfinal.Vistas.Profesional;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.Helpers.SessionManager;
import com.example.superandome_appfinal.IServices.UsuarioService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.UsuarioServiceImpl;

import java.security.MessageDigest;

public class cambiar_password_profesional extends Fragment {

    EditText txtPass, txtPassNueva, txtConfirPassNueva;
    TextView txtNickname;
    Button btnConfirmar;

    String nameUsuario;

    UsuarioService usuarioService;

    Usuario user;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {

            txtNickname = (TextView) view.findViewById(R.id.tvNicknameUsuario);
            txtPass = (EditText) view.findViewById(R.id.editTextPassActual);
            txtPassNueva = (EditText) view.findViewById(R.id.editTextPassNueva);
            txtConfirPassNueva = (EditText) view.findViewById(R.id.editTextConfPassNueva);
            btnConfirmar = (Button) view.findViewById(R.id.btnConfirmar);


            //Instancio el Servicio del Usuario
            usuarioService = new UsuarioServiceImpl();
            nameUsuario = SessionManager.obtenerUsuario(requireActivity()).getNickname();

            user = usuarioService.getUsuario(nameUsuario);

            txtNickname.setText(user.getNickname());



            btnConfirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cambiarContrasenia();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Ha ocurrido un error al inicializar la pantalla", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cambiar_password_profesional, container, false);
    }

    public void cambiarContrasenia(){
        if(txtPass.getText().toString().equals("") || txtPassNueva.getText().toString().equals("") || txtConfirPassNueva.getText().toString().equals("")){
            Toast.makeText(getActivity(),"Campos incompletos, por favor ingrese todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        String passActual = txtPass.getText().toString();

        if(!user.getPassword().equals(doHash(passActual))){
            Toast.makeText(getActivity(),"La Contaseña Actual ingresado no coincide con el Registrado.", Toast.LENGTH_SHORT).show();
            return;
        }

        String passNueva = txtPassNueva.getText().toString();
        String passConfir = txtConfirPassNueva.getText().toString();

        if(!passNueva.equals(passConfir)){
            Toast.makeText(getActivity(),"Las nuevas contraseñas no coinciden.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(passNueva.equals(passActual)){
            Toast.makeText(getActivity(),"La nueva contraseña es igual a la Actual.", Toast.LENGTH_SHORT).show();
            return;
        }

        user.setPassword(doHash(passNueva));

        if(usuarioService.actualizar(user)){
            Toast.makeText(getActivity(),"Contraseña Modificada.", Toast.LENGTH_LONG).show();
            txtPass.setText("");
            txtPassNueva.setText("");
            txtConfirPassNueva.setText("");
        }else{
            Toast.makeText(getActivity(),"ERROR", Toast.LENGTH_LONG).show();
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
