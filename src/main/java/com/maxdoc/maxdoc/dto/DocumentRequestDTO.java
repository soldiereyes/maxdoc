package com.maxdoc.maxdoc.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DocumentRequestDTO {
    private String title;
    private String description;
    private String sigla;
    private int version;
}


