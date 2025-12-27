# playwright-bdd-docker-kubernetes

End-to-end test automation framework using **Playwright with BDD (Cucumber)**, containerized with **Docker** and designed for execution on **Kubernetes**.

---

## 🚀 Tech Stack

- **Language:** Java  
- **Automation Tool:** Playwright  
- **BDD:** Cucumber  
- **Test Runner:** TestNG  
- **Build Tool:** Maven  
- **Containerization:** Docker  
- **Orchestration:** Kubernetes  

---

## 📁 Project Structure

```
playwright-bdd-docker-kubernetes
│
├── src
│   ├── main
│   │   └── java/com/company/qa
│   │       ├── base
│   │       │   └── Browser & context setup
│   │       ├── pages
│   │       │   ├── LoginPage
│   │       │   └── SearchPage
│   │       └── utils
│   │           ├── PropertyReader
│   │           └── CommonUtils
│   │
│   └── test
│       ├── java/com/company/qa
│       │   ├── hooks
│       │   │   └── Cucumber Hooks (@Before, @After)
│       │   ├── runners
│       │   │   └── TestNG + Cucumber Runner
│       │   └── stepdefinitions
│       │       ├── LoginSteps
│       │       └── SearchSteps
│       │
│       └── resources
│           ├── config
│           ├── features
│           └── testdata
│
├── traces/        # Playwright traces (ignored, folder kept)
├── videos/        # Execution videos (ignored, folder kept)
├── screenshots/   # Failure screenshots (ignored, folder kept)
│
├── Dockerfile
├── playwright-job.yaml
├── playwright-deployment.yaml
├── pom.xml
├── testng.xml
└── README.md
```

---

## 🔄 Overall Code Flow

1. Feature files written in Gherkin describe scenarios.
2. TestNG + Cucumber runner triggers execution.
3. Hooks initialize and tear down browser sessions.
4. Step definitions map Gherkin steps to Java code.
5. Page Objects handle UI interactions.
6. Utilities manage configs and helpers.

---

## ▶️ Run Tests Locally

### Prerequisites
- Java 11+
- Maven

### Install Playwright Browsers
```bash
mvn exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install"
```

### Run Tests
```bash
mvn clean test
```

---

## 🐳 Run Tests with Docker

```bash
docker build -t playwright-bdd:1.0 .
docker run --rm playwright-bdd:1.0
```

---

## ☸️ Run Tests on Kubernetes

### Kubernetes Job
```bash
kubectl apply -f playwright-job.yaml
kubectl logs job/playwright-job
```

### Kubernetes Deployment (Debug)
```bash
kubectl apply -f playwright-deployment.yaml
kubectl port-forward deployment/playwright-deployment 6080:6080
```

---

## 📦 Test Artifacts

| Folder | Purpose | Git |
|------|--------|-----|
| traces | Playwright traces | Ignored |
| videos | Execution videos | Ignored |
| screenshots | Failure screenshots | Ignored |

---

## 🔄 CI/CD Ready

Designed for scalable execution using Docker and Kubernetes in CI pipelines.

---

## 👤 Author

Bhargav Chirumamilla  
QA Automation | Playwright | BDD | Docker | Kubernetes
