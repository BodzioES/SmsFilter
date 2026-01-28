package com.smsprotocol.demo.service;

import com.smsprotocol.demo.models.DataSms;
import com.smsprotocol.demo.models.SubscriptionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) //  Mockito turn on
public class SmsServiceTest {

    @Mock
    private SubscriptionRepository repository;  //  Pretend database

    @Mock
    private PhishingScanner scanner;    //  Pretend scanner

    @InjectMocks
    private SmsService service; //  Tested service (with injected fake dependencies)

    @Test
    void addUserOnStartCommand() {
        // Prepare
        DataSms sms = new DataSms("123", "SYSTEM", "START");

        //  Action
        service.processSms(sms);

        //  Check whether the service told the repository to save the number "123"
        verify(repository, times(1)).add("123");
    }

    @Test
    void checkLinkIfUserIsProtected() {
        DataSms sms = new DataSms("777", "100", "Click this http://virus.pl");

        when(repository.isProtected("100")).thenReturn(true);

        when(scanner.isSafe(anyString())).thenReturn(false);

        service.processSms(sms);

        //  Check if the scanner has been used
        verify(scanner, times(1)).isSafe("http://virus.pl");
    }

    @Test
    void ignoreSmsIfUserNotProtected() {
        DataSms sms = new DataSms("777", "200", "Click this http://virus.pl");

        //  Number 200 is not protected
        when(repository.isProtected("200")).thenReturn(false);

        service.processSms(sms);

        //  Scanner is not used
        verify(scanner, never()).isSafe(anyString());
    }
}
