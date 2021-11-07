package com.example.superandome_appfinal.Constantes;

public enum EstadoEnum {
    PENDIENTE(1),
    RECHAZADO_PROFESIONAL(2),
    APROBADO_PROFESIONAL(3),
    RECHAZADO_DIRECTOR(4),
    APROBADO_DIRECTOR(5);

    private final int id;

    EstadoEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EstadoEnum getEstado(int id) {
        for (EstadoEnum t : EstadoEnum.values()) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }
}
