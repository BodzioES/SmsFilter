package com.smsprotocol.demo.models;

import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class SubscriberList implements SubscriptionRepository {
    private final Set<String> protectedNumbers = ConcurrentHashMap.newKeySet();

    @Override
    public boolean isProtected(String number){
        return protectedNumbers.contains(number);
    }

    @Override
    public void add(String number){
        protectedNumbers.add(number);
    }

    @Override
    public void remove(String number){
        protectedNumbers.remove(number);
    }
}
