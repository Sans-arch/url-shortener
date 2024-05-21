package com.negreira.santiago.urlshortener.service;

import com.negreira.santiago.urlshortener.entity.Url;
import com.negreira.santiago.urlshortener.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlRepository urlRepository;

    public String shortenUrl(String originalUrl) {
        Url existingUrl = urlRepository.findByOriginalUrl(originalUrl);
        if (existingUrl != null) {
            return existingUrl.getShortUrl();
        }

        Url url = new Url(originalUrl, generateShortUrl());
        urlRepository.save(url);

        return url.getShortUrl();
    }

    public Optional<Url> getOriginalUrl(String shortUrl) {
        return Optional.ofNullable(urlRepository.findByShortUrl(shortUrl));
    }

    private String generateShortUrl() {
        // Implement a short URL generation logic
        return "bla";
    }

}
