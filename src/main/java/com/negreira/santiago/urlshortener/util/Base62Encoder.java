package com.negreira.santiago.urlshortener.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.SecureRandom;

public class Base62Encoder {
    private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final SecureRandom random = new SecureRandom();

    private Base62Encoder() {
    }

    public static String generateShortUrl(String originalUrl) {
        String saltedUrl = originalUrl + System.currentTimeMillis() + random.nextInt();
        String sha256hex = DigestUtils.sha256Hex(saltedUrl);
        String truncatedHash = sha256hex.substring(0, 6);
        return encodeBase62(truncatedHash.getBytes());
    }

    private static String encodeBase62(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            int value = b & 0xFF; // Convertendo byte para inteiro positivo
            while (value > 0) {
                sb.append(BASE62.charAt(value % 62));
                value /= 62;
            }
        }
        return sb.toString();
    }

}
