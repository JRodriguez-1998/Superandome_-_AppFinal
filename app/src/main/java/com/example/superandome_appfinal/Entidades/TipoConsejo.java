package com.example.superandome_appfinal.Entidades;

public class TipoConsejo {

    private Integer idTipoConsejo;
    private String descripcion;

    public TipoConsejo() {}

    public TipoConsejo(Integer idTipoConsejo, String descripcion) {
        this.idTipoConsejo = idTipoConsejo;
        this.descripcion = descripcion;
    }

    public Integer getIdTipoConsejo() {
        return idTipoConsejo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setIdTipoConsejo(Integer idTipoConsejo) {
        this.idTipoConsejo = idTipoConsejo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoConsejo{" +
                "idTipoConsejo=" + idTipoConsejo +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
