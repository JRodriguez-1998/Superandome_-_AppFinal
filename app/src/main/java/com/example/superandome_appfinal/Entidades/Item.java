package com.example.superandome_appfinal.Entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItem;

    @Column(nullable = false)
    private String descripcion;

    public Item () {
    }

    public Item(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Item{" +
                "idItem=" + idItem +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
