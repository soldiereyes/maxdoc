package com.maxdoc.maxdoc.controller;

import com.maxdoc.maxdoc.entity.Document;
import com.maxdoc.maxdoc.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public ResponseEntity<Document> createDocument(@RequestBody Document document) {
        return ResponseEntity.ok(documentService.createDocument(document));
    }

    @GetMapping
    public ResponseEntity<List<Document>> getAllDocuments() {
        return ResponseEntity.ok(documentService.getAllDocuments());
    }
}
