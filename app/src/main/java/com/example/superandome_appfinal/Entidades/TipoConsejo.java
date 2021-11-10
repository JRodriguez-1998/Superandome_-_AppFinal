package com.example.superandome_appfinal.Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoConsejo {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer idTipoConsejo;
    @Column
    private String descripcion;

    public TipoConsejo() {}

    public TipoConsejo(String descripcion) {
        this.descripcion = descripcion;
    }

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
        return descripcion;
    }
}
