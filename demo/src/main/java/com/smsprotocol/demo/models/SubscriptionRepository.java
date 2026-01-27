package com.smsprotocol.demo.models;

public interface SubscriptionRepository {
    boolean isProtected(String number);
    void add(String number);
    void remove(String number);
}
