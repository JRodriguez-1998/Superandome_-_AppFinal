package com.example.superandome_appfinal.Entidades;

public class TipoArchivo {
    private Integer idTipoArchivo;
    private String descripcion;

    public TipoArchivo() {
    }

    public TipoArchivo(Integer idTipoArchivo, String descripcion) {
        this.idTipoArchivo = idTipoArchivo;
        this.descripcion = descripcion;
    }

    public Integer getIdTipoArchivo() {
        return idTipoArchivo;
    }

    public void setIdTipoArchivo(Integer idTipoArchivo) {
        this.idTipoArchivo = idTipoArchivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoArchivo{" +
                "idTipoArchivo=" + idTipoArchivo +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
