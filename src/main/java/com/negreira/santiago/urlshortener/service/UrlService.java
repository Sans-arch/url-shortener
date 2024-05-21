package com.negreira.santiago.urlshortener.service;

import com.negreira.santiago.urlshortener.dto.ShortenUrlRequestDTO;
import com.negreira.santiago.urlshortener.entity.Url;
import com.negreira.santiago.urlshortener.exception.UrlNotFoundException;
import com.negreira.santiago.urlshortener.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlRepository urlRepository;
    private static final Logger logger = LoggerFactory.getLogger(UrlService.class);

    public String getOriginalUrl(String shortUrl) {
        Url existingUrl = urlRepository.findByShortUrl(shortUrl);
        if (existingUrl == null) {
            throw new UrlNotFoundException(shortUrl);
        }

        return existingUrl.getOriginalUrl();
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
