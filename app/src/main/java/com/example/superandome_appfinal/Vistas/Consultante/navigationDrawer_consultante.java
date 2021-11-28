package com.example.superandome_appfinal.Vistas.Consultante;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superandome_appfinal.Constantes.TipoUsuarioEnum;
import com.example.superandome_appfinal.Dialogos.cerrarSesion;
import com.example.superandome_appfinal.Dialogos.dialogoCargarRutinas;
import com.example.superandome_appfinal.Dialogos.dialogoEmocionxdia;
import com.example.superandome_appfinal.Entidades.EncuestaUsuario;
import com.example.superandome_appfinal.Entidades.ItemUsuario;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.Helpers.SessionManager;
import com.example.superandome_appfinal.IServices.ItemUsuarioService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.EmocionUsuarioServiceImpl;
import com.example.superandome_appfinal.Services.EncuestaUsuarioServiceImpl;
import com.example.superandome_appfinal.Services.ItemUsuarioServiceImpl;
import com.example.superandome_appfinal.Vistas.about;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.superandome_appfinal.databinding.ActivityNavigationDrawerConsultanteBinding;

import java.util.Date;
import java.util.List;

public class navigationDrawer_consultante extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    NavigationView navigationView;

    TextView txtNombreUser;
    Usuario usuario;

    EmocionUsuarioServiceImpl emocionUserService;
    ItemUsuarioService itemUsuarioService;
    EncuestaUsuarioServiceImpl encuestaUsuarioService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            emocionUserService = new EmocionUsuarioServiceImpl();
            itemUsuarioService = new ItemUsuarioServiceImpl();
            encuestaUsuarioService = new EncuestaUsuarioServiceImpl();

            usuario = SessionManager.obtenerUsuario(this);

            ActivityNavigationDrawerConsultanteBinding binding = ActivityNavigationDrawerConsultanteBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            setSupportActionBar(binding.appBarNavigationDrawerConsultante.toolbar);

            DrawerLayout drawer = binding.drawerLayout;
            navigationView = binding.navView;
            mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_cargarEmocionDiaria, R.id.nav_configurarHorario, R.id.nav_sugerirContenido, R.id.nav_sugerirConsejo,
                    R.id.nav_rutinaDiaria, R.id.nav_sugerirContenido_profesional, R.id.nav_sugerirConsejo_profesional, R.id.nav_reporteEmocion,
                    R.id.nav_reporteRutina,R.id.nav_multimedia,R.id.nav_altaProfesional, R.id.nav_aprobarContenido_director,
                    R.id.nav_homeConsultante, R.id.nav_cerrarSesion, R.id.nav_cambiar_password, R.id.nav_cambiar_password_profesional,R.id.nav_rutinaDiariaSeguimiento,
                    R.id.nav_derivarConsejo_profesional, R.id.nav_aprobarConsejo_director, R.id.nav_homeProfesional, R.id.nav_homeDirector, R.id.nav_indexEncuestas, R.id.nav_derivarContenido_profesional,
                    R.id.nav_multimedia_director, R.id.nav_multimedia_profesional)
                    .setOpenableLayout(drawer)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation_drawer_consultante);
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);

            //Creo una variable View para obtener del navigationView el header, y asi setear el TextView del nombre
            View navHeader = navigationView.getHeaderView(0);
            txtNombreUser = (TextView) navHeader.findViewById(R.id.tvNombreUserLogin);
            txtNombreUser.setText(usuario.getNickname());

            LinearLayout sideNavLayout = (LinearLayout) navHeader.findViewById(R.id.linearLayoutHeader);

            binding.appBarNavigationDrawerConsultante.fab.setOnClickListener(view -> {
                switch (TipoUsuarioEnum.getTipoUsuario(usuario.getTipoUsuario().getIdTipoUsuario())) {
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
            });

            setClickListeners();
            hideAllItems();

            switch (TipoUsuarioEnum.getTipoUsuario(usuario.getTipoUsuario().getIdTipoUsuario())) {
                case CONSULTANTE:
                    binding.appBarNavigationDrawerConsultante.toolbar.setBackgroundColor(Color.rgb(111,194,180));

                    //COLOR FAB (ICONO CASITA)
                    ColorStateList csl_cons = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFF6FC2B4});
                    binding.appBarNavigationDrawerConsultante.fab.setBackgroundTintList(csl_cons);

                    sideNavLayout.setBackgroundResource(R.drawable.side_nav_bar_consultante);
                    showItemsConsultante();
                    navController.navigate(R.id.nav_homeConsultante);
                    break;
                case PROFESIONAL:
                    binding.appBarNavigationDrawerConsultante.toolbar.setBackgroundColor(Color.rgb(65,103,178));

                    //COLOR FAB (ICONO CASITA)
                    ColorStateList csl_prof = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFF4167B2});
                    binding.appBarNavigationDrawerConsultante.fab.setBackgroundTintList(csl_prof);

                    sideNavLayout.setBackgroundResource(R.drawable.side_nav_bar);
                    showItemsProfesional();
                    navController.navigate(R.id.nav_homeProfesional);
                    break;
                case DIRECTOR:
                    binding.appBarNavigationDrawerConsultante.toolbar.setBackgroundColor(Color.rgb(111,194,180));

                    //COLOR FAB (ICONO CASITA)
                    ColorStateList csl_dir = new ColorStateList(new int[][]{new int[0]}, new int[]{0xFF6FC2B4});
                    binding.appBarNavigationDrawerConsultante.fab.setBackgroundTintList(csl_dir);



                    sideNavLayout.setBackgroundResource(R.drawable.side_nav_bar_director);
                    showItemsDirector();
                    navController.navigate(R.id.nav_homeDirector);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Ha ocurrido un error al inicializar la pantalla", Toast.LENGTH_SHORT).show();
        }
    }

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
            case R.id.cerrarSesion:
                cerrarSesion cerrar = new cerrarSesion();
                cerrar.show(getSupportFragmentManager(), "fragment_dialogo_cerrar_sesion");
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

    private void setClickListeners() {
        Menu navMenu = navigationView.getMenu();

        navMenu.findItem(R.id.nav_rutinaDiariaSeguimiento).setOnMenuItemClickListener(menuItem -> {
            List<ItemUsuario> itemList = itemUsuarioService.getItemUsuariosByIdUsuario(usuario.getIdUsuario());
            if (itemList == null) {
                Toast.makeText(this, "Ha ocurrido un error al buscar su rutina", Toast.LENGTH_SHORT).show();
                return true;
            }
            if (itemList.size() == 0) {
                dialogoCargarRutinas d = new dialogoCargarRutinas();
                d.show(this.getSupportFragmentManager(), "fragment_dialogo_cargar_rutinas");
                return true;
            }

            return false;
        });

        /*navMenu.findItem(R.id.nav_cerrarSesion).setOnMenuItemClickListener(item -> {
            cerrarSesion cerrar = new cerrarSesion();
            cerrar.show(getSupportFragmentManager(), "fragment_dialogo_cerrar_sesion");
            return true;
        });*/


        navMenu.findItem(R.id.nav_multimedia).setOnMenuItemClickListener(item -> {

            if(usuario.getTipoUsuario().getIdTipoUsuario() != TipoUsuarioEnum.CONSULTANTE.getTipo()){
                return false;
            }

            if(encuestaUsuarioService.getEncuestaUsuarioByUsuario(usuario.getIdUsuario()).size() == 0){
                Toast.makeText(this, "Debe completar previamente la encuesta", Toast.LENGTH_SHORT).show();
                return true;
            }
                return false;
        });
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
        navMenu.findItem(R.id.nav_multimedia_profesional).setVisible(false);
        navMenu.findItem(R.id.nav_multimedia_director).setVisible(false);
        navMenu.findItem(R.id.nav_sugerirContenido_profesional).setVisible(false);
        navMenu.findItem(R.id.nav_sugerirConsejo_profesional).setVisible(false);
        navMenu.findItem(R.id.nav_altaProfesional).setVisible(false);
        navMenu.findItem(R.id.nav_aprobarContenido_director).setVisible(false);
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

        if (emocionUserService.getEmocionByFechaAndId(usuario.getIdUsuario(), new Date()) != null) {
            navMenu.findItem(R.id.nav_cargarEmocionDiaria).setEnabled(false);
        }

        navMenu.findItem(R.id.nav_homeConsultante).setVisible(true);
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
        navMenu.findItem(R.id.nav_rutinaDiariaSeguimiento).setVisible(true);
        //navMenu.findItem(R.id.nav_cerrarSesion).setVisible(true);
    }

    private void showItemsProfesional() {
        Menu navMenu = navigationView.getMenu();
        navMenu.findItem(R.id.nav_homeProfesional).setVisible(true);
        navMenu.findItem(R.id.nav_sugerirContenido_profesional).setVisible(true);
        navMenu.findItem(R.id.nav_sugerirConsejo_profesional).setVisible(true);
        navMenu.findItem(R.id.nav_derivarConsejo_profesional).setVisible(true);
        navMenu.findItem(R.id.nav_derivarContenido_profesional).setVisible(true);
        navMenu.findItem(R.id.nav_multimedia_profesional).setVisible(true);
        navMenu.findItem(R.id.nav_cambiar_password_profesional).setVisible(true);
        //navMenu.findItem(R.id.nav_cerrarSesion).setVisible(true);
    }

    private void showItemsDirector() {
        Menu navMenu = navigationView.getMenu();
        navMenu.findItem(R.id.nav_homeDirector).setVisible(true);
        navMenu.findItem(R.id.nav_altaProfesional).setVisible(true);
        navMenu.findItem(R.id.nav_aprobarContenido_director).setVisible(true);
        navMenu.findItem(R.id.nav_aprobarConsejo_director).setVisible(true);
        navMenu.findItem(R.id.nav_multimedia_director).setVisible(true);
        navMenu.findItem(R.id.nav_cambiar_password).setVisible(true);
        //navMenu.findItem(R.id.nav_cerrarSesion).setVisible(true);
    }
}