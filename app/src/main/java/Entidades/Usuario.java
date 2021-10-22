package Entidades;

import java.util.Date;

public class Usuario {

    private String idUsuario;
    private String nickname;
    private TipoUsuario tipoUsuario;
    private int idTipoUsuario;
    private String password;
    private Boolean habilitado;
    private Date fechaAlta;
    private Date fechaNac;
    private Genero genero;
    private int idTipoGenero;
    private String horarioEmocion; //En duda
    private String ultimoIngreso; //En duda
    private PreguntaSeguridad preguntaSeguridad;
    private int idPreguntaSeguridad;
    private String respuesta;


    public Usuario() {
    }

    public Usuario(String idUsuario, String nickname, TipoUsuario tipoUsuario, int idTipoUsuario, String password, Boolean habilitado, Date fechaAlta, Date fechaNac, Genero genero, int idTipoGenero, String horarioEmocion, String ultimoIngreso, PreguntaSeguridad preguntaSeguridad, int idPreguntaSeguridad, String respuesta) {
        this.idUsuario = idUsuario;
        this.nickname = nickname;
        this.tipoUsuario = tipoUsuario;
        this.idTipoUsuario = idTipoUsuario;
        this.password = password;
        this.habilitado = habilitado;
        this.fechaAlta = fechaAlta;
        this.fechaNac = fechaNac;
        this.genero = genero;
        this.idTipoGenero = idTipoGenero;
        this.horarioEmocion = horarioEmocion;
        this.ultimoIngreso = ultimoIngreso;
        this.preguntaSeguridad = preguntaSeguridad;
        this.idPreguntaSeguridad = idPreguntaSeguridad;
        this.respuesta = respuesta;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNickname() {
        return nickname;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public Genero getGenero() {
        return genero;
    }

    public int getIdTipoGenero() {
        return idTipoGenero;
    }

    public String getHorarioEmocion() {
        return horarioEmocion;
    }

    public String getUltimoIngreso() {
        return ultimoIngreso;
    }

    public PreguntaSeguridad getPreguntaSeguridad() {
        return preguntaSeguridad;
    }

    public int getIdPreguntaSeguridad() {
        return idPreguntaSeguridad;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public void setIdTipoGenero(int idTipoGenero) {
        this.idTipoGenero = idTipoGenero;
    }

    public void setHorarioEmocion(String horarioEmocion) {
        this.horarioEmocion = horarioEmocion;
    }

    public void setUltimoIngreso(String ultimoIngreso) {
        this.ultimoIngreso = ultimoIngreso;
    }

    public void setPreguntaSeguridad(PreguntaSeguridad preguntaSeguridad) {
        this.preguntaSeguridad = preguntaSeguridad;
    }

    public void setIdPreguntaSeguridad(int idPreguntaSeguridad) {
        this.idPreguntaSeguridad = idPreguntaSeguridad;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario='" + idUsuario + '\'' +
                ", nickname='" + nickname + '\'' +
                ", tipoUsuario=" + tipoUsuario +
                ", idTipoUsuario=" + idTipoUsuario +
                ", password='" + password + '\'' +
                ", habilitado=" + habilitado +
                ", fechaAlta=" + fechaAlta +
                ", fechaNac=" + fechaNac +
                ", genero=" + genero +
                ", idTipoGenero=" + idTipoGenero +
                ", horarioEmocion='" + horarioEmocion + '\'' +
                ", ultimoIngreso='" + ultimoIngreso + '\'' +
                ", preguntaSeguridad=" + preguntaSeguridad +
                ", idPreguntaSeguridad=" + idPreguntaSeguridad +
                ", respuesta='" + respuesta + '\'' +
                '}';
    }
}
