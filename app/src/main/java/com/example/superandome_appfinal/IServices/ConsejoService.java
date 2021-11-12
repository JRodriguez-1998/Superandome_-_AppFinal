package com.example.superandome_appfinal.IServices;

import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.Usuario;

import java.util.List;

public interface ConsejoService {
    Boolean guardar(Consejo consejo);
    Boolean actualizar(Consejo consejo);

    List<Consejo> getConsejosPendientes();
    List<Consejo> getConsejosPendientesDIRECTOR();
    List<Consejo> getConsejosByEstado(int idEstado);
    List<Consejo> getConsejosByEstadoAndTipo(int idEstado, int idTipo);
    Consejo getConsejoById(int idConsejo);
}
