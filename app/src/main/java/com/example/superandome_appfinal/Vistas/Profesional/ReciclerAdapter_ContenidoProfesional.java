package com.example.superandome_appfinal.Vistas.Profesional;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.Contenido;
import com.example.superandome_appfinal.R;

import java.util.List;

public class ReciclerAdapter_ContenidoProfesional extends RecyclerView.Adapter<ReciclerAdapter_ContenidoProfesional.ViewHolder> implements View.OnClickListener{
    private View.OnClickListener listener;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView autor, tipoArchivo, archivo;
        private Button derivar, rechazar;

        public ViewHolder(View itemView){
            super(itemView);
            autor=(TextView) itemView.findViewById(R.id.tvAutor);
            tipoArchivo=(TextView) itemView.findViewById(R.id.tvTipoArchivo);
            archivo=(TextView) itemView.findViewById(R.id.tvArchivo);
        }
    }

    //Aqui hay que cambiar el tipo de datos a mostrar
    public List<Contenido> contenidoLista;

    public ReciclerAdapter_ContenidoProfesional(List<Contenido> contenidoLista){
        this.contenidoLista = contenidoLista;
    }

    @Override
    public ReciclerAdapter_ContenidoProfesional.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_derivar_contenido,parent,false);
        ReciclerAdapter_ContenidoProfesional.ViewHolder vH = new ReciclerAdapter_ContenidoProfesional.ViewHolder(view);

        view.setOnClickListener(this);

        return vH;
    }

    @Override
    public void onBindViewHolder(ReciclerAdapter_ContenidoProfesional.ViewHolder holder, int position) {
        holder.autor.setText(contenidoLista.get(position).getUsuario().getNickname());
        holder.tipoArchivo.setText(contenidoLista.get(position).getTipoArchivo().getDescripcion());
        holder.archivo.setText(contenidoLista.get(position).getNombreArchivo());
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
