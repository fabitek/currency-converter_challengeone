package com.aluracursos.convertidormoneda.calculations;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClienteHttp {
    public static String realizaSolicitud(String base_code, String target_code, double amount) {
        String formattedAmount = String.format("%.0f", amount);
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/b30a0eb691f51f24fbab2809/pair/" +
                base_code + "/" + target_code + "/" + formattedAmount);
//        System.out.println("Así queda la dirección: " + direccion);
//        JsonParser parser = new JsonParser();

        try {
            HttpClient cliente = HttpClient.newHttpClient();

            HttpRequest solicitud = HttpRequest.newBuilder()
                    .uri(direccion)
                    .build();

            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
            System.out.println("\nRespuesta status code:" + respuesta.statusCode());
//            System.out.println("Respuesta JSON: " + respuesta.body());
//            System.out.println("Respuesta body: " + parseJson(respuesta.body()));

            if (respuesta.statusCode() != 200 || respuesta.body().trim().startsWith("{") == false) {
                return "Error o respuesta inesperada de la API: " + respuesta.body();
            }

            Gson gson = new Gson();
            ExchangeRate exchangeRate = gson.fromJson(respuesta.body(), ExchangeRate.class);
            double conversionResult = exchangeRate.getConversion_result();
            return String.format("%.2f", conversionResult);

        } catch (JsonSyntaxException e) {
            return "Error al parsear la respuesta: " + e.getMessage();

        } catch (Exception e) {
            return "Error al realizar la solicitud: " + e.getMessage();

        }
    }

}

