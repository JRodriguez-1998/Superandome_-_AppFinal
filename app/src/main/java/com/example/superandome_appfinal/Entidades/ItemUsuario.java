package com.example.superandome_appfinal.Entidades;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable
public class ItemUsuario {

    @DatabaseField(generatedId = true)
    private Integer idItemUsuario;

    @DatabaseField(foreign = true, columnName = "idUsuario", uniqueCombo = true, canBeNull = false)
    private Usuario usuario;

    @DatabaseField(foreign = true, columnName = "idItem", uniqueCombo = true, canBeNull = false)
    private Item item;

    public ItemUsuario() {
    }

    public ItemUsuario(Usuario usuario, Item item) {
        this.usuario = usuario;
        this.item = item;
    }

    public Integer getIdItemUsuario() {
        return idItemUsuario;
    }

    public void setIdItemUsuario(Integer idItemUsuario) {
        this.idItemUsuario = idItemUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "ItemUsuarioDiario{" +
                "idItemUsuarioDiario=" + idItemUsuario +
                ", usuario=" + usuario +
                ", item=" + item +
                '}';
    }
}
