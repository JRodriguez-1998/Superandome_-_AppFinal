package com.example.superandome_appfinal.Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoArchivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoArchivo;

    @Column(nullable = false)
    private String descripcion;

    public TipoArchivo() {
    }

    public TipoArchivo(String descripcion) {
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
