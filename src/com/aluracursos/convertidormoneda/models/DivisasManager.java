package com.aluracursos.convertidormoneda.models;

import java.util.ArrayList;
import java.util.List;

public class DivisasManager {
    public static List<Divisa> divisas = new ArrayList<>();

    static {
        initializeDivisas();
    }

    private static void initializeDivisas() {
        divisas.add(new Divisa("USD", "Dólar Estadounidense", "🇺🇸"));
        divisas.add(new Divisa("EUR", "Euro", "🇪🇺"));
        divisas.add(new Divisa("JPY", "Yen Japonés", "🇯🇵"));
        divisas.add(new Divisa("GBP", "Libra Esterlina", "🇬🇧"));
        divisas.add(new Divisa("AUD", "Dólar Australiano", "🇦🇺"));
        divisas.add(new Divisa("CAD", "Dólar Canadiense", "🇨🇦"));
        divisas.add(new Divisa("CHF", "Franco Suizo", "🇨🇭"));
        divisas.add(new Divisa("CNY", "Yuan Renminbi", "🇨🇳"));
        divisas.add(new Divisa("SEK", "Corona Sueca", "🇸🇪"));
        divisas.add(new Divisa("NZD", "Dólar Neozelandés", "🇳🇿"));
        divisas.add(new Divisa("COP", "Peso Colombiano", "🇨🇴"));
        divisas.add(new Divisa("ARS", "Argentine Peso", "🇦🇷"));
        divisas.add(new Divisa("BOB", "Bolivar Boliviano", "🇧🇴"));
        divisas.add(new Divisa("BRL", "Brazilian Real", "🇧🇷"));
    }

    public static List<Divisa> getDivisas() {
        return divisas;
    }

}
