# DSA Doctor 🩺

An AI-powered tool that diagnoses exactly what's wrong with your DSA thinking — not just your code.

Built with Java + Spring Boot backend and React + Vite frontend, powered by Claude (Anthropic).

---

## The Problem It Solves

Most DSA tools tell you "wrong answer." DSA Doctor tells you:
- **Why** your thinking went wrong
- **Which pattern** you failed to recognise
- **What prerequisites** you're missing
- **Exactly what to practice** to fix it — in order

---

## Features

- **Thought Process Analyzer** — paste what you were thinking, not just your code. The AI diagnoses your mental model, not just your syntax.
- **Mistake Classifier** — categorises your error: Pattern Recognition Failure, Complexity Awareness Failure, Edge Case Failure, etc.
- **Skill Assessment** — scores your weak areas (Sliding Window: 15%, HashMap: 40%)
- **Personalised Prescription** — ordered list of problems to solve, with estimated time and why each one helps
- **Pattern Tips** — the key mental triggers to recognise a pattern next time

---

## Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java 21, Spring Boot 3.3 |
| AI | Anthropic Java SDK (`anthropic-java`), Claude Sonnet |
| Frontend | React, Vite |
| Database | PostgreSQL *(coming soon)* |
| Cache | Redis *(coming soon)* |
| Build | Maven |
| Deploy | Docker Compose *(coming soon)* |

---

## Project Structure

```
dsa-doctor/
├── backend/
│   └── src/main/java/com/dsadoctor/backend/
│       ├── controller/
│       │   └── DiagnosisController.java   # POST /api/diagnose
│       ├── service/
│       │   ├── DiagnosisService.java      # Calls Anthropic API
│       │   └── PromptBuilderService.java  # Builds the AI prompt
│       ├── model/
│       │   ├── DiagnosisRequest.java      # Input shape
│       │   └── DiagnosisResult.java       # Output shape
│       └── BackendApplication.java
├── frontend/                              # React + Vite (in progress)
└── README.md
```

---

## Getting Started

### Prerequisites

- Java 21
- Maven
- Node.js 20+ (for frontend)
- Anthropic API key — get one at [console.anthropic.com](https://console.anthropic.com)

### Backend Setup

```bash
cd backend
```

Add your API key to `src/main/resources/application.properties`:

```properties
spring.application.name=dsa-doctor
server.port=8080
spring.jackson.serialization.indent-output=true
anthropic.api.key=sk-ant-api03-your-key-here
```

Run the app:

```bash
./mvnw spring-boot:run
```

Verify it's running:

```
GET http://localhost:8080/api/health
→ "DSA Doctor is running"
```

### Frontend Setup *(in progress)*

```bash
cd frontend
npm install
npm run dev
```

Open [http://localhost:5173](http://localhost:5173)

---

## API Reference

### `POST /api/diagnose`

**Request body:**

```json
{
  "problem": "Longest Substring Without Repeating Characters",
  "userCode": "for i in range(len(s)): for j in range(i+1)...",
  "userThinking": "I tried nested loops to check every substring. I used a set to detect duplicates. It works but it is O(n²) and too slow. I could not figure out how to avoid the inner loop.",
  "attempts": 2,
  "timeSpent": "45 min"
}
```

**Response:**

```json
{
  "confidenceScore": 65,
  "maturityLevel": "Beginner-Intermediate",
  "mistakeType": "Pattern Recognition Failure",
  "missingPatterns": ["Sliding Window", "HashMap"],
  "issues": [
    {
      "emoji": "❌",
      "title": "Brute force identified but no optimisation path found",
      "explanation": "You correctly spotted O(n²) but did not know how to reduce it. The sliding window pattern is not yet in your toolkit.",
      "category": "Pattern Recognition Failure"
    }
  ],
  "thinkingAnalysis": [
    {
      "userSaid": "I tried nested loops",
      "diagnosisLabel": "Complexity Awareness Gap",
      "whatTheyNoticed": "You recognised a need to check pairs",
      "whatTheyMissed": "A dynamic window can replace the inner loop entirely",
      "betterPattern": "Sliding Window",
      "commonConfusion": "Brute Force vs Window Technique"
    }
  ],
  "skillScores": [
    { "name": "Sliding Window", "score": 15 },
    { "name": "HashMap / Set", "score": 40 },
    { "name": "Two Pointers", "score": 30 }
  ],
  "missingConcepts": ["Sliding window technique", "HashMap frequency tracking"],
  "prescription": {
    "doctorsNote": "Your brute force instinct is solid. The gap is your pattern library...",
    "practicePlan": [
      { "order": 1, "problem": "Maximum Sum Subarray of Size K", "why": "Builds the window intuition", "difficulty": "Easy" },
      { "order": 2, "problem": "Maximum Consecutive Ones III", "why": "Introduces expanding/contracting window", "difficulty": "Medium" },
      { "order": 3, "problem": "Fruits Into Basket", "why": "Window + HashMap together", "difficulty": "Medium" },
      { "order": 4, "problem": "Longest Substring Without Repeating Characters", "why": "The original problem — now you will see it", "difficulty": "Medium" }
    ],
    "estimatedTime": "3-4 days",
    "timeNote": "1 problem per day with reflection",
    "targetAfter": "Sliding Window: 75% mastery",
    "patternTips": [
      "When you see longest/shortest subarray — think sliding window first",
      "When you need element frequency — reach for HashMap before nested loops"
    ]
  }
}
```

---

## Roadmap

- [x] Backend — Spring Boot + Anthropic API
- [x] Prompt engineering — thought process analysis
- [x] Structured JSON diagnosis response
- [ ] React frontend — 4-screen flow
- [ ] PostgreSQL — save diagnosis history
- [ ] Skill graph — track weak nodes over time
- [ ] Progress tracker — see scores improve after prescription
- [ ] Docker Compose — one command local setup
- [ ] Deploy — Railway (backend) + Vercel (frontend)

---

## Important — API Key Security

Never commit your API key to git. Before pushing to GitHub:

1. Add `application.properties` to `.gitignore`
2. Use environment variables in production:

```properties
anthropic.api.key=${ANTHROPIC_API_KEY}
```

---

## Author

**Mohit Soni** — Senior Software Engineer  
[github.com/MGSoni](https://github.com/MGSoni) · [linkedin.com/in/mohitgauravsoni](https://linkedin.com/in/mohitgauravsoni)
