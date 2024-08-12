package com.shortnerapp.services;


import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shortnerapp.modles.ShortUrl;
import com.shortnerapp.repository.UrlShortenerRepository;

@Service
public class UrlShortenerService {

    private static final String BASE_URL = "http://localhost:8080/";
    private static final long EXPIRY_MONTHS = 10;

    @Autowired
    private UrlShortenerRepository urlShortenerRepository;

    public String shortenUrl(String longUrl) {
        String shortUrl = generateShortUrl();
        LocalDateTime expiryDate = LocalDateTime.now().plusMonths(EXPIRY_MONTHS);

        ShortUrl shortUrlEntity = new ShortUrl();
        shortUrlEntity.setShortUrl(shortUrl);
        shortUrlEntity.setLongUrl(longUrl);
        shortUrlEntity.setExpiryDate(expiryDate);

        urlShortenerRepository.save(shortUrlEntity);

        return BASE_URL + shortUrl;
    }

    public Optional<String> getLongUrl(String shortUrl) {
        return urlShortenerRepository.findByShortUrl(shortUrl)
            .filter(shortUrlEntity -> shortUrlEntity.getExpiryDate().isAfter(LocalDateTime.now()))
            .map(ShortUrl::getLongUrl);
    }

    public boolean updateShortUrl(String shortUrl, String newLongUrl) {
        Optional<ShortUrl> optionalShortUrl = urlShortenerRepository.findByShortUrl(shortUrl);

        if (optionalShortUrl.isPresent()) {
            ShortUrl shortUrlEntity = optionalShortUrl.get();
            shortUrlEntity.setLongUrl(newLongUrl);
            urlShortenerRepository.save(shortUrlEntity);
            return true;
        }

        return false;
    }

    public boolean updateExpiry(String shortUrl, long daysToAdd) {
        Optional<ShortUrl> optionalShortUrl = urlShortenerRepository.findByShortUrl(shortUrl);

        if (optionalShortUrl.isPresent()) {
            ShortUrl shortUrlEntity = optionalShortUrl.get();
            shortUrlEntity.setExpiryDate(shortUrlEntity.getExpiryDate().plusDays(daysToAdd));
            urlShortenerRepository.save(shortUrlEntity);
            return true;
        }

        return false;
    }

    private String generateShortUrl() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder shortUrl = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            shortUrl.append(characters.charAt(random.nextInt(characters.length())));
        }

        return shortUrl.toString();
    }
}
