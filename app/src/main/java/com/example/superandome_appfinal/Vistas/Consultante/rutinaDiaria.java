package com.example.superandome_appfinal.Vistas.Consultante;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.superandome_appfinal.Dialogos.dialogoRutinaConfigurada;
import com.example.superandome_appfinal.Dialogos.dialogoSugerirConsejo;
import com.example.superandome_appfinal.Entidades.Item;
import com.example.superandome_appfinal.Entidades.ItemUsuario;
import com.example.superandome_appfinal.Entidades.ItemUsuarioDiario;
import com.example.superandome_appfinal.Entidades.Usuario;
import com.example.superandome_appfinal.IServices.ItemUsuarioService;
import com.example.superandome_appfinal.IServices.UsuarioService;
import com.example.superandome_appfinal.R;
import com.example.superandome_appfinal.Services.ItemServiceImpl;
import com.example.superandome_appfinal.Services.ItemUsuarioServiceImpl;
import com.example.superandome_appfinal.Services.UsuarioServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class rutinaDiaria extends Fragment {

    CheckBox chk1,chk2,chk3,chk4,chk5,chk6,chk7,chk8,chk9,chk10;
    TextView txt1, txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10,prueba;
    Button btnSaveRutina;
    ItemServiceImpl itemService;
//    ItemUsuarioDiario itemUsuarioDiario;
   // ItemUsuario itemUsu;
    ItemUsuarioService itemUsuarioService;
    UsuarioService ususerv;
    Usuario usuario;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        chk1 = (CheckBox) view.findViewById(R.id.checkBox);
        chk2 = (CheckBox) view.findViewById(R.id.checkBox2);
        chk3 = (CheckBox) view.findViewById(R.id.checkBox3);
        chk4 = (CheckBox) view.findViewById(R.id.checkBox4);
        chk5 = (CheckBox) view.findViewById(R.id.checkBox5);
        chk6 = (CheckBox) view.findViewById(R.id.checkBox6);
        chk7 = (CheckBox) view.findViewById(R.id.checkBox7);
        chk8 = (CheckBox) view.findViewById(R.id.checkBox8);
        chk9 = (CheckBox) view.findViewById(R.id.checkBox9);
        chk10 = (CheckBox) view.findViewById(R.id.checkBox10);

        txt1 = (TextView) view.findViewById(R.id.textView5);
        txt2 = (TextView) view.findViewById(R.id.textView7);
        txt3 = (TextView) view.findViewById(R.id.textView13);
        txt4 = (TextView) view.findViewById(R.id.textView14);
        txt5 = (TextView) view.findViewById(R.id.textView15);
        txt6 = (TextView) view.findViewById(R.id.textView16);
        txt7 = (TextView) view.findViewById(R.id.textView17);
        txt8 = (TextView) view.findViewById(R.id.textView18);
        txt9 = (TextView) view.findViewById(R.id.textView19);
        txt10 = (TextView) view.findViewById(R.id.textView20);

        btnSaveRutina = (Button) view.findViewById(R.id.btnSaveRutina);





        prueba = (TextView) view.findViewById(R.id.textView3);

        //int idUsuario = (int) getActivity().getIntent().getSerializableExtra("idUser");

       // String id = String.valueOf(idUsuario);

       // prueba.setText(id);


        List<Item> listaItems = new ArrayList<Item>();


        try {
            itemService = new ItemServiceImpl();
            itemUsuarioService = new ItemUsuarioServiceImpl();
            ususerv = new UsuarioServiceImpl();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Toast.makeText(getContext(), "Error al inicializar servicios", Toast.LENGTH_SHORT).show();
        }
        
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

                

                itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                if (itemsCant !=0){
                    chk1.setChecked(true);
                    }

            }
            if(p.getIdItem()==2){
                txt2.setText(p.getDescripcion());
                int itemsCant = 100;
                

                
                ItemUsuario itemUsu = new ItemUsuario();
                itemUsu.setUsuario(usuario);
                itemUsu.setItem(p);

                

                itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                if (itemsCant !=0){
                    chk2.setChecked(true);
                }
            }
            if(p.getIdItem()==3){
                txt3.setText(p.getDescripcion());
                int itemsCant = 100;
                

                
                ItemUsuario itemUsu = new ItemUsuario();
                itemUsu.setUsuario(usuario);
                itemUsu.setItem(p);

                

                itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                if (itemsCant !=0){
                    chk3.setChecked(true);
                }
            }
            if(p.getIdItem()==4){
                txt4.setText(p.getDescripcion());
                int itemsCant = 100;
                

                
                ItemUsuario itemUsu = new ItemUsuario();
                itemUsu.setUsuario(usuario);
                itemUsu.setItem(p);

                

                itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                if (itemsCant !=0){
                    chk4.setChecked(true);
                }
            }
            if(p.getIdItem()==5){
                txt5.setText(p.getDescripcion());
                int itemsCant = 100;
                

                
                ItemUsuario itemUsu = new ItemUsuario();
                itemUsu.setUsuario(usuario);
                itemUsu.setItem(p);

                

                itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                if (itemsCant !=0){
                    chk5.setChecked(true);
                }
            }
            if(p.getIdItem()==6){
                txt6.setText(p.getDescripcion());
                int itemsCant = 100;
                

                
                ItemUsuario itemUsu = new ItemUsuario();
                itemUsu.setUsuario(usuario);
                itemUsu.setItem(p);

                

                itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                if (itemsCant !=0){
                    chk6.setChecked(true);
                }
            }
            if(p.getIdItem()==7){
                txt7.setText(p.getDescripcion());
                int itemsCant = 100;
                

                
                ItemUsuario itemUsu = new ItemUsuario();
                itemUsu.setUsuario(usuario);
                itemUsu.setItem(p);

                

                itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                if (itemsCant !=0){
                    chk7.setChecked(true);
                }
            }
            if(p.getIdItem()==8){
                txt8.setText(p.getDescripcion());
                int itemsCant = 100;
                

                
                ItemUsuario itemUsu = new ItemUsuario();
                itemUsu.setUsuario(usuario);
                itemUsu.setItem(p);

                

                itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                if (itemsCant !=0){
                    chk8.setChecked(true);
                }
            }
            if(p.getIdItem()==9){
                txt9.setText(p.getDescripcion());
                int itemsCant = 100;
                

                
                ItemUsuario itemUsu = new ItemUsuario();
                itemUsu.setUsuario(usuario);
                itemUsu.setItem(p);

                

                itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                if (itemsCant !=0){
                    chk9.setChecked(true);
                }
            }
            if(p.getIdItem()==10){
                txt10.setText(p.getDescripcion());
                int itemsCant = 100;
                

                
                ItemUsuario itemUsu = new ItemUsuario();
                itemUsu.setUsuario(usuario);
                itemUsu.setItem(p);

                

                itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                if (itemsCant !=0){
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



                if(!chk1.isChecked() && !chk2.isChecked() && !chk3.isChecked() && !chk4.isChecked() && !chk5.isChecked() && !chk6.isChecked() && !chk7.isChecked() && !chk8.isChecked() && !chk9.isChecked() && !chk10.isChecked()){

                    Toast.makeText(getActivity(),"Completar",Toast.LENGTH_SHORT).show();

                }
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if(chk1.isChecked()){
                    int itemsCant = 100;

                    

                    
                    ItemUsuario itemUsu = new ItemUsuario();
                    itemUsu.setUsuario(usuario);
                    itemUsu.setItem(lista.get(0));

                    

                    itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                    if (itemsCant ==0){
                        if (itemUsuarioService.guardarItemUsuario(itemUsu)){
                            cuenta++;
                        }
                    }

                }else{
                    //Si no esta marcado

                    int itemsCant = 0;

                    

                    
                    ItemUsuario itemUsu = new ItemUsuario();
                    itemUsu.setUsuario(usuario);
                    itemUsu.setItem(lista.get(0));

                    

                    itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                    if (itemsCant !=0){
                        ItemUsuario itemdelete = new ItemUsuario();
                        itemdelete = itemUsuarioService.getItemUsuarioObj(itemUsu.getItem().getIdItem().toString(), itemUsu.getUsuario().getIdUsuario().toString());

                        if (itemUsuarioService.deleteItemUsuario(itemdelete)){
                            cuenta++;
                        }
                    }}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if(chk2.isChecked()){
                    int itemsCant = 100;

                    

                    
                    ItemUsuario itemUsu = new ItemUsuario();
                    itemUsu.setUsuario(usuario);
                    itemUsu.setItem(lista.get(1));

                    

                    itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                    if (itemsCant ==0){
                        if (itemUsuarioService.guardarItemUsuario(itemUsu)){
                            cuenta++;
                        }
                    }

                }else{
                    //Si no esta marcado

                    int itemsCant = 0;

                    

                    
                    ItemUsuario itemUsu = new ItemUsuario();
                    itemUsu.setUsuario(usuario);
                    itemUsu.setItem(lista.get(1));

                    

                    itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                    if (itemsCant !=0){
                        ItemUsuario itemdelete = new ItemUsuario();
                        itemdelete = itemUsuarioService.getItemUsuarioObj(itemUsu.getItem().getIdItem().toString(), itemUsu.getUsuario().getIdUsuario().toString());

                        if (itemUsuarioService.deleteItemUsuario(itemdelete)){
                            cuenta++;
                        }
                    }}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if(chk3.isChecked()){
                    int itemsCant = 100;

                    

                    
                    ItemUsuario itemUsu = new ItemUsuario();
                    itemUsu.setUsuario(usuario);
                    itemUsu.setItem(lista.get(2));

                    

                    itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                    if (itemsCant ==0){
                        if (itemUsuarioService.guardarItemUsuario(itemUsu)){
                            cuenta++;
                        }
                    }

                }else{
                    //Si no esta marcado

                    int itemsCant = 0;

                    

                    
                    ItemUsuario itemUsu = new ItemUsuario();
                    itemUsu.setUsuario(usuario);
                    itemUsu.setItem(lista.get(2));

                    

                    itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                    if (itemsCant !=0){
                        ItemUsuario itemdelete = new ItemUsuario();
                        itemdelete = itemUsuarioService.getItemUsuarioObj(itemUsu.getItem().getIdItem().toString(), itemUsu.getUsuario().getIdUsuario().toString());

                        if (itemUsuarioService.deleteItemUsuario(itemdelete)){
                            cuenta++;
                        }
                    }}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if(chk4.isChecked()){
                    int itemsCant = 100;

                    

                    
                    ItemUsuario itemUsu = new ItemUsuario();
                    itemUsu.setUsuario(usuario);
                    itemUsu.setItem(lista.get(3));

                    

                    itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                    if (itemsCant ==0){
                        if (itemUsuarioService.guardarItemUsuario(itemUsu)){
                            cuenta++;
                        }
                    }

                }else{
                    //Si no esta marcado

                    int itemsCant = 0;

                    

                    
                    ItemUsuario itemUsu = new ItemUsuario();
                    itemUsu.setUsuario(usuario);
                    itemUsu.setItem(lista.get(3));

                    

                    itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                    if (itemsCant !=0){
                        ItemUsuario itemdelete = new ItemUsuario();
                        itemdelete = itemUsuarioService.getItemUsuarioObj(itemUsu.getItem().getIdItem().toString(), itemUsu.getUsuario().getIdUsuario().toString());

                        if (itemUsuarioService.deleteItemUsuario(itemdelete)){
                            cuenta++;
                        }
                    }}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if(chk5.isChecked()){
                    int itemsCant = 100;

                    

                    
                    ItemUsuario itemUsu = new ItemUsuario();
                    itemUsu.setUsuario(usuario);
                    itemUsu.setItem(lista.get(4));

                    

                    itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                    if (itemsCant ==0){
                        if (itemUsuarioService.guardarItemUsuario(itemUsu)){
                            cuenta++;
                        }
                    }

                }else{
                    //Si no esta marcado

                    int itemsCant = 0;

                    

                    
                    ItemUsuario itemUsu = new ItemUsuario();
                    itemUsu.setUsuario(usuario);
                    itemUsu.setItem(lista.get(4));

                    

                    itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                    if (itemsCant !=0){
                        ItemUsuario itemdelete = new ItemUsuario();
                        itemdelete = itemUsuarioService.getItemUsuarioObj(itemUsu.getItem().getIdItem().toString(), itemUsu.getUsuario().getIdUsuario().toString());

                        if (itemUsuarioService.deleteItemUsuario(itemdelete)){
                            cuenta++;
                        }
                    }}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if(chk6.isChecked()){
                    int itemsCant = 100;

                    

                    
                    ItemUsuario itemUsu = new ItemUsuario();
                    itemUsu.setUsuario(usuario);
                    itemUsu.setItem(lista.get(5));

                    

                    itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                    if (itemsCant ==0){
                        if (itemUsuarioService.guardarItemUsuario(itemUsu)){
                            cuenta++;
                        }
                    }

                }else{
                    //Si no esta marcado

                    int itemsCant = 0;

                    

                    
                    ItemUsuario itemUsu = new ItemUsuario();
                    itemUsu.setUsuario(usuario);
                    itemUsu.setItem(lista.get(5));

                    

                    itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                    if (itemsCant !=0){
                        ItemUsuario itemdelete = new ItemUsuario();
                        itemdelete = itemUsuarioService.getItemUsuarioObj(itemUsu.getItem().getIdItem().toString(), itemUsu.getUsuario().getIdUsuario().toString());

                        if (itemUsuarioService.deleteItemUsuario(itemdelete)){
                            cuenta++;
                        }
                    }}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if(chk7.isChecked()){
                    int itemsCant = 100;

                    

                    
                    ItemUsuario itemUsu = new ItemUsuario();
                    itemUsu.setUsuario(usuario);
                    itemUsu.setItem(lista.get(6));

                    

                    itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                    if (itemsCant ==0){
                        if (itemUsuarioService.guardarItemUsuario(itemUsu)){
                            cuenta++;
                        }
                    }

                }else{
                    //Si no esta marcado

                    int itemsCant = 0;

                    

                    
                    ItemUsuario itemUsu = new ItemUsuario();
                    itemUsu.setUsuario(usuario);
                    itemUsu.setItem(lista.get(6));

                    

                    itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                    if (itemsCant !=0){
                        ItemUsuario itemdelete = new ItemUsuario();
                        itemdelete = itemUsuarioService.getItemUsuarioObj(itemUsu.getItem().getIdItem().toString(), itemUsu.getUsuario().getIdUsuario().toString());

                        if (itemUsuarioService.deleteItemUsuario(itemdelete)){
                            cuenta++;
                        }
                    }}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if(chk8.isChecked()){
                    int itemsCant = 100;

                    

                    
                    ItemUsuario itemUsu = new ItemUsuario();
                    itemUsu.setUsuario(usuario);
                    itemUsu.setItem(lista.get(7));

                    

                    itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                    if (itemsCant ==0){
                        if (itemUsuarioService.guardarItemUsuario(itemUsu)){
                            cuenta++;
                        }
                    }

                }else{
                    //Si no esta marcado

                    int itemsCant = 0;

                    

                    
                    ItemUsuario itemUsu = new ItemUsuario();
                    itemUsu.setUsuario(usuario);
                    itemUsu.setItem(lista.get(7));

                    

                    itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                    if (itemsCant !=0){
                        ItemUsuario itemdelete = new ItemUsuario();
                        itemdelete = itemUsuarioService.getItemUsuarioObj(itemUsu.getItem().getIdItem().toString(), itemUsu.getUsuario().getIdUsuario().toString());

                        if (itemUsuarioService.deleteItemUsuario(itemdelete)){
                            cuenta++;
                        }
                    }}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if(chk9.isChecked()){
                    int itemsCant = 100;

                    

                    
                    ItemUsuario itemUsu = new ItemUsuario();
                    itemUsu.setUsuario(usuario);
                    itemUsu.setItem(lista.get(8));

                    

                    itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                    if (itemsCant ==0){
                        if (itemUsuarioService.guardarItemUsuario(itemUsu)){
                            cuenta++;
                        }
                    }

                }else{
                    //Si no esta marcado

                    int itemsCant = 0;

                    

                    
                    ItemUsuario itemUsu = new ItemUsuario();
                    itemUsu.setUsuario(usuario);
                    itemUsu.setItem(lista.get(8));

                    

                    itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                    if (itemsCant !=0){
                        ItemUsuario itemdelete = new ItemUsuario();
                        itemdelete = itemUsuarioService.getItemUsuarioObj(itemUsu.getItem().getIdItem().toString(), itemUsu.getUsuario().getIdUsuario().toString());

                        if (itemUsuarioService.deleteItemUsuario(itemdelete)){
                            cuenta++;
                        }
                    }}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if(chk10.isChecked()){
                    int itemsCant = 100;

                    

                    
                    ItemUsuario itemUsu = new ItemUsuario();
                    itemUsu.setUsuario(usuario);
                    itemUsu.setItem(lista.get(9));

                    

                    itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                    if (itemsCant ==0){
                        if (itemUsuarioService.guardarItemUsuario(itemUsu)){
                            cuenta++;
                        }
                    }

                }else{
                    //Si no esta marcado

                    int itemsCant = 0;

                    

                    
                    ItemUsuario itemUsu = new ItemUsuario();
                    itemUsu.setUsuario(usuario);
                    itemUsu.setItem(lista.get(9));

                    

                    itemsCant =itemUsuarioService.getItemUsuario(itemUsu.getItem().getIdItem().toString() , itemUsu.getUsuario().getIdUsuario().toString());

                    if (itemsCant !=0){
                        ItemUsuario itemdelete = new ItemUsuario();
                        itemdelete = itemUsuarioService.getItemUsuarioObj(itemUsu.getItem().getIdItem().toString(), itemUsu.getUsuario().getIdUsuario().toString());

                        if (itemUsuarioService.deleteItemUsuario(itemdelete)){
                            cuenta++;
                        }
                    }}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


                if (cuenta>0){
                    dialogoRutinaConfigurada d = new dialogoRutinaConfigurada();
                    d.show(getActivity().getSupportFragmentManager(), "fragment_dialogo_rutina");
                }
                else{
                    Toast.makeText(getActivity(),"Error al actualizar",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rutina_diaria, container, false);
    }
}