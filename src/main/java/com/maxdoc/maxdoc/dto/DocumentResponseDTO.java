package com.maxdoc.maxdoc.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class DocumentResponseDTO {
    private UUID id;
    private String title;
    private String description;
    private String sigla;
    private int version;
    private String phase;
}
