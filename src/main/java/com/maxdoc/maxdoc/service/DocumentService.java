package com.maxdoc.maxdoc.service;

import com.maxdoc.maxdoc.entity.Document;
import com.maxdoc.maxdoc.repository.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Document updatePhase(UUID id, Document.Phase newPhase) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Documento não encontrado."));
        document.setPhase(newPhase);
        return documentRepository.save(document);
    }

    public Document createNewVersion(UUID id) {
        Document existingDocument = documentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Documento encontrado."));
        if (existingDocument.getPhase() != Document.Phase.VIGENTE) {
            throw new IllegalStateException("Somente documentos vigentes podem gerar novas versões.");
        }

        existingDocument.setPhase(Document.Phase.OBSOLETO);
        documentRepository.save(existingDocument);

        Document newVersion = new Document();
        newVersion.setTitle(existingDocument.getTitle());
        newVersion.setDescription(existingDocument.getDescription());
        newVersion.setSigla(existingDocument.getSigla());
        newVersion.setVersion(existingDocument.getVersion() + 1);
        newVersion.setPhase(Document.Phase.MINUTA);

        return documentRepository.save(newVersion);
    }
}
