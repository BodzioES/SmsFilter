package com.smsprotocol.demo.service;


//  Keeping safe url
public interface PhishingScanner {
    default boolean isSafe(String url) {
        return false;
    }

}
