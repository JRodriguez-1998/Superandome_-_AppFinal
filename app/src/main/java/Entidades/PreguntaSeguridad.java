package Entidades;

public class PreguntaSeguridad {

    private int idPreguntaSeguridad;
    private String descripcion;

    public PreguntaSeguridad() {
    }


    public PreguntaSeguridad(int idPreguntaSeguridad, String descripcion) {
        this.idPreguntaSeguridad = idPreguntaSeguridad;
        this.descripcion = descripcion;
    }

    public int getIdPreguntaSeguridad() {
        return idPreguntaSeguridad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setIdPreguntaSeguridad(int idPreguntaSeguridad) {
        this.idPreguntaSeguridad = idPreguntaSeguridad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "PreguntaSeguridad{" +
                "idPreguntaSeguridad=" + idPreguntaSeguridad +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
