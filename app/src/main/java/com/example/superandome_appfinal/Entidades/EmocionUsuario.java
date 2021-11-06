package com.example.superandome_appfinal.Entidades;

import java.util.Date;

public class EmocionUsuario {
    private Usuario user;
    private Date fecha;
    private Emocion emocion;

    //Propiedades por si hay ORM
    private Integer idUsuario;
    private Integer idEmocion;

    public EmocionUsuario() {
    }

    public EmocionUsuario(Usuario user, Date fecha, Emocion emocion) {
        this.user = user;
        this.fecha = fecha;
        this.emocion = emocion;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
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

}
