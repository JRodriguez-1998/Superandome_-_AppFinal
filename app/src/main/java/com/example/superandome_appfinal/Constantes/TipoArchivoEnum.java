package com.example.superandome_appfinal.Constantes;

public enum TipoArchivoEnum {
    PDF(1),
    VIDEO(2),
    AUDIO(3);

    private final int tipo;

    TipoArchivoEnum(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public static TipoArchivoEnum getTipoArchivo(int tipo) {
        for (TipoArchivoEnum t : TipoArchivoEnum.values()) {
            if (t.getTipo() == tipo) {
                return t;
            }
        }
        return null;
    }
}
