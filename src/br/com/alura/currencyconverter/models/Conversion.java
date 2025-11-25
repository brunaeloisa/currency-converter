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

    private String numberFormat(double amount) {
        String format = amount < 0.01 ? "%.4f" : "%.2f";
        return format.formatted(amount);
    }

    public void displayResult() {
        System.out.printf(
            "%s %s equivale a %s %s.",
            numberFormat(originalAmount),
            baseCurrency,
            numberFormat(convertedAmount),
            targetCurrency
        );
    }

    @Override
    public String toString() {
        return "%s\n%s %s => %s %s".formatted(
                dateTime,
                numberFormat(originalAmount),
                baseCurrency,
                numberFormat(convertedAmount),
                targetCurrency);
    }
}
