package com.maxdoc.maxdoc.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "documents", uniqueConstraints = @UniqueConstraint(columnNames = {"sigla", "version"}))
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String sigla;

    @Column(nullable = false)
    private int version;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Phase phase;

    public enum Phase {
        MINUTA,
        VIGENTE,
        OBSOLETO
    }
}
