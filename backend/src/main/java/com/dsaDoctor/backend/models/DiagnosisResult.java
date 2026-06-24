package com.dsaDoctor.backend.models;

import lombok.Data;
import java.util.List;

@Data
public class DiagnosisResult {

    private Integer confidenceScore;
    private String maturityLevel;
    private String mistakeType;
    private List<String> missingPatterns;
    private List<Issue> issues;
    private List<ThinkingAnalysis> thinkingAnalysis;
    private List<SkillScore> skillScores;
    private List<String> missingConcepts;
    private Prescription prescription;

    @Data
    public static class Issue {
        private String emoji;
        private String title;
        private String explanation;
        private String category;
    }

    @Data
    public static class ThinkingAnalysis {
        private String userSaid;
        private String diagnosisLabel;
        private String whatTheyNoticed;
        private String whatTheyMissed;
        private String betterPattern;
        private String commonConfusion;
    }

    @Data
    public static class SkillScore {
        private String name;
        private Integer score;
    }

    @Data
    public static class Prescription {
        private String doctorsNote;
        private List<PracticeItem> practicePlan;
        private String estimatedTime;
        private String timeNote;
        private String targetAfter;
        private String targetNote;
        private List<String> patternTips;
    }

    @Data
    public static class PracticeItem {
        private Integer order;
        private String problem;
        private String why;
        private String difficulty;
    }

}