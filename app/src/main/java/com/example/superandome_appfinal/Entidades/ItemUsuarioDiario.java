package com.example.superandome_appfinal.Entidades;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable
public class ItemUsuarioDiario {

    @DatabaseField(generatedId = true)
    private Integer idItemUsuarioDiario;

    @DatabaseField(foreign = true, columnName = "idUsuario", uniqueCombo = true, canBeNull = false)
    private Usuario usuario;

    @DatabaseField(foreign = true, columnName = "idItem", uniqueCombo = true, canBeNull = false)
    private Item item;

    @DatabaseField(dataType =  DataType.DATE_STRING, format = "yyyy-MM-dd", uniqueCombo = true, canBeNull = false)
    private Date fecha;

    public ItemUsuarioDiario() {
    }

    public ItemUsuarioDiario(Usuario usuario, Item item, Date fecha) {
        this.usuario = usuario;
        this.item = item;
        this.fecha = fecha;
    }

    public Integer getIdItemUsuarioDiario() {
        return idItemUsuarioDiario;
    }

    public void setIdItemUsuarioDiario(Integer idItemUsuarioDiario) {
        this.idItemUsuarioDiario = idItemUsuarioDiario;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "ItemUsuarioDiario{" +
                "idItemUsuarioDiario=" + idItemUsuarioDiario +
                ", usuario=" + usuario +
                ", item=" + item +
                ", fecha=" + fecha +
                '}';
    }
}
