package com.aluracursos.convertidormoneda.calculations;

public class ExchangeRate {
    double conversion_rate;
    private double conversion_result;

    public ExchangeRate(double conversion_result, double conversion_rate) {
        this.conversion_result = conversion_result;
        this.conversion_rate = conversion_rate;
    }

    public double getConversion_rate() {
        return conversion_rate;
    }

    public void setConversion_rate(double conversion_rate) {
        this.conversion_rate = conversion_rate;
    }

    public double getConversion_result() {
        return conversion_result;
    }

    public void setConversion_result(double conversion_result) {
        this.conversion_result = conversion_result;
    }
}
