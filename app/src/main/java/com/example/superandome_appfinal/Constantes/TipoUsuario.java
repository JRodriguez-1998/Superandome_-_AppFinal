package com.example.superandome_appfinal.Constantes;

public enum TipoUsuario {
    CONSULTANTE(1),
    PROFESIONAL(2),
    DIRECTOR(3);

    private final int tipo;

    TipoUsuario(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public static TipoUsuario getTipoUsuario(int tipo) {
        for (TipoUsuario t : TipoUsuario.values()) {
            if (t.getTipo() == tipo) {
                return t;
            }
        }
        return null;
    }

}
