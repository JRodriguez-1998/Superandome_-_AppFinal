package com.example.superandome_appfinal.Entidades;

public class Encuesta {
    private Integer idEncuesta;
    private String nombre;

    public Encuesta() {}

    public Encuesta(Integer idEncuesta, String nombre) {
        this.idEncuesta = idEncuesta;
        this.nombre = nombre;
    }

    public Integer getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(Integer idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Encuesta{" +
                "idEncuesta=" + idEncuesta +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
