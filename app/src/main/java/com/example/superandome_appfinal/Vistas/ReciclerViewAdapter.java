package com.example.superandome_appfinal.Vistas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.superandome_appfinal.R;

import java.util.List;

public class ReciclerViewAdapter extends RecyclerView.Adapter<ReciclerViewAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView contenido, sugeridopor, tipocontenido;

        public ViewHolder(View itemView){
            super(itemView);
            contenido=(TextView) itemView.findViewById(R.id.tvContent);
            sugeridopor=(TextView) itemView.findViewById(R.id.tvSugeridopor);
            tipocontenido=(TextView) itemView.findViewById(R.id.tvTipoContent);
        }
    }

    //Aqui hay que cambiar el tipo de datos a mostrar
    public List<ClasePrueba> contenidoLista;

    public ReciclerViewAdapter(List<ClasePrueba> contenidoLista){
        this.contenidoLista = contenidoLista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_contenido,parent,false);
        ViewHolder vH = new ViewHolder(view);
        return vH;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.contenido.setText(contenidoLista.get(position).getContenido());
        holder.sugeridopor.setText(contenidoLista.get(position).getSugerido());
        holder.tipocontenido.setText(contenidoLista.get(position).getTipo());
    }

    @Override
    public int getItemCount() {
        return contenidoLista.size();
    }
}
