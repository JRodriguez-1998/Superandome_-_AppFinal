package com.example.superandome_appfinal.Entidades;

public class Estado {

    private Integer idEstado;
    private String descripcion;

    public Estado() { }

    public Estado(Integer idEstado, String descripcion) {
        this.idEstado = idEstado;
        this.descripcion = descripcion;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Estado{" +
                "idEstado=" + idEstado +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
