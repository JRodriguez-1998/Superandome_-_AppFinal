package com.example.superandome_appfinal.Vistas.Profesional;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.superandome_appfinal.Entidades.Contenido;
import com.example.superandome_appfinal.R;

import java.util.List;

public class adapterMultimedia extends RecyclerView.Adapter<com.example.superandome_appfinal.Vistas.Profesional.adapterMultimedia.ViewHolder> implements View.OnClickListener{
    private View.OnClickListener listener;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvAutor, nombreArchivo, tipoArchivo;

        public ViewHolder(View itemView){
            super(itemView);
            tvAutor=(TextView) itemView.findViewById(R.id.tvAutor);
            nombreArchivo=(TextView) itemView.findViewById(R.id.tvArchivo);
            tipoArchivo=(TextView) itemView.findViewById(R.id.tvTipoArchivo);
        }
    }

    //Aqui hay que cambiar el tipo de datos a mostrar
    public List<Contenido> contenidoLista;

    public adapterMultimedia(List<Contenido> contenidoLista){
        this.contenidoLista = contenidoLista;
    }

    @Override
    public com.example.superandome_appfinal.Vistas.Profesional.adapterMultimedia.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_contenido,parent,false);
        com.example.superandome_appfinal.Vistas.Profesional.adapterMultimedia.ViewHolder vH = new com.example.superandome_appfinal.Vistas.Profesional.adapterMultimedia.ViewHolder(view);

        view.setOnClickListener(this);

        return vH;
    }

    @Override
    public void onBindViewHolder(com.example.superandome_appfinal.Vistas.Profesional.adapterMultimedia.ViewHolder holder, int position) {
        holder.nombreArchivo.setText(contenidoLista.get(position).getNombreArchivo());
        holder.tipoArchivo.setText(contenidoLista.get(position).getTipoArchivo().getDescripcion());
        holder.tvAutor.setText(contenidoLista.get(position).getUsuario().getNickname());
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
