package br.com.wefin.testepratico.exceptions;

public class InvalidDocumentException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "O documento %s não é um CPF ou CNPJ válido.";

    public InvalidDocumentException(String document) {
        super(String.format(DEFAULT_MESSAGE, document));
    }
}
