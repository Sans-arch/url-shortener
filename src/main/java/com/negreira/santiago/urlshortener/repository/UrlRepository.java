package com.negreira.santiago.urlshortener.repository;

import com.negreira.santiago.urlshortener.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Url findByShortUrl(String shortUrl);
    Url findByOriginalUrl(String originalUrl);
}
