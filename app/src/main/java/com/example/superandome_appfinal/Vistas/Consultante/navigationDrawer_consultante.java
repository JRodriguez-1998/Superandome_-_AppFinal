package com.example.superandome_appfinal.Vistas.Consultante;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superandome_appfinal.Constantes.TipoUsuarioEnum;
import com.example.superandome_appfinal.Dialogos.cerrarSesion;
import com.example.superandome_appfinal.Dialogos.dialogoConfHorario;
import com.example.superandome_appfinal.Dialogos.dialogoUserConsultanteRegistrado;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.EmocionServiceImpl;
import com.example.superandome_appfinal.Services.EmocionUsuarioServiceImpl;
import com.example.superandome_appfinal.Services.UsuarioServiceImpl;
import com.example.superandome_appfinal.Vistas.about;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.superandome_appfinal.databinding.ActivityNavigationDrawerConsultanteBinding;

import java.sql.SQLException;
import java.util.Date;

public class navigationDrawer_consultante extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavigationDrawerConsultanteBinding binding;
    NavigationView navigationView;

    TextView txtNombreUser;
    int idUser;
    int tipoUser;
    String nameUsuario;

    EmocionUsuarioServiceImpl emocionUserService;

    //Creo Objeto SharedPreferences para utilizar para las Sesiones
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {


        binding = ActivityNavigationDrawerConsultanteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarNavigationDrawerConsultante.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_cargarEmocionDiaria, R.id.nav_configurarHorario, R.id.nav_sugerirContenido, R.id.nav_sugerirConsejo,
                R.id.nav_rutinaDiaria, R.id.nav_sugerirContenido_profesional, R.id.nav_sugerirConsejo_profesional, R.id.nav_reporteEmocion,
                R.id.nav_reporteRutina,R.id.nav_multimedia,R.id.nav_multimedia_video, R.id.nav_altaProfesional, R.id.nav_aprobarContenido_director,R.id.nav_multimedia_text,
                R.id.nav_pregunta_seguridad, R.id.nav_homeConsultante, R.id.nav_cerrarSesion, R.id.nav_cambiar_password, R.id.nav_cambiar_password_profesional,R.id.nav_rutinaDiariaSeguimiento,
                R.id.nav_derivarConsejo_profesional, R.id.nav_aprobarConsejo_director, R.id.nav_homeProfesional, R.id.nav_homeDirector, R.id.nav_indexEncuestas, R.id.nav_derivarContenido_profesional)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation_drawer_consultante);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        iniciarServicios();

        preferences = getSharedPreferences("sesiones", Context.MODE_PRIVATE);
        editor = preferences.edit();

        //Recibo los 3 datos enviados desde Login
        //tipoUser = (int) getIntent().getSerializableExtra("tipoUser");
        //nameUsuario = (String) getIntent().getSerializableExtra("nickname");
        //idUser = (int) getIntent().getSerializableExtra("idUser");

        obtenerPreferences();

        //Creo una variable View para obtener del navigationView el header, y asi setear el TextView del nombre
        View navHeader = navigationView.getHeaderView(0);
        txtNombreUser = (TextView) navHeader.findViewById(R.id.tvNombreUserLogin);
        txtNombreUser.setText(nameUsuario);

        binding.appBarNavigationDrawerConsultante.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (TipoUsuarioEnum.getTipoUsuario(tipoUser)) {
                    case CONSULTANTE:
                        navController.navigate(R.id.nav_homeConsultante);
                        break;
                    case PROFESIONAL:
                        navController.navigate(R.id.nav_homeProfesional);
                        break;
                    case DIRECTOR:
                        navController.navigate(R.id.nav_homeDirector);
                        break;
                }
            }
        });

        hideAllItems();
        //Reemplazar parametro con tipoUser que viene del Login
        switch (TipoUsuarioEnum.getTipoUsuario(tipoUser)) {
            case CONSULTANTE:
                showItemsConsultante();
                navController.navigate(R.id.nav_homeConsultante);
                break;
            case PROFESIONAL:
                showItemsProfesional();
                navController.navigate(R.id.nav_homeProfesional);
                break;
            case DIRECTOR:
                showItemsDirector();
                navController.navigate(R.id.nav_homeDirector);
                break;
        }
    }catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Ha ocurrido un error al inicializar la pantalla", Toast.LENGTH_SHORT).show();
        }}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_drawer_consultante, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                about d = new about();
                d.show(this.getSupportFragmentManager(), "fragment_about");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation_drawer_consultante);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void hideAllItems() {
        Menu navMenu = navigationView.getMenu();
        navMenu.findItem(R.id.nav_homeConsultante).setVisible(false);
        navMenu.findItem(R.id.nav_homeProfesional).setVisible(false);
        navMenu.findItem(R.id.nav_homeDirector).setVisible(false);
        navMenu.findItem(R.id.nav_cargarEmocionDiaria).setVisible(false);
        navMenu.findItem(R.id.nav_configurarHorario).setVisible(false);
        navMenu.findItem(R.id.nav_sugerirContenido).setVisible(false);
        navMenu.findItem(R.id.nav_reporteEmocion).setVisible(false);
        navMenu.findItem(R.id.nav_reporteRutina).setVisible(false);
        navMenu.findItem(R.id.nav_sugerirConsejo).setVisible(false);
        navMenu.findItem(R.id.nav_indexEncuestas).setVisible(false);
        navMenu.findItem(R.id.nav_rutinaDiaria).setVisible(false);
        navMenu.findItem(R.id.nav_multimedia).setVisible(false);
        navMenu.findItem(R.id.nav_multimedia_text).setVisible(false);
        navMenu.findItem(R.id.nav_multimedia_video).setVisible(false);
        navMenu.findItem(R.id.nav_sugerirContenido_profesional).setVisible(false);
        navMenu.findItem(R.id.nav_sugerirConsejo_profesional).setVisible(false);
        navMenu.findItem(R.id.nav_altaProfesional).setVisible(false);
        navMenu.findItem(R.id.nav_aprobarContenido_director).setVisible(false);
        navMenu.findItem(R.id.nav_pregunta_seguridad).setVisible(false);
        navMenu.findItem(R.id.nav_cambiar_password).setVisible(false);
        navMenu.findItem(R.id.nav_cambiar_password_profesional).setVisible(false);
        navMenu.findItem(R.id.nav_cerrarSesion).setVisible(false);
        navMenu.findItem(R.id.nav_derivarConsejo_profesional).setVisible(false);
        navMenu.findItem(R.id.nav_rutinaDiariaSeguimiento).setVisible(false);
        navMenu.findItem(R.id.nav_aprobarConsejo_director).setVisible(false);
        navMenu.findItem(R.id.nav_derivarContenido_profesional).setVisible(false);
    }

    private void showItemsConsultante() {
        Menu navMenu = navigationView.getMenu();

        navMenu.findItem(R.id.nav_homeConsultante).setVisible(true);

        if (emocionUserService.getEmocionByFechaAndId(idUser, new Date()) != null) {
            navMenu.findItem(R.id.nav_cargarEmocionDiaria).setEnabled(false);
        }
        navMenu.findItem(R.id.nav_cargarEmocionDiaria).setChecked(false);
        navMenu.findItem(R.id.nav_cargarEmocionDiaria).setVisible(true);
        navMenu.findItem(R.id.nav_configurarHorario).setVisible(true);
        navMenu.findItem(R.id.nav_sugerirContenido).setVisible(true);
        navMenu.findItem(R.id.nav_reporteEmocion).setVisible(true);
        navMenu.findItem(R.id.nav_reporteRutina).setVisible(true);
        navMenu.findItem(R.id.nav_sugerirConsejo).setVisible(true);
        navMenu.findItem(R.id.nav_indexEncuestas).setVisible(true);
        navMenu.findItem(R.id.nav_rutinaDiaria).setVisible(true);
        navMenu.findItem(R.id.nav_multimedia).setVisible(true);
        navMenu.findItem(R.id.nav_multimedia_text).setVisible(true);
        navMenu.findItem(R.id.nav_multimedia_video).setVisible(true);
        navMenu.findItem(R.id.nav_rutinaDiariaSeguimiento).setVisible(true);

        navMenu.findItem(R.id.nav_cerrarSesion).setVisible(true);

        navMenu.findItem(R.id.nav_cerrarSesion).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                cerrarSesion cerrar = new cerrarSesion();
                cerrar.show(getSupportFragmentManager(), "fragment_dialogo_cerrar_sesion");
                return true;
            }
        });
    }

    private void showItemsProfesional() {
        Menu navMenu = navigationView.getMenu();
        navMenu.findItem(R.id.nav_homeProfesional).setVisible(true);
        navMenu.findItem(R.id.nav_sugerirContenido_profesional).setVisible(true);
        navMenu.findItem(R.id.nav_sugerirConsejo_profesional).setVisible(true);
        navMenu.findItem(R.id.nav_derivarConsejo_profesional).setVisible(true);
        navMenu.findItem(R.id.nav_derivarContenido_profesional).setVisible(true);
        navMenu.findItem(R.id.nav_cambiar_password_profesional).setVisible(true);
        navMenu.findItem(R.id.nav_cerrarSesion).setVisible(true);

        navMenu.findItem(R.id.nav_cerrarSesion).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                cerrarSesion cerrar = new cerrarSesion();
                cerrar.show(getSupportFragmentManager(), "fragment_dialogo_cerrar_sesion");
                return true;
            }
        });
    }

    private void showItemsDirector() {
        Menu navMenu = navigationView.getMenu();
        navMenu.findItem(R.id.nav_homeDirector).setVisible(true);
        navMenu.findItem(R.id.nav_altaProfesional).setVisible(true);
        navMenu.findItem(R.id.nav_aprobarContenido_director).setVisible(true);
        navMenu.findItem(R.id.nav_aprobarConsejo_director).setVisible(true);
        navMenu.findItem(R.id.nav_cambiar_password).setVisible(true);
        navMenu.findItem(R.id.nav_cerrarSesion).setVisible(true);

        navMenu.findItem(R.id.nav_cerrarSesion).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                cerrarSesion cerrar = new cerrarSesion();
                cerrar.show(getSupportFragmentManager(), "fragment_dialogo_cerrar_sesion");
                return true;
            }
        });
    }

    public void iniciarServicios(){
        try {
            emocionUserService= new EmocionUsuarioServiceImpl();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void obtenerPreferences(){
        idUser = preferences.getInt("idUser",0);
        tipoUser = preferences.getInt("tipoUser",0);
        nameUsuario = preferences.getString("nickname",null);
    }
}