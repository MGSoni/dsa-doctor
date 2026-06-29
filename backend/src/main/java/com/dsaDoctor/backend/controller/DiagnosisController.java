package com.dsaDoctor.backend.controller;

import com.dsaDoctor.backend.model.DiagnosisRequest;
import com.dsaDoctor.backend.model.DiagnosisResult;
import com.dsaDoctor.backend.service.DiagnosisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DiagnosisController {

    private final DiagnosisService diagnosisService;

    public DiagnosisController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("DSA Doctor is running");
    }

    @PostMapping("/diagnose")
    public ResponseEntity<DiagnosisResult> diagnose(
            @RequestBody DiagnosisRequest request) {

        DiagnosisResult result = diagnosisService.diagnose(request);
        return ResponseEntity.ok(result);
    }
}