package com.example.superandome_appfinal.Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Encuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEncuesta;

    @Column(nullable = false)
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
