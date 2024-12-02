package com.maxdoc.maxdoc.dto;

import lombok.Data;
import java.util.UUID;

public class DocumentResponseDTO {
    private UUID id;
    private String title;
    private String description;
    private String sigla;
    private int version;
    private String phase;
}
