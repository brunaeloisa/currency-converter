package br.com.alura.currencyconverter.models;

public record ConversionResult(double conversion_result, double conversion_rate, String base_code, String target_code) {
}
