package com.maxdoc.maxdoc.service;

import com.maxdoc.maxdoc.dto.DocumentRequestDTO;
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
//        if (documentRepository.findBySiglaAndVersion(document.getSigla(), document.getVersion()).isPresent()) {
//            throw new IllegalArgumentException("Documento com a mesma sigla e versão já existe.");
//        }
        document.setPhase(Document.Phase.MINUTA);
        return documentRepository.save(document);
    }

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public Document createNewVersion(UUID documentId) {
        Optional<Document> existingDocumentOpt = documentRepository.findById(documentId);
        if (existingDocumentOpt.isEmpty()) {
            throw new IllegalArgumentException("Documento não encontrado com o ID: " + documentId);
        }

        Document existingDocument = existingDocumentOpt.get();
        Document newVersionDocument = new Document();
        newVersionDocument.setTitle(existingDocument.getTitle());
        newVersionDocument.setDescription(existingDocument.getDescription());
        newVersionDocument.setSigla(existingDocument.getSigla());
        newVersionDocument.setVersion(existingDocument.getVersion() + 1);
        newVersionDocument.setPhase(Document.Phase.MINUTA);

        return documentRepository.save(newVersionDocument);
    }

    public void deleteDocumentById(UUID id) {
        if (!documentRepository.existsById(id)) {
            throw new IllegalArgumentException("Documento não encontrado com o ID: " + id);
        }
        documentRepository.deleteById(id);
    }

    public Document updateDocument(UUID id, DocumentRequestDTO requestDTO) {
        Document existingDocument = documentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Documento não encontrado com o ID: " + id));

        existingDocument.setTitle(requestDTO.getTitle());
        existingDocument.setDescription(requestDTO.getDescription());
        existingDocument.setSigla(requestDTO.getSigla());
        existingDocument.setVersion(requestDTO.getVersion());

        return documentRepository.save(existingDocument);
    }

    public Document updatePhase(UUID documentId, Document.Phase newPhase) {
        Optional<Document> optionalDocument = documentRepository.findById(documentId);
        if (optionalDocument.isEmpty()) {
            throw new IllegalArgumentException("Documento não encontrado com o ID: " + documentId);
        }

        Document document = optionalDocument.get();

        if (!isValidPhaseTransition(document.getPhase(), newPhase)) {
            throw new IllegalStateException("Transição de fase inválida: " + document.getPhase() + " -> " + newPhase);
        }

        document.setPhase(newPhase);
        return documentRepository.save(document);
    }

    private boolean isValidPhaseTransition(Document.Phase currentPhase, Document.Phase newPhase) {
        switch (currentPhase) {
            case MINUTA:
                return newPhase == Document.Phase.REVISAO;
            case REVISAO:
                return newPhase == Document.Phase.FINALIZADO || newPhase == Document.Phase.MINUTA;
            default:
                return false;
        }
    }

}
