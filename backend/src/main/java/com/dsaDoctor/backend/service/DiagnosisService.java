package com.dsaDoctor.backend.service;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.Message;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;
import com.dsaDoctor.backend.model.DiagnosisRequest;
import com.dsaDoctor.backend.model.DiagnosisResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DiagnosisService {

    @Value("${anthropic.api.key}")
    private String apiKey;

    private AnthropicClient client;
    private ObjectMapper objectMapper;

    private final PromptBuilderService promptBuilder;

    public DiagnosisService(PromptBuilderService promptBuilder) {
        this.promptBuilder = promptBuilder;
    }

    @PostConstruct
    public void init() {
        client = AnthropicOkHttpClient.builder()
                .apiKey(apiKey)
                .build();

        objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(
                PropertyNamingStrategies.SNAKE_CASE
        );
    }

    public DiagnosisResult diagnose(DiagnosisRequest request) {
        try {
            String prompt = promptBuilder.build(request);

            MessageCreateParams params = MessageCreateParams.builder()
                    .model(Model.CLAUDE_SONNET_4_6)
                    .maxTokens(1024L)
                    .addUserMessage(prompt)
                    .build();

            Message message = client.messages().create(params);

            String rawJson = message.content().stream()
                    .filter(block -> block.isText())
                    .map(block -> block.asText().text())
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No text in AI response"));

            rawJson = rawJson.trim();
            if (rawJson.startsWith("```")) {
                rawJson = rawJson.replaceAll("```json|```", "").trim();
            }

            return objectMapper.readValue(rawJson, DiagnosisResult.class);

        } catch (Exception e) {
            throw new RuntimeException("Diagnosis failed: " + e.getMessage(), e);
        }
    }
}