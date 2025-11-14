package br.com.alura.currencyconverter;

import br.com.alura.currencyconverter.models.Conversion;
import br.com.alura.currencyconverter.models.CurrencyConverter;

public class Main {
    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();
        Conversion conversion = converter.convert("USD", "BRL", 1);
        conversion.displayResult();
    }
}
