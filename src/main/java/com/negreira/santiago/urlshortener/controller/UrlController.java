package com.negreira.santiago.urlshortener.controller;

import com.negreira.santiago.urlshortener.dto.ShortenUrlRequestDTO;
import com.negreira.santiago.urlshortener.dto.ShortenUrlResponseDTO;
import com.negreira.santiago.urlshortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;

    @GetMapping("/{shortUrl}")
    public RedirectView redirectToOriginalUrl(@PathVariable String shortUrl) {
        String originalUrl = urlService.getOriginalUrl(shortUrl);
        return new RedirectView(originalUrl);
    }

    @PostMapping(value = "/shorten", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ShortenUrlResponseDTO shortenUrl(@RequestBody ShortenUrlRequestDTO dto) {
        String shortUrl = urlService.shortenUrl(dto);
        return new ShortenUrlResponseDTO(shortUrl);
    }

}
