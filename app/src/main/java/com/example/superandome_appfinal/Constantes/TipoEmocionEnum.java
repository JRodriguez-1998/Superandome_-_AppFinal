package com.example.superandome_appfinal.Constantes;

public enum TipoEmocionEnum {
    ASCO(1),
    IRA(2),
    FELICIDAD(3),
    MIEDO(4),
    TRISTERZA(5);

    private final int tipo;

    TipoEmocionEnum(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public static TipoEmocionEnum getTipoEmocion(int tipo) {
        for (TipoEmocionEnum t : TipoEmocionEnum.values()) {
            if (t.getTipo() == tipo) {
                return t;
            }
        }
        return null;
    }
}
