package com.maxdoc.maxdoc.controller;

import com.maxdoc.maxdoc.dto.DocumentRequestDTO;
import com.maxdoc.maxdoc.dto.DocumentResponseDTO;
import com.maxdoc.maxdoc.entity.Document;
import com.maxdoc.maxdoc.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/documents")
@CrossOrigin(origins = "http://localhost:4200")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public ResponseEntity<Document> createDocument(@RequestBody DocumentRequestDTO requestDTO) {
        Document document = new Document();
        document.setTitle(requestDTO.getTitle());
        document.setDescription(requestDTO.getDescription());
        document.setSigla(requestDTO.getSigla());
        document.setVersion(requestDTO.getVersion());

        Document createdDocument = documentService.createDocument(document);

        return ResponseEntity.ok(documentService.createDocument(document));
    }

    @GetMapping
    public ResponseEntity<List<DocumentResponseDTO>> getAllDocuments() {
        List<Document> documents = documentService.getAllDocuments();
        List<DocumentResponseDTO> responseDTOs = documents.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    @PatchMapping("/{id}/phase")
    public ResponseEntity<DocumentResponseDTO> updatePhase(
            @PathVariable UUID id,
            @RequestParam Document.Phase phase) {
        Document updatedDocument = documentService.updatePhase(id, phase);
        return ResponseEntity.ok(mapToResponseDTO(updatedDocument));
    }

    @PostMapping("/{id}/new-version")
    public ResponseEntity<DocumentResponseDTO> createNewVersion(@PathVariable UUID id) {
        Document newVersion = documentService.createNewVersion(id);
        return ResponseEntity.ok(mapToResponseDTO(newVersion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable("id") UUID id) {
        documentService.deleteDocumentById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentResponseDTO> updateDocument(
            @PathVariable UUID id,
            @RequestBody DocumentRequestDTO requestDTO) {
        Document updatedDocument = documentService.updateDocument(id, requestDTO);
        return ResponseEntity.ok(mapToResponseDTO(updatedDocument));
    }

    private DocumentResponseDTO mapToResponseDTO(Document document) {
        DocumentResponseDTO responseDTO = new DocumentResponseDTO();
        responseDTO.setId(document.getId());
        responseDTO.setTitle(document.getTitle());
        responseDTO.setDescription(document.getDescription());
        responseDTO.setSigla(document.getSigla());
        responseDTO.setVersion(document.getVersion());
        responseDTO.setPhase(document.getPhase().name());
        return responseDTO;
    }


}
