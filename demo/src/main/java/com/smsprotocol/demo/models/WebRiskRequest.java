package com.smsprotocol.demo.models;

import java.util.List;

public record WebRiskRequest(
   String url,
   List<String> threatTypes,
   boolean allowScan
) {}
