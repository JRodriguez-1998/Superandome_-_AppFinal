package com.example.superandome_appfinal.Vistas.Consultante;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.superandome_appfinal.Entidades.Encuesta;
import com.example.superandome_appfinal.R;

import java.util.List;

public class AdapterEncuesta extends RecyclerView.Adapter<AdapterEncuesta.ViewHolder> implements View.OnClickListener {
    private View.OnClickListener listener;
    public List<Encuesta> encuestaList;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvNombreEncuesta;

        public ViewHolder(View itemView){
            super(itemView);
            tvNombreEncuesta = itemView.findViewById(R.id.tvNombreEncuesta);
        }
    }

    public AdapterEncuesta(List<Encuesta> encuestaList) {
        this.encuestaList = encuestaList;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return encuestaList.size();
    }

    @Override
    public AdapterEncuesta.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_encuesta,parent,false);
        AdapterEncuesta.ViewHolder vH = new AdapterEncuesta.ViewHolder(view);

        view.setOnClickListener(this);

        return vH;
    }

    @Override
    public void onBindViewHolder(AdapterEncuesta.ViewHolder holder, int position) {
        holder.tvNombreEncuesta.setText(encuestaList.get(position).getNombre());
    }

    @Override
    public void onClick(View view) {
        if (listener != null)
            listener.onClick(view);
    }
}
