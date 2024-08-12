package com.shortnerapp.modles;



public class ShortenUrlRequest {

    private String longUrl;

   

    public ShortenUrlRequest() {
    }

    public ShortenUrlRequest(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
