package com.aluracursos.convertidormoneda.calculations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Conversion {
    private final String fromCode;
    private final String toCode;
    private final double amount; // Monto original
    private final double result; // Monto convertido
    private final LocalDateTime timestampObject; // Almacenamos el objeto LocalDateTime

    // Formateador estático para la representación en String del timestamp
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Conversion(String fromCode, String toCode, double amount, double result, LocalDateTime timestampObject) {
        this.fromCode = fromCode;
        this.toCode = toCode;
        this.amount = amount;
        this.result = result;
        this.timestampObject = timestampObject;
    }

    // Getters para todas las propiedades
    public String getFromCode() {
        return fromCode;
    }

    public String getToCode() {
        return toCode;
    }

    public double getAmount() {
        return amount;
    }

    public double getResult() {
        return result;
    }

    public LocalDateTime getTimestampObject() {
        return timestampObject;
    }

    // Método para obtener el timestamp formateado como String
    public String getFormattedTimestamp() {
        return timestampObject.format(TIMESTAMP_FORMATTER);
    }

    @Override
    public String toString() {
        // Este toString() puede ser usado para depuración o una representación simple.
        // Para la visualización del historial, formatearemos los números en la clase Principal.
        return getFormattedTimestamp() + ": " + String.format("%.2f", amount) + " " + fromCode +
                " fue convertido a " + String.format("%.2f", result) + " " + toCode;
    }
}