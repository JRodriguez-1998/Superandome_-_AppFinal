package com.example.superandome_appfinal.Entidades;

import java.util.Date;

public class ItemUsuarioDiario {

    private Integer idUsuario;
    private Usuario usuario;
    private Integer idItem;
    private Item item;
    private Date fecha;

    public ItemUsuarioDiario() {}

    public ItemUsuarioDiario(Integer idUsuario, Usuario usuario, Integer idItem, Item item, Date fecha) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.idItem = idItem;
        this.item = item;
        this.fecha = fecha;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "ItemUsuarioDiario{" +
                "idUsuario=" + idUsuario +
                ", usuario=" + usuario +
                ", idItem=" + idItem +
                ", item=" + item +
                ", fecha=" + fecha +
                '}';
    }
}
