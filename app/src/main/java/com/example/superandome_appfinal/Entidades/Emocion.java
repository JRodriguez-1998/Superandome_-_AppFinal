package com.example.superandome_appfinal.Entidades;

public class Emocion {
    private Integer idEmocion;
    private String descripcion;
    private Boolean habilitado;

    public Emocion() {
    }

    public Emocion(Integer idEmocion, String descripcion, Boolean habilitado) {
        this.idEmocion = idEmocion;
        this.descripcion = descripcion;
        this.habilitado = habilitado;
    }

    public Integer getIdEmocion() {
        return idEmocion;
    }

    public void setIdEmocion(Integer idEmocion) {
        this.idEmocion = idEmocion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    @Override
    public String toString() {
        return "Emoción{" +
                "idEmocion=" + idEmocion +
                ", descripcion='" + descripcion + '\'' +
                ", habilitado=" + habilitado +
                '}';
    }
}
