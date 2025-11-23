package br.com.alura.currencyconverter.models;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Conversion {
    private final double originalAmount;
    private final String baseCurrency;
    private final String targetCurrency;
    private final double exchangeRate;
    private final double convertedAmount;
    private final String dateTime;

    public Conversion(ConversionResult result, double amount) {
        this.originalAmount = amount;
        this.convertedAmount = result.conversion_result();
        this.exchangeRate = result.conversion_rate();
        this.baseCurrency = result.base_code();
        this.targetCurrency = result.target_code();

        ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "dd MMM yyyy HH:mm:ss (zzz)",
                new Locale("pt", "BR")
        );

        this.dateTime = currentTime.format(formatter);
    }

    public void displayResult() {
        System.out.printf(
            "%.2f %s equivale a %.2f %s.",
            originalAmount,
            baseCurrency,
            convertedAmount,
            targetCurrency
        );
    }

    @Override
    public String toString() {
        return "%s\n%.2f %s => %.2f %s".formatted(
                dateTime,
                originalAmount,
                baseCurrency,
                convertedAmount,
                targetCurrency);
    }
}
