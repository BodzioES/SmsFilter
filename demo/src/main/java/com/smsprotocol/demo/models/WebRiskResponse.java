package com.smsprotocol.demo.models;

import java.util.List;

public record WebRiskResponse(List<Score> scores) {}

record Score(String threatType, ConfidenceLevel confidenceLevel){}