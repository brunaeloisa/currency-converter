# Conversor de Moedas

![Badge do Challenge Conversor de Moedas](./badge-conversor.png)

O Conversor de Moedas é uma aplicação de linha de comando desenvolvida em Java que permite realizar conversões de forma rápida e fácil, utilizando a ExchangeRate-API para obter resultados atualizados. Este projeto foi desenvolvido como parte do programa ONE - Oracle Next Education, com o objetivo de consolidar conhecimentos em Java e Orientação a Objetos.

## Funcionalidades

- Menu interativo com validação de entradas do usuário via console;
- Integração com API para realizar conversões com cotações atualizadas;
- Histórico de conversões com rastreamento completo das operações, incluindo data e hora;
- Armazenamento de histórico detalhado em JSON para persistência dos dados entre execuções.

## Conceitos explorados

- Uso de coleções (`Map` e `List`);
- Requisições HTTP e consumo de APIs;
- Processamento de dados JSON com `Gson`;
- Manipulação de dados temporais com `java.time`;
- Leitura e escrita de arquivos;
- Tratamento de exceções.

## Instalação e Execução

1. Clone o repositório:

    ```bash
    git clone https://github.com/brunaeloisa/currency-converter.git
    ```

2. Crie uma conta gratuita no site da [ExchangeRate-API](https://www.exchangerate-api.com/) e obtenha uma API Key.

3. Abra a pasta do projeto em um editor de código.

4. Configure a sua API Key utilizando uma das opções:

    * *Definindo como variável de ambiente (recomendado):* no IntelliJ, acesse "Run" > "Edit Configurations..." > "Environment variables" e adicione uma variável chamada `API_KEY` com o valor da sua chave.
    
    * *Inserindo diretamente no código:* forneça a sua chave como argumento do construtor no momento da criação do objeto da classe `CurrencyConverter` no arquivo `Main.java`, como mostrado abaixo.
    
        ```java
        var converter = new CurrencyConverter("sua_chave_aqui");
        ```

5. Execute o arquivo `Main.java` e escolha uma das opções do menu:

    * **Iniciar Conversão:** Serão solicitados a moeda de origem, a moeda final e o valor a ser convertido. As moedas disponíveis são: ARS, BOB, BRL, CLP, COP, EUR, GBP, JPY e USD;
    * **Exibir Histórico:** Exibe um resumo das conversões realizadas;
    * **Limpar Histórico:** Apaga o histórico de conversões;
    * **Sair:** Encerra o programa.
                         
6. Ao encerrar o programa, o histórico de conversões será salvo no arquivo `conversion-log.json`, o qual armazena as informações detalhadas de cada conversão realizada no seguinte formato:

    ```json
    {
      "originalAmount": 20.0,
      "baseCurrency": "USD",
      "targetCurrency": "BRL",
      "exchangeRate": 5.3167,
      "convertedAmount": 106.334,
      "dateTime": "04 dez. 2025 11:32:18 (BRT)"
    }
    ```