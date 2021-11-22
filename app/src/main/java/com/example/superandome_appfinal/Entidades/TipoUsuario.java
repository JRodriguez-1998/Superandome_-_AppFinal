package com.example.superandome_appfinal.Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTipoUsuario;

    @Column(nullable = false)
    private String descripcion;

    public TipoUsuario() {
    }

    public TipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "TipoUsuario{" +
                "idTipoUsuario=" + idTipoUsuario +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
