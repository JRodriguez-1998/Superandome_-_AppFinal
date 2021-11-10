package com.example.superandome_appfinal.Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PreguntaSeguridad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPreguntaSeguridad;

    @Column(nullable = false)
    private String descripcion;

    public PreguntaSeguridad() { }

    public PreguntaSeguridad(int idPreguntaSeguridad, String descripcion) {
        this.idPreguntaSeguridad = idPreguntaSeguridad;
        this.descripcion = descripcion;
    }

    public PreguntaSeguridad(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdPreguntaSeguridad() {
        return idPreguntaSeguridad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setIdPreguntaSeguridad(int idPreguntaSeguridad) {
        this.idPreguntaSeguridad = idPreguntaSeguridad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
