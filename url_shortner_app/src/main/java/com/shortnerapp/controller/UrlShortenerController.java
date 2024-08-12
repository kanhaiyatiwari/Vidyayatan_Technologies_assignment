package com.shortnerapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.shortnerapp.modles.ShortenUrlRequest;
import com.shortnerapp.services.UrlShortenerService;

@RestController
@CrossOrigin(origins = "*")
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody ShortenUrlRequest longUrl) {
        String shortUrl = urlShortenerService.shortenUrl(longUrl.getLongUrl());
        return ResponseEntity.ok(shortUrl);
    }

    @RequestMapping(value = "/{shortUrl}", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<?> handleDynamicMethods(@PathVariable String shortUrl,
                                                  @RequestBody(required = false) String body,
                                                  @RequestHeader HttpHeaders headers,
                                                  HttpMethod method) {
        String longUrl = urlShortenerService.getLongUrl(shortUrl)
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "URL not found or expired"));

   
        if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
        	 HttpHeaders headersne = new HttpHeaders();
            return forwardRequest(longUrl, body, headersne, method);
        } else {
           
        	System.out.println(method);
        	return forwardRequest(longUrl, body, headers, method);
        }
    	
    	
    }

    @PostMapping("/update")
    public ResponseEntity<Boolean> updateShortUrl(@RequestParam String shortUrl, @RequestParam String newLongUrl) {
        boolean updated = urlShortenerService.updateShortUrl(shortUrl, newLongUrl);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/update-expiry")
    public ResponseEntity<Boolean> updateExpiry(@RequestParam String shortUrl, @RequestParam long daysToAdd) {
        boolean updated = urlShortenerService.updateExpiry(shortUrl, daysToAdd);
        return ResponseEntity.ok(updated);
    }

    private ResponseEntity<?> forwardRequest(String longUrl, String body, HttpHeaders headers, HttpMethod method) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers); 

        return restTemplate.exchange(longUrl, method, requestEntity, String.class);
    }
}
