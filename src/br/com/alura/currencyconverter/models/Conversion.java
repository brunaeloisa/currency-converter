package br.com.alura.currencyconverter.models;

public class Conversion {
    private double originalAmount;
    private String baseCurrency;
    private String targetCurrency;
    private double exchangeRate;
    private double convertedAmount;

    public Conversion(ConversionResult result, double amount) {
        this.originalAmount = amount;
        this.convertedAmount = result.conversion_result();
        this.exchangeRate = result.conversion_rate();
        this.baseCurrency = result.base_code();
        this.targetCurrency = result.target_code();
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
}
