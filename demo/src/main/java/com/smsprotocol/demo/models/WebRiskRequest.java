package com.smsprotocol.demo.models;

import java.util.List;

//  Record defining what address to check and what threat it may pose

public record WebRiskRequest(
   String url,
   List<String> threatTypes,
   boolean allowScan
) {}
