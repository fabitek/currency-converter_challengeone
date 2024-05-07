package com.aluracursos.convertidormoneda.models;

public class Divisa {
    private final String base_code;
//    private final String target_code;
    private final String nombre;
    private final String pais;

    public Divisa(String base_code, String nombre, String pais) {
        this.base_code = base_code;
        this.nombre = nombre;
        this.pais = pais;
    }

    public String getBase_code() {
        return base_code;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }

    @Override
    public String toString() {
        return base_code + " " + nombre + " " + pais;
    }
}

