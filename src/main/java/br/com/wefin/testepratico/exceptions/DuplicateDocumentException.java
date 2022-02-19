package br.com.wefin.testepratico.exceptions;

public class DuplicateDocumentException extends RuntimeException {
    public DuplicateDocumentException() {
        super("Documento duplicado.");
    }
}
