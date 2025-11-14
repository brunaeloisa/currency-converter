package br.com.alura.currencyconverter.exceptions;

public class InvalidApiKeyException extends RuntimeException {
    public InvalidApiKeyException(String message) {
        super(message);
    }
}
