package com.example.superandome_appfinal.IServices;

import com.example.superandome_appfinal.Entidades.Contenido;

import java.util.List;

public interface ContenidoService {
    Boolean guardar(Contenido contenido);
    Contenido getContenidoByID(int id);
    List<Contenido> getContenidosAprobados();
}
