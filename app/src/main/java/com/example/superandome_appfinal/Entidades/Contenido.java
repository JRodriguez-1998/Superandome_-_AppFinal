package com.example.superandome_appfinal.Entidades;

import java.util.Date;

public class Contenido {
    private Integer idContenido;
    private String nombreArchivo;
    private TipoArchivo tipoArchivo;
    private Date fechaAprobacion;
    private Date fechaCarga;
    private Estado estado;
    private Usuario user;

    //Propiedades por si hay ORM
    private Integer idTipoArchivo;
    private Integer idEstado;
    private Integer idUsuario;

    public Contenido(){}

    public Contenido(Integer idContenido, String nombreArchivo, TipoArchivo tipoArchivo, Date fechaAprobacion, Date fechaCarga, Estado estado, Usuario user) {
        this.idContenido = idContenido;
        this.nombreArchivo = nombreArchivo;
        this.tipoArchivo = tipoArchivo;
        this.fechaAprobacion = fechaAprobacion;
        this.fechaCarga = fechaCarga;
        this.estado = estado;
        this.user = user;
    }

    public Integer getIdContenido() {
        return idContenido;
    }

    public void setIdContenido(Integer idContenido) {
        this.idContenido = idContenido;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public TipoArchivo getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(TipoArchivo tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public Date getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Date fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}
