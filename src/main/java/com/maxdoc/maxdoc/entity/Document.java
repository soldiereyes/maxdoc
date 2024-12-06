package com.maxdoc.maxdoc.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Data
@Entity
@Table(name = "documents", uniqueConstraints = @UniqueConstraint(columnNames = {"sigla", "version"}))
public class Document {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false)
    private UUID id;

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
        OBSOLETO,
        REVISAO,
        FINALIZADO
    }

    public void incrementVersion() {
        this.version += 1;
        this.phase = Phase.MINUTA;
    }
}
