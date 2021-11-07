package com.example.superandome_appfinal.Constantes;

public enum TipoUsuarioEnum {
    CONSULTANTE(1),
    PROFESIONAL(2),
    DIRECTOR(3);

    private final int tipo;

    TipoUsuarioEnum(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public static TipoUsuarioEnum getTipoUsuario(int tipo) {
        for (TipoUsuarioEnum t : TipoUsuarioEnum.values()) {
            if (t.getTipo() == tipo) {
                return t;
            }
        }
        return null;
    }

}
