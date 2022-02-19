package br.com.wefin.testepratico.exceptions;

public class InvalidDocumentException extends RuntimeException {
    public InvalidDocumentException(String document) {
        super("O documento " + document + " não é um CPF ou CNPJ válido.");
    }
}
