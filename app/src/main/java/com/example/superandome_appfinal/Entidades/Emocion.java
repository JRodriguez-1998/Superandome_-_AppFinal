package com.example.superandome_appfinal.Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Emocion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmocion;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
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
