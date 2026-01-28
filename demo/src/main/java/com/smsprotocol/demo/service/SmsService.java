package com.smsprotocol.demo.service;

import com.smsprotocol.demo.models.DataSms;
import com.smsprotocol.demo.models.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SmsService {

    private final SubscriptionRepository subscriptionRepository;
    private final PhishingScanner phishingScanner;

    public SmsService(SubscriptionRepository subscriptionRepository, PhishingScanner phishingScanner) {
        this.subscriptionRepository = subscriptionRepository;
        this.phishingScanner = phishingScanner;
    }

    public void processSms(DataSms sms){
        String message = sms.message().trim();
        String sender = sms.sender();

        if ("START".equalsIgnoreCase(message)){
            subscriptionRepository.add(sender);
            System.out.println("Saved number: " + sender);
            return;
        }

        if ("STOP".equalsIgnoreCase(message)){
            subscriptionRepository.remove(sender);
            System.out.println("Removed number: " + sender);
            return;
        }

        if (subscriptionRepository.isProtected(sms.recipient())){
            System.out.println("Starting link scanning");

            String url = extractUrl(message);

            if (url != null){
                boolean isSafe = phishingScanner.isSafe(url);
                if (!isSafe){
                    System.out.println("SMS blocked, phishing detected");
                    return;
                }
            }
        }
        System.out.println("SMS forwarded to the recipient " + sms.recipient());
    }

    public String extractUrl(String text){
        Pattern pattern = Pattern.compile("https?://\\S+");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()){
            return matcher.group();
        }
        return null;
    }
}
