package com.example.superandome_appfinal.Vistas.Profesional;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superandome_appfinal.Constantes.EstadoEnum;
import com.example.superandome_appfinal.Constantes.TipoArchivoEnum;
import com.example.superandome_appfinal.Dialogos.dialogoSugerirContenido;
import com.example.superandome_appfinal.Dialogos.dialogoSugerirContenidoProf;
import com.example.superandome_appfinal.Entidades.Contenido;
import com.example.superandome_appfinal.Entidades.Estado;
import com.example.superandome_appfinal.Entidades.TipoArchivo;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.Helpers.SessionManager;
import com.example.superandome_appfinal.IServices.ContenidoService;
import com.example.superandome_appfinal.IServices.EstadoService;
import com.example.superandome_appfinal.IServices.UsuarioService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.ContenidoServiceImpl;
import com.example.superandome_appfinal.Services.UsuarioServiceImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class sugerirContenido_profesional extends Fragment {

    Button btnElegirArchivo,btnGuardar;
    TextView txtTipoContenido;
    // ActivityResultLauncher<String> contenido;
    final int FILE_PICKER_REQUEST_CODE = 100;

    byte[] bytesDato;
    Contenido content;
    ContenidoService contenidoService;
    Usuario usuario;
    UsuarioService usuarioService;
    EstadoService estadoService;
    Estado estado;
    TipoArchivo tipoArchivo;
    String tipo = new String();

    public sugerirContenido_profesional(){};

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btnElegirArchivo = (Button) view.findViewById(R.id.btnElegirArchivoProf);
        btnGuardar = (Button) view.findViewById(R.id.btnGuardarArchivoProf);
        txtTipoContenido = (TextView) view.findViewById(R.id.tvTipoContenidoProf);

        try {
            InicializarServicio();


            btnGuardar.setEnabled(false);

            txtTipoContenido.setText("");

            btnElegirArchivo.setOnClickListener(view1 -> showFileChooser());
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Error al inicializar pantalla", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sugerir_contenido_profesional, container, false);
    }

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent, "Seleccionar fila"), 100);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Error al seleccionar fila", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (requestCode == FILE_PICKER_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {

                    Uri uri = data.getData();
                    String pathStr = uri.getPath();

                    if (pathStr.contains("document/raw:"))
                        pathStr = pathStr.replace("/document/raw:", "");

                    Path path = Paths.get(pathStr);
                    Boolean exists = Files.exists(path);

                    String cadenaPath = path.toString();

                    btnGuardar.setEnabled(true);

                    bytesDato = new byte[0];
                    bytesDato = Files.readAllBytes(path);

                    String resultado = Base64.getEncoder().encodeToString(bytesDato);

                    // Borra todos los espacios del base64.
                    String base64 = resultado.replaceAll("\\s+","");

                    InicializarServicio();

                    String[] partesName = cadenaPath.split("/");
                    String nombreArchivo = partesName[partesName.length-1];

                    content = new Contenido(nombreArchivo, base64, estado, usuario);

                    tipo = base64.substring(0, 4);
                    switch (tipo) {
                        case "JVBE":
                            tipoArchivo.setIdTipoArchivo(TipoArchivoEnum.PDF.getTipo());
                            txtTipoContenido.setText("PDF");
                            break;
                        case "SUQz":
                            tipoArchivo.setIdTipoArchivo(TipoArchivoEnum.AUDIO.getTipo());
                            txtTipoContenido.setText("Audio");
                            break;
                        case "AAAA":
                            tipoArchivo.setIdTipoArchivo(TipoArchivoEnum.VIDEO.getTipo());
                            txtTipoContenido.setText("Video");
                            break;
                        default:
                            throw new Exception("No se encontrÃ³ el tipo de archivo");
                    }

                    content.setTipoArchivo(tipoArchivo);

                    btnGuardar.setOnClickListener(view -> {
                        if (contenidoService.guardar(content)) {
                            dialogoSugerirContenidoProf d = new dialogoSugerirContenidoProf();
                            d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_sugerir_contenido_prof");
                        }
                        else {
                            Toast.makeText(getActivity(),"Error al cargar",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
            super.onActivityResult(requestCode, resultCode, data);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Error al cargar", Toast.LENGTH_SHORT).show();
        }
    }

    public void InicializarServicio() throws Exception {
        usuarioService = new UsuarioServiceImpl();
        contenidoService = new ContenidoServiceImpl();

        int idUsuario = SessionManager.obtenerUsuario(requireActivity()).getIdUsuario();

        usuario = usuarioService.getUsuarioById(idUsuario);

        estado = new Estado(EstadoEnum.APROBADO_PROFESIONAL.getId());

        tipoArchivo = new TipoArchivo();
    }
}