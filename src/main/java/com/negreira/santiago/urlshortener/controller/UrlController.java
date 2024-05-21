package com.negreira.santiago.urlshortener.controller;

import com.negreira.santiago.urlshortener.dto.ShortenUrlRequestDTO;
import com.negreira.santiago.urlshortener.dto.ShortenUrlResponseDTO;
import com.negreira.santiago.urlshortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Object> redirect(@PathVariable String shortUrl) {
        return urlService.getOriginalUrl(shortUrl)
                .map(url -> {
                    try {
                        return ResponseEntity.status(HttpStatus.FOUND).location(new URI(url.getOriginalUrl())).build();
                    } catch (URISyntaxException e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping(value = "/shorten", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ShortenUrlResponseDTO shortenUrl(@RequestBody ShortenUrlRequestDTO dto) {
        String shortUrl = urlService.shortenUrl(dto);
        return new ShortenUrlResponseDTO(shortUrl);
    }

}
