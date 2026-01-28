package com.smsprotocol.demo.service;

public interface PhishingScanner {
    default boolean isSafe(String url) {
        return false;
    }

}
