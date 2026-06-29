package com.dsaDoctor.backend.service;

import com.dsaDoctor.backend.model.DiagnosisRequest;
import org.springframework.stereotype.Service;

@Service
public class PromptBuilderService {

    public String build(DiagnosisRequest req) {

        StringBuilder prompt = new StringBuilder();

        prompt.append("You are DSA Doctor, an expert DSA mentor.\n");
        prompt.append("Analyze this student submission and return ONLY a valid JSON object.\n");
        prompt.append("No markdown, no backticks, no explanation — just raw JSON.\n\n");

        prompt.append("Problem: \"").append(req.getProblem()).append("\"\n");

        if (req.getUserCode() != null && !req.getUserCode().isBlank()) {
            prompt.append("Student code:\n").append(req.getUserCode()).append("\n\n");
        } else {
            prompt.append("Student code: not provided\n\n");
        }

        prompt.append("Student thought process: \"").append(req.getUserThinking()).append("\"\n");

        if (req.getAttempts() != null) {
            prompt.append("Attempts: ").append(req.getAttempts()).append("\n");
        }

        if (req.getTimeSpent() != null && !req.getTimeSpent().isBlank()) {
            prompt.append("Time spent: ").append(req.getTimeSpent()).append("\n");
        }

        prompt.append("\nReturn exactly this JSON structure:\n");
        prompt.append(getJsonTemplate());

        return prompt.toString();
    }

    private String getJsonTemplate() {
        return """
                {
                  "confidence_score": 65,
                  ...
                }
                """;
    }

}