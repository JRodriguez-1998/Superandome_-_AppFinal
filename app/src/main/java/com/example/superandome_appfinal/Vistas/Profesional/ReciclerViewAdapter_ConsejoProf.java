package com.example.superandome_appfinal.Vistas.Profesional;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.R;

import java.util.ArrayList;
import java.util.List;

public class ReciclerViewAdapter_ConsejoProf extends RecyclerView.Adapter<ReciclerViewAdapter_ConsejoProf.ViewHolder> implements View.OnClickListener {


    private View.OnClickListener listener;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView autor, tipoConsejo, consejo;
        private Button derivar, rechazar;

        public ViewHolder(View itemView){
            super(itemView);
            autor=(TextView) itemView.findViewById(R.id.tvAutor);
            tipoConsejo=(TextView) itemView.findViewById(R.id.tvTipoConsejo);
            consejo=(TextView) itemView.findViewById(R.id.tvConsejo);
        }
    }

    //Aqui hay que cambiar el tipo de datos a mostrar
    public List<Consejo> contenidoLista;

    public ReciclerViewAdapter_ConsejoProf(List<Consejo> contenidoLista){
        this.contenidoLista = contenidoLista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_consejo,parent,false);
        ViewHolder vH = new ViewHolder(view);

        view.setOnClickListener(this);

        return vH;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.autor.setText(contenidoLista.get(position).getUsuarioAutor().getNickname());
        holder.tipoConsejo.setText(contenidoLista.get(position).getTipoConsejo().getDescripcion());
        holder.consejo.setText(contenidoLista.get(position).getTexto());
    }

    @Override
    public int getItemCount() {
        return contenidoLista.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }
}