package br.com.alura.currencyconverter;

import br.com.alura.currencyconverter.models.Conversion;
import br.com.alura.currencyconverter.models.CurrencyConverter;
import br.com.alura.currencyconverter.models.HistoryManager;
import br.com.alura.currencyconverter.models.Menu;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Conversion> log = HistoryManager.readJsonLog();
        var converter = new CurrencyConverter();
        var scanner = new Scanner(System.in);
        int option;
        int action;

        do {
            System.out.println("""
                    \n===============================
                          CONVERSOR DE MOEDAS
                    ===============================
                
                    1 - Iniciar conversão
                    2 - Exibir histórico
                    3 - Limpar histórico
                    0 - Sair""");

            option = Menu.readMenuOption(scanner, 4);

            switch (option) {
                case 0:
                    HistoryManager.saveJsonLog(log);
                    break;
                case 1:
                    do {
                        String baseCode = Menu.readCurrency(scanner, null);
                        if (baseCode == null) break;

                        String targetCode = Menu.readCurrency(scanner, baseCode);
                        if (targetCode == null) break;

                        do {
                            System.out.println("\n===============================\n");
                            System.out.printf("Convertendo de %s para %s...\n", baseCode, targetCode);
                            double amount = Menu.readAmount(scanner);

                            Conversion conversion = converter.convert(baseCode, targetCode, amount);
                            conversion.displayResult();
                            log.add(conversion);

                            System.out.println("\n\n===============================\n");
                            System.out.println("1 - Converter outro valor");
                            System.out.println("2 - Iniciar nova conversão");
                            System.out.println("0 - Voltar para o menu");

                            action = Menu.readMenuOption(scanner, 3);
                        } while (action == 1);
                    } while (action == 2);
                    break;
                case 2:
                    System.out.println("\n===============================\n");
                    System.out.println("    HISTÓRICO DE CONVERSÕES    \n");

                    if (log.isEmpty()) {
                        System.out.println(" Não há registros de conversão.\n");
                    } else {
                        log.forEach(System.out::println);
                    }
                    break;
                case 3:
                    log.clear();
                    HistoryManager.saveJsonLog(log);
                    System.out.println("\nO histórico foi limpo!");
                    break;
            }
        } while (option != 0);
    }
}
