package com.example.superandome_appfinal.Vistas.Consultante;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.superandome_appfinal.Dialogos.dialogoCambiarPass;
import com.example.superandome_appfinal.Dialogos.dialogoErrorFragment;
import com.example.superandome_appfinal.Dialogos.dialogoErrorPass;
import com.example.superandome_appfinal.Entidades.PreguntaSeguridad;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.Helpers.SessionManager;
import com.example.superandome_appfinal.IServices.UsuarioService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.PreguntaSeguridadServiceImpl;
import com.example.superandome_appfinal.Services.UsuarioServiceImpl;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class cambiar_password_consultante extends Fragment {

    EditText txtPass, txtPassNueva, txtConfirPassNueva, txtRespuesta;
    TextView txtNickname;
    Button btnConfirmar;
    Spinner spinnerPreguntas;

    String nameUsuario;

    UsuarioService usuarioService;
    PreguntaSeguridadServiceImpl preguntaService;
    List<PreguntaSeguridad> listaPreguntas = new ArrayList<PreguntaSeguridad>();

    Usuario user;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtNickname = (TextView) view.findViewById(R.id.tvNicknameUsuario);
        txtPass = (EditText) view.findViewById(R.id.editTextPassActual);
        txtPassNueva = (EditText) view.findViewById(R.id.editTextPassNueva);
        txtConfirPassNueva = (EditText) view.findViewById(R.id.editTextConfPassNueva);
        btnConfirmar = (Button) view.findViewById(R.id.btnConfirmar);

        txtRespuesta = (EditText) view.findViewById(R.id.txtRespuesta);
        spinnerPreguntas = (Spinner) view.findViewById(R.id.spPregunta);

        try {
            nameUsuario = SessionManager.obtenerUsuario(requireActivity()).getNickname();

            // Instancio el Servicio del Usuario
            usuarioService = new UsuarioServiceImpl();
            preguntaService = new PreguntaSeguridadServiceImpl();

            user = usuarioService.getUsuario(nameUsuario);

            txtNickname.setText(user.getNickname());

            PreguntaSeguridad pregunta = new PreguntaSeguridad();
            pregunta.setIdPreguntaSeguridad(0);
            pregunta.setDescripcion("Seleccione su pregunta de seguridad");

            listaPreguntas.add(pregunta);

            for(int i = 0; i < preguntaService.getPreguntas().size(); i++){
                listaPreguntas.add(preguntaService.getPreguntas().get(i));
            }

            ArrayAdapter<PreguntaSeguridad> adapter = new ArrayAdapter<PreguntaSeguridad>(getActivity(), R.layout.spinner_item_tipoconsejo, listaPreguntas);
            spinnerPreguntas.setAdapter(adapter);

            btnConfirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cambiarContrasenia();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            dialogoErrorFragment d = new dialogoErrorFragment();
            d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_errorfragment");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cambiar_password_consultante, container, false);
    }

    public void cambiarContrasenia(){

        PreguntaSeguridad pregunta = (PreguntaSeguridad) spinnerPreguntas.getSelectedItem();

        if(txtPass.getText().toString().equals("") || txtPassNueva.getText().toString().equals("") || txtConfirPassNueva.getText().toString().equals("")){
            //Toast.makeText(getActivity(),"Campos incompletos, por favor ingrese todos los campos.", Toast.LENGTH_SHORT).show();
            dialogoErrorPass d = new dialogoErrorPass("Campos incompletos, por favor ingrese todos los campos.");
            d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_error_pass");
            return;
        }

        if(spinnerPreguntas.getSelectedItemPosition() == 0){
            //Toast.makeText(getActivity(), "Seleccione una pregunta de seguridad", Toast.LENGTH_LONG).show();
            dialogoErrorPass d = new dialogoErrorPass("Seleccione una Pregunta de Seguridad");
            d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_error_pass");
            return;
        }

        if(txtRespuesta.getText().toString().isEmpty()){
            //Toast.makeText(getActivity(), "Debe responder la respuesta", Toast.LENGTH_LONG).show();
            dialogoErrorPass d = new dialogoErrorPass("Debe responder la respuesta");
            d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_error_pass");
            return;
        }

        String passActual = txtPass.getText().toString();

        if(!user.getPassword().equals(doHash(passActual))){
            //Toast.makeText(getActivity(),"La Contaseña Actual ingresado no coincide con el Registrado.", Toast.LENGTH_SHORT).show();
            dialogoErrorPass d = new dialogoErrorPass("La Contraseña Actual ingresado no coincide con el Registrado.");
            d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_error_pass");
            return;
        }

        String passNueva = txtPassNueva.getText().toString();
        String passConfir = txtConfirPassNueva.getText().toString();

        if(!passNueva.equals(passConfir)){
            //Toast.makeText(getActivity(),"Las nuevas contraseñas no coinciden.", Toast.LENGTH_SHORT).show();
            dialogoErrorPass d = new dialogoErrorPass("Las nuevas contraseñas no coinciden.");
            d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_error_pass");
            return;
        }

        if(passNueva.equals(passActual)){
            //Toast.makeText(getActivity(),"La nueva contraseña es igual a la Actual.", Toast.LENGTH_SHORT).show();
            dialogoErrorPass d = new dialogoErrorPass("La nueva contraseña es igual a la Actual.");
            d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_error_pass");
            return;
        }

        if(pregunta.getIdPreguntaSeguridad() == user.getPreguntaSeguridad().getIdPreguntaSeguridad()){
            if(!txtRespuesta.getText().toString().toLowerCase().equals(user.getRespuesta().toString().toLowerCase())){
                //Toast.makeText(getActivity(), "RESPUESTA INCORRECTA", Toast.LENGTH_LONG).show();
                dialogoErrorPass d = new dialogoErrorPass("La Respuesta es Incorrecta");
                d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_error_pass");
                return;
            }
        }else{
            //Toast.makeText(getActivity(), "Revise la pregunta seleccionada", Toast.LENGTH_LONG).show();
            dialogoErrorPass d = new dialogoErrorPass("La Pregunta es errónea.");
            d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_error_pass");
            return;
        }

        user.setPassword(doHash(passNueva));

        if(usuarioService.actualizar(user)){
            Toast.makeText(getActivity(),"Contraseña Modificada.", Toast.LENGTH_LONG).show();
            txtPass.setText("");
            txtPassNueva.setText("");
            txtConfirPassNueva.setText("");
            txtRespuesta.setText("");
            spinnerPreguntas.setSelection(0);
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
