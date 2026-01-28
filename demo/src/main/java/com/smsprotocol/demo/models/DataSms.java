package com.smsprotocol.demo.models;

public record DataSms(
    String sender,
    String recipient,
    String message
) {}