package com.maxdoc.maxdoc.service;

import com.maxdoc.maxdoc.entity.Document;
import com.maxdoc.maxdoc.repository.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Document createDocument(Document document) {
        if (documentRepository.findBySiglaAndVersion(document.getSigla(), document.getVersion()).isPresent()) {
            throw new IllegalArgumentException("Documento com a mesma sigla e versão já existe.");
        }
        document.setPhase(Document.Phase.MINUTA);
        return documentRepository.save(document);
    }

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }
}
