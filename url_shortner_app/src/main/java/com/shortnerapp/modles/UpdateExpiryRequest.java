package com.shortnerapp.modles;

import lombok.Data;

@Data
public class UpdateExpiryRequest {
    private String shortUrl;
    private int daysToAdd;
  
}