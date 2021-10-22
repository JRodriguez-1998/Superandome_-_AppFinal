package com.example.superandome_appfinal.Entidades;

import java.util.Date;

public class Consejo {

    private Integer idConsejo;
    private String texto;
    private TipoConsejo idTipoConsejo;
    private Estado idEstado;
    private Usuario idUsuarioAutor;
    private Date fechaAlta;

    //propiedades por si sale orm
    private Integer id_TipoConsejo;
    private Boolean id_Estado;
    private Integer id_UsuarioAutor;

    public Consejo() { }

    public Consejo(Integer idConsejo, String texto, TipoConsejo idTipoConsejo, Estado idEstado, Usuario idUsuarioAutor, Date fechaAlta) {
        this.idConsejo = idConsejo;
        this.texto = texto;
        this.idTipoConsejo = idTipoConsejo;
        this.idEstado = idEstado;
        this.idUsuarioAutor = idUsuarioAutor;
        this.fechaAlta = fechaAlta;
    }

    public Integer getIdConsejo() {
        return idConsejo;
    }

    public String getTexto() {
        return texto;
    }

    public TipoConsejo getIdTipoConsejo() {
        return idTipoConsejo;
    }

    public Estado getIdEstado() {
        return idEstado;
    }

    public Usuario getIdUsuarioAutor() {
        return idUsuarioAutor;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setIdConsejo(Integer idConsejo) {
        this.idConsejo = idConsejo;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setIdTipoConsejo(TipoConsejo idTipoConsejo) {
        this.idTipoConsejo = idTipoConsejo;
    }

    public void setIdEstado(Estado idEstado) {
        this.idEstado = idEstado;
    }

    public void setIdUsuarioAutor(Usuario idUsuarioAutor) {
        this.idUsuarioAutor = idUsuarioAutor;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
}
