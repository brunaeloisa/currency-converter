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

    public void convert(String baseCurrency, String targetCurrency, double amount) {
        String url = "https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/"
                .formatted(apiKey, baseCurrency, targetCurrency) + amount;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonElement element = JsonParser.parseString(response.body());
            JsonObject objectRoot = element.getAsJsonObject();
            double result = objectRoot.get("conversion_result").getAsDouble();
            System.out.printf("O valor de %.2f %s equivale a %.2f %s.", amount, baseCurrency, result, targetCurrency);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}