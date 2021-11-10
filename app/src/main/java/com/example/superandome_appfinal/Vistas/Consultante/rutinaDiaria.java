package com.example.superandome_appfinal.Vistas.Consultante;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.superandome_appfinal.Entidades.Item;
import com.example.superandome_appfinal.IServices.ItemService;
import com.example.superandome_appfinal.Services.ItemServiceImpl;
import com.example.superandome_appfinal.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class rutinaDiaria extends Fragment {

    CheckBox chk1,chk2,chk3,chk4,chk5,chk6,chk7,chk8,chk9,chk10;
    TextView txt1, txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10;
    Button btnSaveRutina;
    ItemServiceImpl itemService;

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

        btnSaveRutina = (Button) view.findViewById(R.id.button3);



        List<Item> listaItems = new ArrayList<Item>();


        try {
            itemService = new ItemServiceImpl();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        listaItems = itemService.getItemsRutina();
        for(Item p: listaItems){

            if(p.getIdItem()==1){
                txt1.setText(p.getDescripcion());
            }
            if(p.getIdItem()==2){
                txt2.setText(p.getDescripcion());
            }
            if(p.getIdItem()==3){
                txt3.setText(p.getDescripcion());
            }
            if(p.getIdItem()==4){
                txt4.setText(p.getDescripcion());
            }
            if(p.getIdItem()==5){
                txt5.setText(p.getDescripcion());
            }
            if(p.getIdItem()==6){
                txt6.setText(p.getDescripcion());
            }
            if(p.getIdItem()==7){
                txt7.setText(p.getDescripcion());
            }
            if(p.getIdItem()==8){
                txt8.setText(p.getDescripcion());
            }
            if(p.getIdItem()==9){
                txt9.setText(p.getDescripcion());
            }
            if(p.getIdItem()==10){
                txt10.setText(p.getDescripcion());
            }


        }
        chk1.setSelected(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rutina_diaria, container, false);
    }
}