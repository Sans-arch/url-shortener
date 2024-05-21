package com.negreira.santiago.urlshortener.exception;

public class UrlNotFoundException extends RuntimeException {
    public UrlNotFoundException(String shortUrl) {
        super("Short URL not found: " + shortUrl);
    }
}
