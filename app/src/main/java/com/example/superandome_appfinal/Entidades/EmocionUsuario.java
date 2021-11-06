package com.example.superandome_appfinal.Entidades;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable
public class EmocionUsuario {
    @DatabaseField(generatedId = true)
    private Integer idEmocionUsuario;

    @DatabaseField(foreign = true, columnName = "idUsuario", uniqueCombo = true, canBeNull = false)
    private Usuario usuario;

    @DatabaseField(dataType =  DataType.DATE_STRING, format = "yyyy-MM-dd", uniqueCombo = true, canBeNull = false)
    private Date fecha;

    @DatabaseField(foreign = true, columnName = "idEmocion", canBeNull = false)
    private Emocion emocion;

    public EmocionUsuario() {
    }

    public EmocionUsuario(Usuario usuario, Date fecha, Emocion emocion) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.emocion = emocion;
    }

    public Integer getIdEmocionUsuario() {
        return idEmocionUsuario;
    }

    public void setIdEmocionUsuario(Integer idEmocionUsuario) {
        this.idEmocionUsuario = idEmocionUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Emocion getEmocion() {
        return emocion;
    }

    public void setEmocion(Emocion emocion) {
        this.emocion = emocion;
    }

    @Override
    public String toString() {
        return "EmocionUsuario{" +
                "idEmocionUsuario=" + idEmocionUsuario +
                ", usuario=" + usuario +
                ", fecha=" + fecha +
                ", emocion=" + emocion +
                '}';
    }
}
