package com.aluracursos.convertidormoneda.calculations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonParser {
    private Gson gson;

    public JsonParser() {
        this.gson = new Gson();
    }

    public ExchangeRate parseExchangeRate(String json) {
        return gson.fromJson(json, ExchangeRate.class);
    }
}
