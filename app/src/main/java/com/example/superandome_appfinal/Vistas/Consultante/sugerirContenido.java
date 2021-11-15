package com.example.superandome_appfinal.Vistas.Consultante;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superandome_appfinal.Dialogos.dialogoSugerirConsejo;
import com.example.superandome_appfinal.Dialogos.dialogoSugerirContenido;
import com.example.superandome_appfinal.Entidades.Contenido;
import com.example.superandome_appfinal.Entidades.Estado;
import com.example.superandome_appfinal.Entidades.TipoArchivo;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.IServices.ContenidoService;
import com.example.superandome_appfinal.IServices.EstadoService;
import com.example.superandome_appfinal.IServices.TipoArchivoService;
import com.example.superandome_appfinal.IServices.UsuarioService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.ContenidoServiceImpl;
import com.example.superandome_appfinal.Services.UsuarioServiceImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Base64;

public class sugerirContenido extends Fragment {
    Button btnElegirArchivo,btnGuardar;
    TextView txtTipoContenido;
   // ActivityResultLauncher<String> contenido;
    final int FILE_PICKER_REQUEST_CODE = 100;

    byte[] bytesDato;
    Contenido  content;
    ContenidoService contenidoService;
    Usuario usuario;
    UsuarioService usuarioService;
    EstadoService estadoService;
    Estado estado;
    TipoArchivo tipoArchivo;
    String tipo = new String();

    public sugerirContenido(){};

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        btnElegirArchivo = (Button) view.findViewById(R.id.btnElegirArchivo);
        btnGuardar = (Button) view.findViewById(R.id.button);
        txtTipoContenido = (TextView) view.findViewById(R.id.tvTipoContenido);
        try {

        btnGuardar.setEnabled(false);

        txtTipoContenido.setText("");

        btnElegirArchivo.setOnClickListener(view1 -> showFileChooser() );
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Ha ocurrido un error al inicializar la pantalla", Toast.LENGTH_SHORT).show();
        }
    }

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent, "Seleccionar archivo"), 100);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Error al seleccionar", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (requestCode == FILE_PICKER_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
                    //            String path = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
                    //            if (path != null) {
                    Uri uri = data.getData();
                    String pathStr = uri.getPath();
//                File file = new File(path);
//                String fileStr = String.valueOf(file);
                    if (pathStr.contains("document/raw:"))
                        pathStr = pathStr.replace("/document/raw:", "");

                    Path path = Paths.get(pathStr);
                    Boolean exists = Files.exists(path);

                    String cadenaPath = new String();
                    cadenaPath = path.toString();

//                    txtAutor.setText(MessageFormat.format("" +
//                                    "Path: {0}\n\n",
//                            path
//                    ));

//                    txtTipoContenido.setText(cadenaPath);
                    btnGuardar.setEnabled(true);

                    bytesDato = new byte[0];

                    bytesDato = Files.readAllBytes(path);
                    int x = 1;


                    String resultado = Base64.getEncoder().encodeToString(bytesDato);

                    InicializarServicio();

                    String[] partesName = cadenaPath.split("/");

                    int nombrePost = partesName.length;
                   // partesName[0].get;

                    content = new Contenido();
                    content.setUsuario(usuario);
                    content.setArchivo(resultado);
                    content.setEstado(estado);
                    content.setNombreArchivo(partesName[nombrePost-1]);


                    //txtAutor.setText(partesName[nombrePost-1]);

                    for(int i = 0; i<4;i++){

                        tipo += resultado.charAt(i);

                    }
                    if(tipo.equals("JVBE")){
                        tipoArchivo.setIdTipoArchivo(1);
                        content.setTipoArchivo(tipoArchivo);
                        txtTipoContenido.setText("PDF");
                    }
                    if (tipo.equals("/+MY")) {
                        tipoArchivo.setIdTipoArchivo(3);
                        content.setTipoArchivo(tipoArchivo);
                        txtTipoContenido.setText("Audio");
                    }
                    if (tipo.equals("AAAA")) {
                        tipoArchivo.setIdTipoArchivo(2);
                        content.setTipoArchivo(tipoArchivo);
                        txtTipoContenido.setText("Video");
                    }

                    btnGuardar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(contenidoService.guardar(content)){
                                dialogoSugerirContenido d = new dialogoSugerirContenido();
                                d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_sugerir_consejo");
                            }
                            else{
                                Toast.makeText(getActivity(),"Error al cargar",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//                    txt = (TextView) view.findViewById(R.id.txtPrueba);
//                    txt.setText(resultado);


  //                  byte[] decoder = Base64.getDecoder().decode(resultado);
                    //byte[] decodedString


//                    pdf = (PDFView) view.findViewById(R.id.pdfView);
//                    pdf.fromBytes(decoder).load();
                }
            }
            super.onActivityResult(requestCode, resultCode, data);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Error al cargar", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sugerir_contenido,container,false);
    }

    public void InicializarServicio(){
        try {
            usuarioService = new UsuarioServiceImpl();
            contenidoService = new ContenidoServiceImpl();
            SharedPreferences preferences = requireActivity().getSharedPreferences("sesiones", Context.MODE_PRIVATE);
            int idUsuario = preferences.getInt("idUser", 0);

            usuario = usuarioService.getUsuarioById(idUsuario);

            estado = new Estado();
            estado.setIdEstado(5);

            tipoArchivo = new TipoArchivo();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Error al inicializar servicios", Toast.LENGTH_SHORT).show();
        }
    }
}