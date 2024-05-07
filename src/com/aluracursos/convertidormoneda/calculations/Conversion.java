package com.aluracursos.convertidormoneda.calculations;

public class Conversion {
    private final String fromCode;
    private final String toCode;
    private final double amount;
    private final double result;
    private final String timestamp;

    public Conversion(String fromCode, String toCode, double amount, double result, String timestamp) {
        this.fromCode = fromCode;
        this.toCode = toCode;
        this.amount = amount;
        this.result = result;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return timestamp + ": " + amount + " " + fromCode + " fue convertido a " + result + " " + toCode;
    }
}
