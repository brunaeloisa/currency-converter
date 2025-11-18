package br.com.alura.currencyconverter;

import br.com.alura.currencyconverter.models.Conversion;
import br.com.alura.currencyconverter.models.CurrencyConverter;
import br.com.alura.currencyconverter.models.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var converter = new CurrencyConverter();
        var scanner = new Scanner(System.in);
        int action;

        do {
            System.out.println("\n===============================");
            System.out.println("      CONVERSOR DE MOEDAS      ");
            System.out.println("===============================\n");

            String baseCode = Menu.readCurrency(scanner, null);
            String targetCode = Menu.readCurrency(scanner, baseCode);

            do {
                System.out.println("\n===============================\n");
                System.out.printf("Convertendo de %s para %s...\n", baseCode, targetCode);
                double amount = Menu.readAmount(scanner);
                Conversion conversion = converter.convert(baseCode, targetCode, amount);
                conversion.displayResult();

                System.out.println("\n\n===============================\n");
                System.out.println("1 - Converter outro valor");
                System.out.println("2 - Voltar para o menu");
                System.out.println("0 - Sair");

                action = Menu.readMenuOption(scanner);
            } while (action == 1);
        } while (action == 2);
    }
}
