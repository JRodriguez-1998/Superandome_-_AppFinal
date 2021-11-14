package com.example.superandome_appfinal.Constantes;

import androidx.annotation.NonNull;

public enum EncuestaEnum {
    TEST_ANSIEDAD_BECK(1),
    NO_IMPLEMENTADO(-1);

    private final int id;

    EncuestaEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EncuestaEnum getEncuestaEnum(int id) {
        for (EncuestaEnum t : EncuestaEnum.values()) {
            if (t.getId() == id) {
                return t;
            }
        }
        return NO_IMPLEMENTADO;
    }
}
