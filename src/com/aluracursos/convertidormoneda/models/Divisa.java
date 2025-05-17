package com.aluracursos.convertidormoneda.models;

public class Divisa {
    private final String base_code;
//    private final String target_code;
    private final String name;
    private final String countryFlags;

    public Divisa(String base_code, String name, String countryFlags) {
        this.base_code = base_code;
        this.name = name;
        this.countryFlags = countryFlags;
    }

    public String getBase_code() {
        return base_code;
    }

    public String getName() {
        return name;
    }

    public String getCountryFlags() {
        return countryFlags;
    }

    @Override
    public String toString() {
        return countryFlags + " (" + base_code + ") " + name;
    }
}

