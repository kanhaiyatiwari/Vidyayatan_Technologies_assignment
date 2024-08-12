package com.shortnerapp.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shortnerapp.modles.ShortUrl;

public interface UrlShortenerRepository extends JpaRepository<ShortUrl, Long> {
    Optional<ShortUrl> findByShortUrl(String shortUrl);
}
