package br.com.alura.currencyconverter.models;

import java.util.*;

public class Menu {
    private static final Map<String, String> CURRENCY_NAMES = Map.of(
            "ARS", "Peso argentino",
            "BOB", "Boliviano",
            "BRL", "Real",
            "CLP", "Peso chileno",
            "COP", "Peso colombiano",
            "EUR", "Euro",
            "GBP", "Libra esterlina",
            "JPY", "Iene",
            "USD", "Dólar americano"
    );

    public static double readAmount(Scanner scanner) {
        while (true) {
            try {
                System.out.print("\nInsira o valor inicial: ");
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Utilize o formato 00,00");
                scanner.nextLine();
            }
        }
    }

    public static int readMenuOption(Scanner scanner) {
        while (true) {
            try {
                System.out.print("\nEscolha uma ação: ");
                int option = scanner.nextInt();

                if (option == 0) {
                    System.exit(0);
                } else if (option == 1 || option == 2) {
                    return option;
                } else {
                    System.out.println("Opção inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite uma opção válida.");
                scanner.nextLine();
            }
        }
    }

    public static String readCurrency(Scanner scanner, String baseCode) {
        List<String> currencies = new ArrayList<>(CURRENCY_NAMES.keySet());
        currencies.remove(baseCode);
        Collections.sort(currencies);

        if (baseCode == null) {
            System.out.println("Escolha a moeda de origem:\n");
        } else {
            System.out.println("\n===============================\n");
            System.out.printf("Converter de %s para...\n\n", baseCode);
        }

        for (int i = 1; i <= currencies.size(); i++) {
            String currency = currencies.get(i-1);
            System.out.printf("%d - [%s] %s\n", i, currency, CURRENCY_NAMES.get(currency));
        }

        System.out.println("0 - Sair");

        while (true) {
            try {
                System.out.printf("\nEscolha uma opção (0-%d): ", currencies.size());
                int option = scanner.nextInt();

                if (option == 0) {
                    System.exit(0);
                }

                return currencies.get(option - 1);
            } catch (IndexOutOfBoundsException | InputMismatchException e) {
                System.out.println("Digite uma opção válida.");
                scanner.nextLine();
            }
        }
    }
}