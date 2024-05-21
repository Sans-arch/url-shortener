package com.negreira.santiago.urlshortener.exception.handler;

import com.negreira.santiago.urlshortener.dto.UrlNotFoundResponseDTO;
import com.negreira.santiago.urlshortener.exception.UrlNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class UrlNotFoundExceptionHandler {

    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<UrlNotFoundResponseDTO> handleUrlNotFoundException(UrlNotFoundException ex) {
        UrlNotFoundResponseDTO responseDTO = new UrlNotFoundResponseDTO(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                Instant.now().toEpochMilli()
        );
        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }
}
