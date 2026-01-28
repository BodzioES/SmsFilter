package com.smsprotocol.demo.service;

import com.smsprotocol.demo.models.ConfidenceLevel;
import org.springframework.stereotype.Service;

@Service
public class PhishingMockScanner implements PhishingScanner {

    //  Function that checks whether a given link i a threat or not

    @Override
    public boolean isSafe(String url){
        System.out.println("I check the link on Google: " + url);

        if (url != null && url.contains("m-bonk.pl.ng")){
            System.out.println("Threat has been detected: " + ConfidenceLevel.VERY_HIGH);
            return false;
        }

        System.out.println("Clear link: " + ConfidenceLevel.SAFE);
        return true;
    }
}
