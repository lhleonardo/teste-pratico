package br.com.wefin.testepratico.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.wefin.testepratico.exceptions.InvalidDocumentException;

@Embeddable()
public class Document {
    @Column(name = "document_number", nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", nullable = false)
    private DocumentType type;

    public Document(String content, DocumentType type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public DocumentType getType() {
        return type;
    }

    public static Document from(String plainContent) {

        String content = plainContent.replaceAll("[^0-9]+", "");

        // check if content contains only numbers
        if (!content.matches("^[0-9]{11,14}$")) {
            throw new InvalidDocumentException(content);
        }

        int length = content.trim().length();

        if (length != 11 && length != 14) {
            throw new InvalidDocumentException(content);
        }

        return new Document(content, length == 11 ? DocumentType.CPF : DocumentType.CNPJ);
    }
}
