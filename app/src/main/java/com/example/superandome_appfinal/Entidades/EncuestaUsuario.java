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
public class EncuestaUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEncuestaUsuario;

    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private String resultado;

    @ManyToOne
    @JoinColumn(name = "idEncuesta", nullable = false)
    private Encuesta encuesta;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    public EncuestaUsuario() {}

    public EncuestaUsuario(Integer idEncuestaUsuario, Date fecha, String resultado, Encuesta encuesta, Usuario usuario) {
        this.idEncuestaUsuario = idEncuestaUsuario;
        this.fecha = fecha;
        this.resultado = resultado;
        this.encuesta = encuesta;
        this.usuario = usuario;
    }

    public Integer getIdEncuestaUsuario() {
        return idEncuestaUsuario;
    }

    public void setIdEncuestaUsuario(Integer idEncuestaUsuario) {
        this.idEncuestaUsuario = idEncuestaUsuario;
    }

    public Encuesta getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
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
                ", encuesta=" + encuesta +
                ", usuario=" + usuario +
                ", fecha=" + fecha +
                ", resultado='" + resultado + '\'' +
                '}';
    }
}
