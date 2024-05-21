package com.negreira.santiago.urlshortener.service;

import com.negreira.santiago.urlshortener.dto.ShortenUrlRequestDTO;
import com.negreira.santiago.urlshortener.entity.Url;
import com.negreira.santiago.urlshortener.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlRepository urlRepository;
    private static final Logger logger = LoggerFactory.getLogger(UrlService.class);

    public Optional<Url> getOriginalUrl(String shortUrl) {
        return Optional.ofNullable(urlRepository.findByShortUrl(shortUrl));
    }

    public String shortenUrl(ShortenUrlRequestDTO dto) {
        Url existingUrl = urlRepository.findByOriginalUrl(dto.getOriginalUrl());
        if (existingUrl != null) {
            logger.info("Url already exists");
            return existingUrl.getShortUrl();
        }

        Url generatedUrl = generateShortUrl(dto.getOriginalUrl());
        return generatedUrl.getShortUrl();
    }

    private Url generateShortUrl(String originalUrl) {
        Url url = new Url(originalUrl, "short-url");
        urlRepository.save(new Url(originalUrl, "short-url"));
        return url;
    }
}
