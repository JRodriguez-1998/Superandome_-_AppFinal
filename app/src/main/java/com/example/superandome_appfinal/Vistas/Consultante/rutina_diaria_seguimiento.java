package com.example.superandome_appfinal.Vistas.Consultante;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.superandome_appfinal.Dialogos.dialogoRutinaConfigurada;
import com.example.superandome_appfinal.Entidades.Item;
import com.example.superandome_appfinal.Entidades.ItemUsuario;
import com.example.superandome_appfinal.Entidades.ItemUsuarioDiario;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.IServices.ItemUsuarioDiarioService;
import com.example.superandome_appfinal.IServices.ItemUsuarioService;
import com.example.superandome_appfinal.IServices.UsuarioService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.ItemServiceImpl;
import com.example.superandome_appfinal.Services.ItemUsuarioDiarioServiceImpl;
import com.example.superandome_appfinal.Services.ItemUsuarioServiceImpl;
import com.example.superandome_appfinal.Services.UsuarioServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class rutina_diaria_seguimiento extends Fragment {


    CheckBox chk1,chk2,chk3,chk4,chk5,chk6,chk7,chk8,chk9,chk10;
    TextView txt1, txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10,prueba;
    TableRow tb1,tb2,tb3,tb4,tb5,tb6,tb7,tb8,tb9,tb10;
    Button btnSaveRutina;
    ItemServiceImpl itemService;
    ItemUsuarioDiario itemUsuarioDiario;
    // ItemUsuario itemUsu;
    ItemUsuarioService itemUsuarioService;
    ItemUsuarioDiarioService itemUsuarioDiarioService;
    UsuarioService ususerv;
    Usuario usuario;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        chk1 = (CheckBox) view.findViewById(R.id.chk1);
        chk2 = (CheckBox) view.findViewById(R.id.chk2);
        chk3 = (CheckBox) view.findViewById(R.id.chk3);
        chk4 = (CheckBox) view.findViewById(R.id.chk4);
        chk5 = (CheckBox) view.findViewById(R.id.chk5);
        chk6 = (CheckBox) view.findViewById(R.id.chk6);
        chk7 = (CheckBox) view.findViewById(R.id.chk7);
        chk8 = (CheckBox) view.findViewById(R.id.chk8);
        chk9 = (CheckBox) view.findViewById(R.id.chk9);
        chk10 = (CheckBox) view.findViewById(R.id.chk10);

        txt1 = (TextView) view.findViewById(R.id.txtRutina1);
        txt2 = (TextView) view.findViewById(R.id.txtRutina2);
        txt3 = (TextView) view.findViewById(R.id.txtRutina3);
        txt4 = (TextView) view.findViewById(R.id.txtRutina4);
        txt5 = (TextView) view.findViewById(R.id.txtRutina5);
        txt6 = (TextView) view.findViewById(R.id.txtRutina6);
        txt7 = (TextView) view.findViewById(R.id.txtRutina7);
        txt8 = (TextView) view.findViewById(R.id.txtRutina8);
        txt9 = (TextView) view.findViewById(R.id.txtRutina9);
        txt10 = (TextView) view.findViewById(R.id.txtRutina10);

        tb1 = (TableRow) view.findViewById(R.id.tb1);
        tb2 = (TableRow) view.findViewById(R.id.tb2);
        tb3 = (TableRow) view.findViewById(R.id.tb3);
        tb4 = (TableRow) view.findViewById(R.id.tb4);
        tb5 = (TableRow) view.findViewById(R.id.tb5);
        tb6 = (TableRow) view.findViewById(R.id.tb6);
        tb7 = (TableRow) view.findViewById(R.id.tb7);
        tb8 = (TableRow) view.findViewById(R.id.tb8);
        tb9 = (TableRow) view.findViewById(R.id.tb9);
        tb10 = (TableRow) view.findViewById(R.id.tb10);

        btnSaveRutina = (Button) view.findViewById(R.id.btnSaveRutinaSeguimiento);

        List<Item> listaItems = new ArrayList<Item>();


        try {
            itemService = new ItemServiceImpl();
            itemUsuarioService= new ItemUsuarioServiceImpl();
            itemUsuarioDiarioService= new ItemUsuarioDiarioServiceImpl();
            ususerv = new UsuarioServiceImpl();
        
        SharedPreferences preferences = requireActivity().getSharedPreferences("sesiones", Context.MODE_PRIVATE);
        int idUsuario = preferences.getInt("idUser", 0);

        usuario = ususerv.getUsuarioById(idUsuario);



        listaItems = itemService.getItemsRutina();
        for(Item p: listaItems){

            if(p.getIdItem()==1){
                txt1.setText(p.getDescripcion());
                int itemsCant = 100;

                ItemUsuario itemUsu = new ItemUsuario();
                itemUsu.setUsuario(usuario);
                itemUsu.setItem(p);



                Date fecha = new Date();


                ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                itemPorCargar.setUsuario(usuario);
                itemPorCargar.setItem(listaItems.get(0));
                itemPorCargar.setFecha(fecha);

                
                int itemsCantidadDiario = 0;
                itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());
                itemsCantidadDiario = itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                if (itemsCant ==0){
                    tb1.setVisibility(view.GONE);
                }
                if(itemsCantidadDiario == 1){
                    ItemUsuarioDiario  comparar = new ItemUsuarioDiario();
                    comparar = itemUsuarioDiarioService.getItemUsuarioFechaObj(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                    Calendar fecha1 = Calendar.getInstance();
                    Calendar fecha2 = Calendar.getInstance();
                    fecha1.setTime(itemPorCargar.getFecha());
                    fecha2.setTime(comparar.getFecha());

                    if(fecha1.get(Calendar.YEAR) == fecha2.get(Calendar.YEAR) && fecha1.get(Calendar.DAY_OF_YEAR) == fecha2.get(Calendar.DAY_OF_YEAR) && fecha1.get(Calendar.MONTH) == fecha2.get(Calendar.MONTH) ){
                        chk1.setChecked(true);
                    }
                }


            }
            if(p.getIdItem()==2){
                txt2.setText(p.getDescripcion());
                int itemsCant = 100;
                

                ItemUsuario itemUsu = new ItemUsuario();
                itemUsu.setUsuario(usuario);
                itemUsu.setItem(p);



                Date fecha = new Date();


                ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                itemPorCargar.setUsuario(usuario);
                itemPorCargar.setItem(listaItems.get(1));
                itemPorCargar.setFecha(fecha);

                int itemsCantidadDiario = 0;
                itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());
                itemsCantidadDiario = itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                if (itemsCant ==0){
                    tb2.setVisibility(view.GONE);
                }
                if(itemsCantidadDiario == 1){
                    ItemUsuarioDiario  comparar = new ItemUsuarioDiario();
                    comparar = itemUsuarioDiarioService.getItemUsuarioFechaObj(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                    Calendar fecha1 = Calendar.getInstance();
                    Calendar fecha2 = Calendar.getInstance();
                    fecha1.setTime(itemPorCargar.getFecha());
                    fecha2.setTime(comparar.getFecha());

                    if(fecha1.get(Calendar.YEAR) == fecha2.get(Calendar.YEAR) && fecha1.get(Calendar.DAY_OF_YEAR) == fecha2.get(Calendar.DAY_OF_YEAR) && fecha1.get(Calendar.MONTH) == fecha2.get(Calendar.MONTH) ){
                        chk2.setChecked(true);
                    }
                }

            }
            if(p.getIdItem()==3){
                txt3.setText(p.getDescripcion());
                int itemsCant = 100;

                ItemUsuario itemUsu = new ItemUsuario();
                itemUsu.setUsuario(usuario);
                itemUsu.setItem(p);



                Date fecha = new Date();


                ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                itemPorCargar.setUsuario(usuario);
                itemPorCargar.setItem(listaItems.get(2));
                itemPorCargar.setFecha(fecha);

                

                
                int itemsCantidadDiario = 0;
                itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());
                itemsCantidadDiario = itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                if (itemsCant ==0){
                    tb3.setVisibility(view.GONE);
                }
                if(itemsCantidadDiario == 1){
                    ItemUsuarioDiario  comparar = new ItemUsuarioDiario();
                    comparar = itemUsuarioDiarioService.getItemUsuarioFechaObj(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                    Calendar fecha1 = Calendar.getInstance();
                    Calendar fecha2 = Calendar.getInstance();
                    fecha1.setTime(itemPorCargar.getFecha());
                    fecha2.setTime(comparar.getFecha());

                    if(fecha1.get(Calendar.YEAR) == fecha2.get(Calendar.YEAR) && fecha1.get(Calendar.DAY_OF_YEAR) == fecha2.get(Calendar.DAY_OF_YEAR) && fecha1.get(Calendar.MONTH) == fecha2.get(Calendar.MONTH) ){
                        chk3.setChecked(true);
                    }
                }

            }
            if(p.getIdItem()==4){
                txt4.setText(p.getDescripcion());
                int itemsCant = 100;
                

                ItemUsuario itemUsu = new ItemUsuario();
                itemUsu.setUsuario(usuario);
                itemUsu.setItem(p);



                Date fecha = new Date();


                ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                itemPorCargar.setUsuario(usuario);
                itemPorCargar.setItem(listaItems.get(3));
                itemPorCargar.setFecha(fecha);

                
                int itemsCantidadDiario = 0;
                itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());
                itemsCantidadDiario = itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                if (itemsCant ==0){
                    tb4.setVisibility(view.GONE);
                }
                if(itemsCantidadDiario == 1){
                    ItemUsuarioDiario  comparar = new ItemUsuarioDiario();
                    comparar = itemUsuarioDiarioService.getItemUsuarioFechaObj(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                    Calendar fecha1 = Calendar.getInstance();
                    Calendar fecha2 = Calendar.getInstance();
                    fecha1.setTime(itemPorCargar.getFecha());
                    fecha2.setTime(comparar.getFecha());

                    if(fecha1.get(Calendar.YEAR) == fecha2.get(Calendar.YEAR) && fecha1.get(Calendar.DAY_OF_YEAR) == fecha2.get(Calendar.DAY_OF_YEAR) && fecha1.get(Calendar.MONTH) == fecha2.get(Calendar.MONTH) ){
                        chk4.setChecked(true);
                    }
                }

            }
            if(p.getIdItem()==5){
                txt5.setText(p.getDescripcion());
                int itemsCant = 100;
                

                ItemUsuario itemUsu = new ItemUsuario();
                itemUsu.setUsuario(usuario);
                itemUsu.setItem(p);



                Date fecha = new Date();


                ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                itemPorCargar.setUsuario(usuario);
                itemPorCargar.setItem(listaItems.get(4));
                itemPorCargar.setFecha(fecha);

                
                int itemsCantidadDiario = 0;
                itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());
                itemsCantidadDiario = itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                if (itemsCant ==0){
                    tb5.setVisibility(view.GONE);
                }
                if(itemsCantidadDiario == 1){
                    ItemUsuarioDiario  comparar = new ItemUsuarioDiario();
                    comparar = itemUsuarioDiarioService.getItemUsuarioFechaObj(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                    Calendar fecha1 = Calendar.getInstance();
                    Calendar fecha2 = Calendar.getInstance();
                    fecha1.setTime(itemPorCargar.getFecha());
                    fecha2.setTime(comparar.getFecha());

                    if(fecha1.get(Calendar.YEAR) == fecha2.get(Calendar.YEAR) && fecha1.get(Calendar.DAY_OF_YEAR) == fecha2.get(Calendar.DAY_OF_YEAR) && fecha1.get(Calendar.MONTH) == fecha2.get(Calendar.MONTH) ){
                        chk5.setChecked(true);
                    }
                }

            }
            if(p.getIdItem()==6){
                txt6.setText(p.getDescripcion());
                int itemsCant = 100;
                

                ItemUsuario itemUsu = new ItemUsuario();
                itemUsu.setUsuario(usuario);
                itemUsu.setItem(p);



                Date fecha = new Date();


                ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                itemPorCargar.setUsuario(usuario);
                itemPorCargar.setItem(listaItems.get(5));
                itemPorCargar.setFecha(fecha);

                
                int itemsCantidadDiario = 0;
                itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());
                itemsCantidadDiario = itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                if (itemsCant ==0){
                    tb6.setVisibility(view.GONE);
                }
                if(itemsCantidadDiario == 1){
                    ItemUsuarioDiario  comparar = new ItemUsuarioDiario();
                    comparar = itemUsuarioDiarioService.getItemUsuarioFechaObj(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                    Calendar fecha1 = Calendar.getInstance();
                    Calendar fecha2 = Calendar.getInstance();
                    fecha1.setTime(itemPorCargar.getFecha());
                    fecha2.setTime(comparar.getFecha());

                    if(fecha1.get(Calendar.YEAR) == fecha2.get(Calendar.YEAR) && fecha1.get(Calendar.DAY_OF_YEAR) == fecha2.get(Calendar.DAY_OF_YEAR) && fecha1.get(Calendar.MONTH) == fecha2.get(Calendar.MONTH) ){
                        chk6.setChecked(true);
                    }
                }


            }
            if(p.getIdItem()==7){
                txt7.setText(p.getDescripcion());
                int itemsCant = 100;
                

                ItemUsuario itemUsu = new ItemUsuario();
                itemUsu.setUsuario(usuario);
                itemUsu.setItem(p);



                Date fecha = new Date();


                ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                itemPorCargar.setUsuario(usuario);
                itemPorCargar.setItem(listaItems.get(6));
                itemPorCargar.setFecha(fecha);

                
                int itemsCantidadDiario = 0;
                itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());
                itemsCantidadDiario = itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                if (itemsCant ==0){
                    tb7.setVisibility(view.GONE);
                }
                if(itemsCantidadDiario == 1){
                    ItemUsuarioDiario  comparar = new ItemUsuarioDiario();
                    comparar = itemUsuarioDiarioService.getItemUsuarioFechaObj(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                    Calendar fecha1 = Calendar.getInstance();
                    Calendar fecha2 = Calendar.getInstance();
                    fecha1.setTime(itemPorCargar.getFecha());
                    fecha2.setTime(comparar.getFecha());

                    if(fecha1.get(Calendar.YEAR) == fecha2.get(Calendar.YEAR) && fecha1.get(Calendar.DAY_OF_YEAR) == fecha2.get(Calendar.DAY_OF_YEAR) && fecha1.get(Calendar.MONTH) == fecha2.get(Calendar.MONTH) ){
                        chk7.setChecked(true);
                    }
                }


            }
            if(p.getIdItem()==8){
                txt8.setText(p.getDescripcion());
                int itemsCant = 100;
                

                ItemUsuario itemUsu = new ItemUsuario();
                itemUsu.setUsuario(usuario);
                itemUsu.setItem(p);



                Date fecha = new Date();


                ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                itemPorCargar.setUsuario(usuario);
                itemPorCargar.setItem(listaItems.get(7));
                itemPorCargar.setFecha(fecha);

                
                int itemsCantidadDiario = 0;
                itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());
                itemsCantidadDiario = itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                if (itemsCant ==0){
                    tb8.setVisibility(view.GONE);
                }
                if(itemsCantidadDiario == 1){
                    ItemUsuarioDiario  comparar = new ItemUsuarioDiario();
                    comparar = itemUsuarioDiarioService.getItemUsuarioFechaObj(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                    Calendar fecha1 = Calendar.getInstance();
                    Calendar fecha2 = Calendar.getInstance();
                    fecha1.setTime(itemPorCargar.getFecha());
                    fecha2.setTime(comparar.getFecha());

                    if(fecha1.get(Calendar.YEAR) == fecha2.get(Calendar.YEAR) && fecha1.get(Calendar.DAY_OF_YEAR) == fecha2.get(Calendar.DAY_OF_YEAR) && fecha1.get(Calendar.MONTH) == fecha2.get(Calendar.MONTH) ){
                        chk7.setChecked(true);
                    }
                }


            }
            if(p.getIdItem()==9){
                txt9.setText(p.getDescripcion());
                int itemsCant = 100;
                

                ItemUsuario itemUsu = new ItemUsuario();
                itemUsu.setUsuario(usuario);
                itemUsu.setItem(p);



                Date fecha = new Date();


                ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                itemPorCargar.setUsuario(usuario);
                itemPorCargar.setItem(listaItems.get(8));
                itemPorCargar.setFecha(fecha);

                
                int itemsCantidadDiario = 0;
                itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());
                itemsCantidadDiario = itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                if (itemsCant ==0){
                    tb9.setVisibility(view.GONE);
                }
                if(itemsCantidadDiario == 1){
                    ItemUsuarioDiario  comparar = new ItemUsuarioDiario();
                    comparar = itemUsuarioDiarioService.getItemUsuarioFechaObj(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                    Calendar fecha1 = Calendar.getInstance();
                    Calendar fecha2 = Calendar.getInstance();
                    fecha1.setTime(itemPorCargar.getFecha());
                    fecha2.setTime(comparar.getFecha());

                    if(fecha1.get(Calendar.YEAR) == fecha2.get(Calendar.YEAR) && fecha1.get(Calendar.DAY_OF_YEAR) == fecha2.get(Calendar.DAY_OF_YEAR) && fecha1.get(Calendar.MONTH) == fecha2.get(Calendar.MONTH) ){
                        chk9.setChecked(true);
                    }
                }

            }
            if(p.getIdItem()==10){
                txt10.setText(p.getDescripcion());
                int itemsCant = 100;
                

                ItemUsuario itemUsu = new ItemUsuario();
                itemUsu.setUsuario(usuario);
                itemUsu.setItem(p);



                Date fecha = new Date();


                ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                itemPorCargar.setUsuario(usuario);
                itemPorCargar.setItem(listaItems.get(9));
                itemPorCargar.setFecha(fecha);

                
                int itemsCantidadDiario = 0;
                itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());
                itemsCantidadDiario = itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                if (itemsCant ==0){
                    tb10.setVisibility(view.GONE);
                }
                if(itemsCantidadDiario == 1){
                    ItemUsuarioDiario  comparar = new ItemUsuarioDiario();
                    comparar = itemUsuarioDiarioService.getItemUsuarioFechaObj(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                    Calendar fecha1 = Calendar.getInstance();
                    Calendar fecha2 = Calendar.getInstance();
                    fecha1.setTime(itemPorCargar.getFecha());
                    fecha2.setTime(comparar.getFecha());

                    if(fecha1.get(Calendar.YEAR) == fecha2.get(Calendar.YEAR) && fecha1.get(Calendar.DAY_OF_YEAR) == fecha2.get(Calendar.DAY_OF_YEAR) && fecha1.get(Calendar.MONTH) == fecha2.get(Calendar.MONTH) ){
                        chk10.setChecked(true);
                    }
                }



            }

        btnSaveRutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Item> lista = new ArrayList<Item>();
                lista = itemService.getItemsRutina();
                int cuenta=0;



//                if(!chk1.isChecked() && !chk2.isChecked() && !chk3.isChecked() && !chk4.isChecked() && !chk5.isChecked() && !chk6.isChecked() && !chk7.isChecked() && !chk8.isChecked() && !chk9.isChecked() && !chk10.isChecked()){
//
//                    Toast.makeText(getActivity(),"Completar",Toast.LENGTH_SHORT).show();
//
//                }
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if(chk1.isChecked()){
                    int itemsCant = 100;


                    //ItemUsuario itemUsu = new ItemUsuario();
                    //itemUsu.setUsuario(usuario);
                    //itemUsu.setItem(lista.get(0));
                    Date fecha = new Date();


                    ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                    itemPorCargar.setUsuario(usuario);
                    itemPorCargar.setItem(lista.get(0));
                    itemPorCargar.setFecha(fecha);


                    itemsCant =itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                    if (itemsCant ==0){
                         if (itemUsuarioDiarioService.guardarItemUsuarioDiario(itemPorCargar)){
                            cuenta++;

                        }

                    }

                }

                else{
                    //Si no esta marcado

                    int itemsCant = 100;

                    
                    //ItemUsuario itemUsu = new ItemUsuario();
                    //itemUsu.setUsuario(usuario);
                    //itemUsu.setItem(lista.get(0));
                    Date fecha = new Date();


                    ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                    itemPorCargar.setUsuario(usuario);
                    itemPorCargar.setItem(lista.get(0));
                    itemPorCargar.setFecha(fecha);


                    itemsCant =itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());


                    if (itemsCant !=0){
                        ItemUsuarioDiario itemdelete = new ItemUsuarioDiario();
                        itemdelete = itemUsuarioDiarioService.getItemUsuarioFechaObj(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                        if (itemUsuarioDiarioService.deleteItemUsuarioDiario(itemdelete)){
                            cuenta++;
                        }
                    }}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if(chk2.isChecked()){
                    int itemsCant = 100;

                    

                    
                    //ItemUsuario itemUsu = new ItemUsuario();
                    //itemUsu.setUsuario(usuario);
                    //itemUsu.setItem(lista.get(0));
                    Date fecha = new Date();


                    ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                    itemPorCargar.setUsuario(usuario);
                    itemPorCargar.setItem(lista.get(1));
                    itemPorCargar.setFecha(fecha);


                    


                    itemsCant =itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                    if (itemsCant ==0){

                        ItemUsuarioDiario itemdelete = new ItemUsuarioDiario();
                        itemdelete = itemUsuarioDiarioService.getItemUsuarioFechaObj(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());
                        if (itemUsuarioDiarioService.guardarItemUsuarioDiario(itemPorCargar)){
                            cuenta++;

                        }

                    }

                }
                else{
                    //Si no esta marcado

                    int itemsCant = 100;

                    

                    
                    //ItemUsuario itemUsu = new ItemUsuario();
                    //itemUsu.setUsuario(usuario);
                    //itemUsu.setItem(lista.get(0));
                    Date fecha = new Date();


                    ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                    itemPorCargar.setUsuario(usuario);
                    itemPorCargar.setItem(lista.get(1));
                    itemPorCargar.setFecha(fecha);


                    


                    itemsCant =itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());


                    if (itemsCant !=0){
                        ItemUsuarioDiario itemdelete = new ItemUsuarioDiario();
                        itemdelete = itemUsuarioDiarioService.getItemUsuarioFechaObj(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                        if (itemUsuarioDiarioService.deleteItemUsuarioDiario(itemdelete)){
                            cuenta++;
                        }
                    }}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if(chk3.isChecked()){
                    int itemsCant = 100;

                    

                    
                    //ItemUsuario itemUsu = new ItemUsuario();
                    //itemUsu.setUsuario(usuario);
                    //itemUsu.setItem(lista.get(0));
                    Date fecha = new Date();


                    ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                    itemPorCargar.setUsuario(usuario);
                    itemPorCargar.setItem(lista.get(2));
                    itemPorCargar.setFecha(fecha);


                    


                    itemsCant =itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                    if (itemsCant ==0){


                        if (itemUsuarioDiarioService.guardarItemUsuarioDiario(itemPorCargar)){
                            cuenta++;

                        }

                    }

                }
                else{
                    //Si no esta marcado

                    int itemsCant = 100;

                    

                    
                    //ItemUsuario itemUsu = new ItemUsuario();
                    //itemUsu.setUsuario(usuario);
                    //itemUsu.setItem(lista.get(0));
                    Date fecha = new Date();


                    ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                    itemPorCargar.setUsuario(usuario);
                    itemPorCargar.setItem(lista.get(2));
                    itemPorCargar.setFecha(fecha);


                    


                    itemsCant =itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());


                    if (itemsCant !=0){
                        ItemUsuarioDiario itemdelete = new ItemUsuarioDiario();
                        itemdelete = itemUsuarioDiarioService.getItemUsuarioFechaObj(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                        if (itemUsuarioDiarioService.deleteItemUsuarioDiario(itemdelete)){
                            cuenta++;
                        }
                    }}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if(chk4.isChecked()){
                    int itemsCant = 100;

                    

                    
                    //ItemUsuario itemUsu = new ItemUsuario();
                    //itemUsu.setUsuario(usuario);
                    //itemUsu.setItem(lista.get(0));
                    Date fecha = new Date();


                    ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                    itemPorCargar.setUsuario(usuario);
                    itemPorCargar.setItem(lista.get(3));
                    itemPorCargar.setFecha(fecha);


                    


                    itemsCant =itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                    if (itemsCant ==0){
                        if (itemUsuarioDiarioService.guardarItemUsuarioDiario(itemPorCargar)){
                            cuenta++;

                        }

                    }

                }
                else{
                    //Si no esta marcado

                    int itemsCant = 100;

                    

                    
                    //ItemUsuario itemUsu = new ItemUsuario();
                    //itemUsu.setUsuario(usuario);
                    //itemUsu.setItem(lista.get(0));
                    Date fecha = new Date();


                    ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                    itemPorCargar.setUsuario(usuario);
                    itemPorCargar.setItem(lista.get(3));
                    itemPorCargar.setFecha(fecha);


                    


                    itemsCant =itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());


                    if (itemsCant !=0){
                        ItemUsuarioDiario itemdelete = new ItemUsuarioDiario();
                        itemdelete = itemUsuarioDiarioService.getItemUsuarioFechaObj(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                        if (itemUsuarioDiarioService.deleteItemUsuarioDiario(itemdelete)){
                            cuenta++;
                        }
                    }}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if(chk5.isChecked()){
                    int itemsCant = 100;

                    

                    
                    //ItemUsuario itemUsu = new ItemUsuario();
                    //itemUsu.setUsuario(usuario);
                    //itemUsu.setItem(lista.get(0));
                    Date fecha = new Date();


                    ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                    itemPorCargar.setUsuario(usuario);
                    itemPorCargar.setItem(lista.get(4));
                    itemPorCargar.setFecha(fecha);


                    


                    itemsCant =itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                    if (itemsCant ==0){
                        if (itemUsuarioDiarioService.guardarItemUsuarioDiario(itemPorCargar)){
                            cuenta++;

                        }

                    }

                }
                else{
                    //Si no esta marcado

                    int itemsCant = 100;

                    

                    
                    //ItemUsuario itemUsu = new ItemUsuario();
                    //itemUsu.setUsuario(usuario);
                    //itemUsu.setItem(lista.get(0));
                    Date fecha = new Date();


                    ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                    itemPorCargar.setUsuario(usuario);
                    itemPorCargar.setItem(lista.get(4));
                    itemPorCargar.setFecha(fecha);


                    


                    itemsCant =itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());


                    if (itemsCant !=0){
                        ItemUsuarioDiario itemdelete = new ItemUsuarioDiario();
                        itemdelete = itemUsuarioDiarioService.getItemUsuarioFechaObj(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                        if (itemUsuarioDiarioService.deleteItemUsuarioDiario(itemdelete)){
                            cuenta++;
                        }
                    }}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if(chk6.isChecked()){
                    int itemsCant = 100;

                    

                    
                    //ItemUsuario itemUsu = new ItemUsuario();
                    //itemUsu.setUsuario(usuario);
                    //itemUsu.setItem(lista.get(0));
                    Date fecha = new Date();


                    ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                    itemPorCargar.setUsuario(usuario);
                    itemPorCargar.setItem(lista.get(5));
                    itemPorCargar.setFecha(fecha);


                    


                    itemsCant =itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                    if (itemsCant ==0){
                        if (itemUsuarioDiarioService.guardarItemUsuarioDiario(itemPorCargar)){
                            cuenta++;

                        }

                    }

                }
                else{
                    //Si no esta marcado

                    int itemsCant = 100;

                    

                    
                    //ItemUsuario itemUsu = new ItemUsuario();
                    //itemUsu.setUsuario(usuario);
                    //itemUsu.setItem(lista.get(0));
                    Date fecha = new Date();


                    ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                    itemPorCargar.setUsuario(usuario);
                    itemPorCargar.setItem(lista.get(5));
                    itemPorCargar.setFecha(fecha);


                    


                    itemsCant =itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());


                    if (itemsCant !=0){
                        ItemUsuarioDiario itemdelete = new ItemUsuarioDiario();
                        itemdelete = itemUsuarioDiarioService.getItemUsuarioFechaObj(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                        if (itemUsuarioDiarioService.deleteItemUsuarioDiario(itemdelete)){
                            cuenta++;
                        }
                    }}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if(chk7.isChecked()){
                    int itemsCant = 100;

                    

                    
                    //ItemUsuario itemUsu = new ItemUsuario();
                    //itemUsu.setUsuario(usuario);
                    //itemUsu.setItem(lista.get(0));
                    Date fecha = new Date();


                    ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                    itemPorCargar.setUsuario(usuario);
                    itemPorCargar.setItem(lista.get(6));
                    itemPorCargar.setFecha(fecha);


                    


                    itemsCant =itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                    if (itemsCant ==0){
                        if (itemUsuarioDiarioService.guardarItemUsuarioDiario(itemPorCargar)){
                            cuenta++;

                        }

                    }

                }
                else{
                    //Si no esta marcado

                    int itemsCant = 100;

                    

                    
                    //ItemUsuario itemUsu = new ItemUsuario();
                    //itemUsu.setUsuario(usuario);
                    //itemUsu.setItem(lista.get(0));
                    Date fecha = new Date();


                    ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                    itemPorCargar.setUsuario(usuario);
                    itemPorCargar.setItem(lista.get(6));
                    itemPorCargar.setFecha(fecha);


                    


                    itemsCant =itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());


                    if (itemsCant !=0){
                        ItemUsuarioDiario itemdelete = new ItemUsuarioDiario();
                        itemdelete = itemUsuarioDiarioService.getItemUsuarioFechaObj(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                        if (itemUsuarioDiarioService.deleteItemUsuarioDiario(itemdelete)){
                            cuenta++;
                        }
                    }}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if(chk8.isChecked()){
                    int itemsCant = 100;

                    

                    
                    //ItemUsuario itemUsu = new ItemUsuario();
                    //itemUsu.setUsuario(usuario);
                    //itemUsu.setItem(lista.get(0));
                    Date fecha = new Date();


                    ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                    itemPorCargar.setUsuario(usuario);
                    itemPorCargar.setItem(lista.get(7));
                    itemPorCargar.setFecha(fecha);


                    


                    itemsCant =itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                    if (itemsCant ==0){
                        if (itemUsuarioDiarioService.guardarItemUsuarioDiario(itemPorCargar)){
                            cuenta++;

                        }

                    }

                }
                else{
                    //Si no esta marcado

                    int itemsCant = 100;

                    

                    
                    //ItemUsuario itemUsu = new ItemUsuario();
                    //itemUsu.setUsuario(usuario);
                    //itemUsu.setItem(lista.get(0));
                    Date fecha = new Date();


                    ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                    itemPorCargar.setUsuario(usuario);
                    itemPorCargar.setItem(lista.get(7));
                    itemPorCargar.setFecha(fecha);


                    


                    itemsCant =itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());


                    if (itemsCant !=0){
                        ItemUsuarioDiario itemdelete = new ItemUsuarioDiario();
                        itemdelete = itemUsuarioDiarioService.getItemUsuarioFechaObj(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                        if (itemUsuarioDiarioService.deleteItemUsuarioDiario(itemdelete)){
                            cuenta++;
                        }
                    }}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if(chk9.isChecked()){
                    int itemsCant = 100;

                    

                    
                    //ItemUsuario itemUsu = new ItemUsuario();
                    //itemUsu.setUsuario(usuario);
                    //itemUsu.setItem(lista.get(0));
                    Date fecha = new Date();


                    ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                    itemPorCargar.setUsuario(usuario);
                    itemPorCargar.setItem(lista.get(8));
                    itemPorCargar.setFecha(fecha);


                    


                    itemsCant =itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                    if (itemsCant ==0){
                        if (itemUsuarioDiarioService.guardarItemUsuarioDiario(itemPorCargar)){
                            cuenta++;

                        }

                    }

                }
                else{
                    //Si no esta marcado

                    int itemsCant = 100;

                    

                    
                    //ItemUsuario itemUsu = new ItemUsuario();
                    //itemUsu.setUsuario(usuario);
                    //itemUsu.setItem(lista.get(0));
                    Date fecha = new Date();


                    ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                    itemPorCargar.setUsuario(usuario);
                    itemPorCargar.setItem(lista.get(8));
                    itemPorCargar.setFecha(fecha);


                    


                    itemsCant =itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());


                    if (itemsCant !=0){
                        ItemUsuarioDiario itemdelete = new ItemUsuarioDiario();
                        itemdelete = itemUsuarioDiarioService.getItemUsuarioFechaObj(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                        if (itemUsuarioDiarioService.deleteItemUsuarioDiario(itemdelete)){
                            cuenta++;
                        }
                    }}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if(chk10.isChecked()){
                    int itemsCant = 100;

                    

                    
                    //ItemUsuario itemUsu = new ItemUsuario();
                    //itemUsu.setUsuario(usuario);
                    //itemUsu.setItem(lista.get(0));
                    Date fecha = new Date();


                    ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                    itemPorCargar.setUsuario(usuario);
                    itemPorCargar.setItem(lista.get(9));
                    itemPorCargar.setFecha(fecha);


                    


                    itemsCant =itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                    if (itemsCant ==0){
                        if (itemUsuarioDiarioService.guardarItemUsuarioDiario(itemPorCargar)){
                            cuenta++;

                        }

                    }

                }
                else{
                    //Si no esta marcado

                    int itemsCant = 100;

                    

                    
                    //ItemUsuario itemUsu = new ItemUsuario();
                    //itemUsu.setUsuario(usuario);
                    //itemUsu.setItem(lista.get(0));
                    Date fecha = new Date();


                    ItemUsuarioDiario itemPorCargar = new ItemUsuarioDiario();
                    itemPorCargar.setUsuario(usuario);
                    itemPorCargar.setItem(lista.get(9));
                    itemPorCargar.setFecha(fecha);


                    


                    itemsCant =itemUsuarioDiarioService.getItemUsuarioFecha(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());


                    if (itemsCant !=0){
                        ItemUsuarioDiario itemdelete = new ItemUsuarioDiario();
                        itemdelete = itemUsuarioDiarioService.getItemUsuarioFechaObj(itemPorCargar.getItem().getIdItem().toString(),itemPorCargar.getUsuario().getIdUsuario().toString(),itemPorCargar.getFecha());

                        if (itemUsuarioDiarioService.deleteItemUsuarioDiario(itemdelete)){
                            cuenta++;
                        }
                    }}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////







/////////////////////////////////CARTEL////////////////////////////////

                if (cuenta>0){
                    dialogoRutinaConfigurada d = new dialogoRutinaConfigurada();
                    d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_rutina");
                }
                else{
                    Toast.makeText(getActivity(),"Error al actualizar",Toast.LENGTH_LONG).show();
                }

            }
        });

    } } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Ha ocurrido un error al inicializar la pantalla", Toast.LENGTH_SHORT).show();
        }}



        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rutina_diaria_seguimiento, container, false);
    }
}