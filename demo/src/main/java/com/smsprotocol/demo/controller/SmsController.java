package com.smsprotocol.demo.controller;

import com.smsprotocol.demo.service.SmsService;
import com.smsprotocol.demo.models.DataSms;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sms")
public class SmsController {
    private final SmsService smsService;

    public SmsController(SmsService smsService) {
        this.smsService = smsService;
    }

    @PostMapping
    public void receiveSms(@RequestBody DataSms dataSms){
        System.out.println("SMS received from " + dataSms.sender());
        smsService.processSms(dataSms);
    }
}
