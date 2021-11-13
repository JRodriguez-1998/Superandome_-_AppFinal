package com.example.superandome_appfinal.Constantes;

public enum ItemRutinaEnum {
    DIETA(1),
    CONSUMO(2),
    EJERCICIO(3),
    DESCANSO(4),
    AMBIENTE(5),
    HIGIENE(6),
    EQUILIBRIO(7),
    SOCIAL(8),
    OCIO(9),
    NATURALEZA(10);

    private final int id;

    ItemRutinaEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static ItemRutinaEnum getItemRutina(int id) {
        for (ItemRutinaEnum t : ItemRutinaEnum.values()) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }
}
