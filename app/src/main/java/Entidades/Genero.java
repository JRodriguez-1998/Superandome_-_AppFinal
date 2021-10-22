package Entidades;

public class Genero {
    private int idGenero;
    private String descripcion;

    public Genero() {
    }

    public Genero(int idGenero, String descripcion) {
        this.idGenero = idGenero;
        this.descripcion = descripcion;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Genero{" +
                "idGenero=" + idGenero +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
