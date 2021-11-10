package com.example.superandome_appfinal.IServices;

import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.Usuario;

import java.util.List;

public interface ConsejoService {
    Boolean guardar(Consejo consejo);

    //  List<Consejo> getConsejosPendientes();
    List<Consejo> getConsejosByEstado(int idEstado);
    Consejo getConsejoById(int idConsejo);
}
