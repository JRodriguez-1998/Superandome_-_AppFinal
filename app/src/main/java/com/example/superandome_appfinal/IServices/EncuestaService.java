package com.example.superandome_appfinal.IServices;

import com.example.superandome_appfinal.Entidades.Encuesta;

import java.util.List;

public interface EncuestaService {
    Encuesta getEncuestaById(int id);
    List<Encuesta> getEncuestas();
}
