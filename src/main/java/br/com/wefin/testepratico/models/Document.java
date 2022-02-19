package br.com.wefin.testepratico.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable()
public class Document {
    @Column(name="document_number")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name="document_type")
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
}
