package com.example.superandome_appfinal.Entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Contenido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idContenido;

    @Column(nullable = false)
    private String nombreArchivo;

    @Column
    private Date fechaAprobacion;

    @Column(nullable = false)
    private Date fechaCarga;

    @Column(columnDefinition = "TEXT")
    private String archivo;

    @ManyToOne
    @JoinColumn(name = "idTipoArchivo")
    private TipoArchivo tipoArchivo;

    @ManyToOne
    @JoinColumn(name = "idEstado")
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public Contenido(){}

    public Contenido(Integer idContenido, String nombreArchivo, TipoArchivo tipoArchivo, Date fechaAprobacion, Date fechaCarga, Estado estado, Usuario usuario, String archivo) {
        this.idContenido = idContenido;
        this.nombreArchivo = nombreArchivo;
        this.tipoArchivo = tipoArchivo;
        this.fechaAprobacion = fechaAprobacion;
        this.fechaCarga = fechaCarga;
        this.estado = estado;
        this.usuario = usuario;
        this.archivo = archivo;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
}
