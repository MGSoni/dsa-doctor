package com.dsaDoctor.backend.models;

import lombok.Data;

@Data
public class DiagnosisRequest {

    private String problem;
    private String userCode;
    private String userThinking;
    private Integer attempts;
    private String timeSpent;

}