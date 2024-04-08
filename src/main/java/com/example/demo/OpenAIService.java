package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenAIService {
    private static final String OPENAI_API_URL = "https://api.openai.com";

    @Value("${openai.api.key}")
    private String OPENAI_API_KEY;
}
