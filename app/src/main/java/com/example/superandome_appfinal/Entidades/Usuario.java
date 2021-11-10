package com.example.superandome_appfinal.Entidades;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable
public class Usuario {

    @DatabaseField(generatedId = true)
    private Integer idUsuario;

    @DatabaseField(unique = true, canBeNull = false)
    private String nickname;

    @DatabaseField(canBeNull = false)
    private String password;

    @DatabaseField(canBeNull = false)
    private Boolean habilitado;

    @DatabaseField(canBeNull = false)
    private Date fechaAlta;

    @DatabaseField(dataType =  DataType.DATE_STRING, format = "yyyy-MM-dd", canBeNull = false)
    private Date fechaNac;

    @DatabaseField(dataType =  DataType.DATE_STRING, format = "HH:mm")
    private Date horarioEmocion;

    @DatabaseField
    private Date ultimoIngreso;

    @DatabaseField
    private String respuesta;

    @DatabaseField(foreign = true, columnName = "idTipoUsuario", canBeNull = false)
    private TipoUsuario tipoUsuario;

    @DatabaseField(foreign = true, columnName = "idGenero", canBeNull = false)
    private Genero genero;

    @DatabaseField(foreign = true, columnName = "idPreguntaSeguridad")
    private PreguntaSeguridad preguntaSeguridad;

    public Usuario() {
    }

    public Usuario(String nickname, String password, Boolean habilitado, Date fechaNac, TipoUsuario tipoUsuario, Genero genero) {
        this.nickname = nickname;
        this.password = password;
        this.habilitado = habilitado;
        this.fechaNac = fechaNac;
        this.tipoUsuario = tipoUsuario;
        this.genero = genero;
    }

    public Usuario(String nickname, String password, Boolean habilitado, Date fechaNac, String respuesta, TipoUsuario tipoUsuario, Genero genero, PreguntaSeguridad preguntaSeguridad) {
        this.nickname = nickname;
        this.password = password;
        this.habilitado = habilitado;
        this.fechaNac = fechaNac;
        this.respuesta = respuesta;
        this.tipoUsuario = tipoUsuario;
        this.genero = genero;
        this.preguntaSeguridad = preguntaSeguridad;
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
