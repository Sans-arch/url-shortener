package com.negreira.santiago.urlshortener.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UrlNotFoundResponseDTO {
    private int status;
    private String message;
    private long timestamp;
}
