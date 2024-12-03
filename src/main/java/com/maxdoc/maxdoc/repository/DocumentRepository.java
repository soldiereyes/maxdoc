package com.maxdoc.maxdoc.repository;

import com.maxdoc.maxdoc.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DocumentRepository extends JpaRepository<Document, UUID> {
    Optional<Document> findBySiglaAndVersion(String sigla, int version);
}
