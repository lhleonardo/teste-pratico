package br.com.wefin.testepratico.exceptions;

public class DuplicateDocumentException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Documento duplicado.";

    public DuplicateDocumentException() {
        super(DEFAULT_MESSAGE);
    }
}
