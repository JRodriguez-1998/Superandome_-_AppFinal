package com.example.superandome_appfinal.IServices;

import com.example.superandome_appfinal.Entidades.Contenido;

public interface ContenidoService {
    Boolean guardar(Contenido contenido);
    Contenido getContenidoByID(int id);
}
