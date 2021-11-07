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
public class Consejo {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer idConsejo;

    @Column(nullable = false)
    private String texto;

    @Column(nullable = false)
    private Date fechaAlta;

    @ManyToOne
    @JoinColumn(name = "idTipoConsejo", nullable = false)
    private TipoConsejo tipoConsejo;

    @ManyToOne
    @JoinColumn(name = "idEstado", nullable = false)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "idUsuarioAutor", nullable = false)
    private Usuario usuarioAutor;

    public Consejo() { }

    public Consejo(String texto, TipoConsejo tipoConsejo) {
        this.texto = texto;
        this.tipoConsejo = tipoConsejo;
    }

    public Consejo(String toString, Object o, Object o1, Object o2, Date date) {

    }

    public Integer getIdConsejo() {
        return idConsejo;
    }

    public void setIdConsejo(Integer idConsejo) {
        this.idConsejo = idConsejo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public TipoConsejo getTipoConsejo() {
        return tipoConsejo;
    }

    public void setTipoConsejo(TipoConsejo tipoConsejo) {
        this.tipoConsejo = tipoConsejo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Usuario getUsuarioAutor() {
        return usuarioAutor;
    }

    public void setUsuarioAutor(Usuario usuarioAutor) {
        this.usuarioAutor = usuarioAutor;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @Override
    public String toString() {
        return "Consejo{" +
                "idConsejo=" + idConsejo +
                ", texto='" + texto + '\'' +
                ", tipoConsejo=" + tipoConsejo +
                ", eEstado=" + estado +
                ", usuarioAutor=" + usuarioAutor +
                ", fechaAlta=" + fechaAlta +
                '}';
    }
}
