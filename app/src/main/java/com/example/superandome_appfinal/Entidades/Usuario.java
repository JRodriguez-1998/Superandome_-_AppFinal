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
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean habilitado;

    @Column(nullable = false)
    private Date fechaAlta;

    @Column(nullable = false)
    private Date fechaNac;

    @Column
    private Date horarioEmocion;
    @Column
    private Date ultimoIngreso;
    @Column
    private String respuesta;

    @ManyToOne
    @JoinColumn(name = "idTipoUsuario", nullable = false)
    private TipoUsuario tipoUsuario;

    @ManyToOne
    @JoinColumn(name = "idGenero", nullable = false)
    private Genero genero;

    @ManyToOne
    @JoinColumn(name = "idPreguntaSeguridad")
    private PreguntaSeguridad preguntaSeguridad;


    public Usuario() {
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public Date getHorarioEmocion() {
        return horarioEmocion;
    }

    public void setHorarioEmocion(Date horarioEmocion) {
        this.horarioEmocion = horarioEmocion;
    }

    public Date getUltimoIngreso() {
        return ultimoIngreso;
    }

    public void setUltimoIngreso(Date ultimoIngreso) {
        this.ultimoIngreso = ultimoIngreso;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public PreguntaSeguridad getPreguntaSeguridad() {
        return preguntaSeguridad;
    }

    public void setPreguntaSeguridad(PreguntaSeguridad preguntaSeguridad) {
        this.preguntaSeguridad = preguntaSeguridad;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", habilitado=" + habilitado +
                ", fechaAlta=" + fechaAlta +
                ", fechaNac=" + fechaNac +
                ", horarioEmocion=" + horarioEmocion +
                ", ultimoIngreso=" + ultimoIngreso +
                ", respuesta='" + respuesta + '\'' +
                ", tipoUsuario=" + tipoUsuario +
                ", genero=" + genero +
                ", preguntaSeguridad=" + preguntaSeguridad +
                '}';
    }
}
