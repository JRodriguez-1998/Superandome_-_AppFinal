package com.example.superandome_appfinal;

public class ClasePrueba {
    private String Contenido;
    private String Sugerido;
    private String Tipo;

    public ClasePrueba(String contenido, String sugerido, String tipo) {
        Contenido = contenido;
        Sugerido = sugerido;
        Tipo = tipo;
    }

    public String getContenido() {
        return Contenido;
    }

    public void setContenido(String contenido) {
        Contenido = contenido;
    }

    public String getSugerido() {
        return Sugerido;
    }

    public void setSugerido(String sugerido) {
        Sugerido = sugerido;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }
}
