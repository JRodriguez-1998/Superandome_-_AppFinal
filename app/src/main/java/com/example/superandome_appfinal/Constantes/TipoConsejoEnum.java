package com.example.superandome_appfinal.Constantes;

public enum TipoConsejoEnum {
    GENERAL(1),
    ASCO(2),
    IRA(3),
    FELICIDAD(4),
    MIEDO(5),
    TRISTEZA(6);

    private final int tipo;

    TipoConsejoEnum(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public static TipoConsejoEnum getTipoConsejo(int tipo) {
        for (TipoConsejoEnum t : TipoConsejoEnum.values()) {
            if (t.getTipo() == tipo) {
                return t;
            }
        }
        return null;
    }
}
