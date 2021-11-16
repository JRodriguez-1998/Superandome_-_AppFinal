package com.example.superandome_appfinal.Vistas.Consultante;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.superandome_appfinal.Entidades.EncuestaUsuario;
import com.example.superandome_appfinal.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class AdapterResultados extends RecyclerView.Adapter<AdapterResultados.ViewHolder> implements View.OnClickListener {
    private View.OnClickListener listener;
    public List<EncuestaUsuario> resultadosList;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvEncuestaUsuario;
        private final TextView tvEncuestaUsuarioFecha;
        private final TextView tvEncuestaUsuarioResultado;

        public ViewHolder(View itemView) {
            super(itemView);
            tvEncuestaUsuario = itemView.findViewById(R.id.tvEncuestaUsuario);
            tvEncuestaUsuarioFecha = itemView.findViewById(R.id.tvEncuestaUsuarioFecha);
            tvEncuestaUsuarioResultado = itemView.findViewById(R.id.tvEncuestaUsuarioResultado);
        }
    }

    public AdapterResultados(List<EncuestaUsuario> resultadosList) {
        this.resultadosList = resultadosList;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return resultadosList.size();
    }

    @Override
    public AdapterResultados.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_resultado_encuesta, parent,false);
        AdapterResultados.ViewHolder vH = new AdapterResultados.ViewHolder(view);

        view.setOnClickListener(this);

        return vH;
    }

    @Override
    public void onBindViewHolder(AdapterResultados.ViewHolder holder, int position) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        EncuestaUsuario encuestaUsuario = resultadosList.get(position);

        String nombre = encuestaUsuario.getEncuesta().getNombre();
        String fecha = formatter.format(encuestaUsuario.getFecha());
        String resultado;

        if (encuestaUsuario.getResultado() <= 7) {
            resultado = "Minimo nivel de ansiedad";
        } else if (encuestaUsuario.getResultado() <= 15) {
            resultado = "Leve nivel de ansiedad";
        } else if (encuestaUsuario.getResultado() <= 25) {
            resultado = "Ansiedad moderada";
        } else {
            resultado = "Ansiedad grave";
        }

        holder.tvEncuestaUsuario.setText(nombre);
        holder.tvEncuestaUsuarioFecha.setText(fecha);
        holder.tvEncuestaUsuarioResultado.setText(resultado);
    }

    @Override
    public void onClick(View view) {
        if (listener != null)
            listener.onClick(view);
    }
}
