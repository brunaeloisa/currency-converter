package br.com.alura.currencyconverter.models;

import br.com.alura.currencyconverter.exceptions.InvalidApiKeyException;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyConverter {
    private final String apiKey;

    public CurrencyConverter() {
        apiKey = System.getenv("API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            throw new InvalidApiKeyException("A chave da API não foi configurada. Defina a variável de " +
                    "ambiente API_KEY ou passe a chave no construtor.");
        }
    }

    public CurrencyConverter(String apiKey) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new InvalidApiKeyException("apiKey deve ser uma String válida.");
        }
        this.apiKey = apiKey;
    }

    public Conversion convert(String baseCurrency, String targetCurrency, double amount) {
        String url = "https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/"
                .formatted(apiKey, baseCurrency, targetCurrency) + amount;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonElement element = JsonParser.parseString(response.body());
            JsonObject objectRoot = element.getAsJsonObject();
            String status = objectRoot.get("result").getAsString();

            if (status.equals("success")) {
                var conversionResult = new Gson().fromJson(element, ConversionResult.class);
                return new Conversion(conversionResult, amount);
            }

            String error = objectRoot.get("error-type").getAsString();

            if (error.equals("invalid-key")) {
                throw new InvalidApiKeyException("Insira uma chave válida.");
            } else {
                throw new RuntimeException("Ocorreu um erro do tipo " + error + ".");
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}