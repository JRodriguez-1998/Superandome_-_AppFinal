package com.example.superandome_appfinal.Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGenero;

    @Column(nullable = false)
    private String descripcion;

    public Genero() {
    }

    public Genero(String descripcion) {
        this.descripcion = descripcion;
    }

    public Genero(int idGenero, String descripcion) {
        this.idGenero = idGenero;
        this.descripcion = descripcion;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
