package com.example.superandome_appfinal.Entidades;

import java.util.Date;

public class EncuestaUsuario {

    private Integer idEncuestaUsuario;
    private Integer idEncuesta;
    private Encuesta encuesta;
    private Integer idUsuario;
    private Usuario usuario;
    private Date fecha;
    private String resultado;

    public EncuestaUsuario() {}

    public EncuestaUsuario(Integer idEncuestaUsuario, Integer idEncuesta, Encuesta encuesta, Integer idUsuario, Usuario usuario, Date fecha, String resultado) {
        this.idEncuestaUsuario = idEncuestaUsuario;
        this.idEncuesta = idEncuesta;
        this.encuesta = encuesta;
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.fecha = fecha;
        this.resultado = resultado;
    }

    public Integer getIdEncuestaUsuario() {
        return idEncuestaUsuario;
    }

    public void setIdEncuestaUsuario(Integer idEncuestaUsuario) {
        this.idEncuestaUsuario = idEncuestaUsuario;
    }

    public Integer getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(Integer idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public Encuesta getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "EncuestaUsuario{" +
                "idEncuestaUsuario=" + idEncuestaUsuario +
                ", idEncuesta=" + idEncuesta +
                ", encuesta=" + encuesta +
                ", idUsuario=" + idUsuario +
                ", usuario=" + usuario +
                ", fecha=" + fecha +
                ", resultado='" + resultado + '\'' +
                '}';
    }
}
