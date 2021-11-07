package com.example.superandome_appfinal.Vistas.Consultante;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.example.superandome_appfinal.Constantes.TipoUsuarioEnum;
import com.example.superandome_appfinal.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.superandome_appfinal.databinding.ActivityNavigationDrawerConsultanteBinding;

public class navigationDrawer_consultante extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavigationDrawerConsultanteBinding binding;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNavigationDrawerConsultanteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarNavigationDrawerConsultante.toolbar);
        binding.appBarNavigationDrawerConsultante.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_cargarEmocionDiaria, R.id.nav_configurarHorario, R.id.nav_sugerirContenido, R.id.nav_sugerirConsejo,
                R.id.nav_ingresarEncuesta,R.id.nav_rutinaDiaria, R.id.nav_sugerirContenido_profesional, R.id.nav_sugerirConsejo_profesional, R.id.nav_reporteEmocion, R.id.nav_reporteRutina,R.id.nav_multimedia,R.id.nav_multimedia_video, R.id.nav_altaProfesional, R.id.nav_aprobarContenido_director,R.id.nav_multimedia_text,R.id.nav_pregunta_seguridad)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation_drawer_consultante);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        int idTipoUsuario = 1;
        hideAllItems();
        switch (TipoUsuarioEnum.getTipoUsuario(idTipoUsuario)) {
            case CONSULTANTE:
                showItemsConsultante();
                navController.navigate(R.id.nav_cargarEmocionDiaria);
                break;
            case PROFESIONAL:
                showItemsProfesional();
                navController.navigate(R.id.nav_sugerirContenido);
                break;
            case DIRECTOR:
                showItemsDirector();
                navController.navigate(R.id.nav_altaProfesional);
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_drawer_consultante, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation_drawer_consultante);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void hideAllItems() {
        Menu navMenu = navigationView.getMenu();
        navMenu.findItem(R.id.nav_cargarEmocionDiaria).setVisible(false);
        navMenu.findItem(R.id.nav_configurarHorario).setVisible(false);
        navMenu.findItem(R.id.nav_sugerirContenido).setVisible(false);
        navMenu.findItem(R.id.nav_reporteEmocion).setVisible(false);
        navMenu.findItem(R.id.nav_reporteRutina).setVisible(false);
        navMenu.findItem(R.id.nav_sugerirConsejo).setVisible(false);
        navMenu.findItem(R.id.nav_ingresarEncuesta).setVisible(false);
        navMenu.findItem(R.id.nav_rutinaDiaria).setVisible(false);
        navMenu.findItem(R.id.nav_multimedia).setVisible(false);
        navMenu.findItem(R.id.nav_multimedia_text).setVisible(false);
        navMenu.findItem(R.id.nav_multimedia_video).setVisible(false);
        navMenu.findItem(R.id.nav_sugerirContenido_profesional).setVisible(false);
        navMenu.findItem(R.id.nav_sugerirConsejo_profesional).setVisible(false);
        navMenu.findItem(R.id.nav_altaProfesional).setVisible(false);
        navMenu.findItem(R.id.nav_aprobarContenido_director).setVisible(false);
        navMenu.findItem(R.id.nav_pregunta_seguridad).setVisible(false);
    }

    private void showItemsConsultante() {
        Menu navMenu = navigationView.getMenu();
        navMenu.findItem(R.id.nav_cargarEmocionDiaria).setVisible(true);
        navMenu.findItem(R.id.nav_configurarHorario).setVisible(true);
        navMenu.findItem(R.id.nav_sugerirContenido).setVisible(true);
        navMenu.findItem(R.id.nav_reporteEmocion).setVisible(true);
        navMenu.findItem(R.id.nav_reporteRutina).setVisible(true);
        navMenu.findItem(R.id.nav_sugerirConsejo).setVisible(true);
        navMenu.findItem(R.id.nav_ingresarEncuesta).setVisible(true);
        navMenu.findItem(R.id.nav_rutinaDiaria).setVisible(true);
        navMenu.findItem(R.id.nav_multimedia).setVisible(true);
        navMenu.findItem(R.id.nav_multimedia_text).setVisible(true);
        navMenu.findItem(R.id.nav_multimedia_video).setVisible(true);
        navMenu.findItem(R.id.nav_pregunta_seguridad).setVisible(true);
    }

    private void showItemsProfesional() {
        Menu navMenu = navigationView.getMenu();
        navMenu.findItem(R.id.nav_sugerirContenido_profesional).setVisible(true);
        navMenu.findItem(R.id.nav_sugerirConsejo_profesional).setVisible(true);
        navMenu.findItem(R.id.nav_pregunta_seguridad).setVisible(true);
    }

    private void showItemsDirector() {
        Menu navMenu = navigationView.getMenu();
        navMenu.findItem(R.id.nav_altaProfesional).setVisible(true);
        navMenu.findItem(R.id.nav_aprobarContenido_director).setVisible(true);
        navMenu.findItem(R.id.nav_pregunta_seguridad).setVisible(true);
    }
}