# Project Context Memory

## Project Name
**LearnAgenticAI** — Android Mobile App (Jetpack Compose)

## Purpose
An educational Android application that teaches users the fundamentals of Agentic AI — how autonomous agents think, plan, and act. This app serves a dual purpose: delivering learning content to users AND acting as a live training ground for multi-agent engineering workflows.

## Created
2026-06-30

## Active Conversation ID
f23b3e25-f566-4143-9659-cfb12b19d4c5

## Tech Stack
- **Language**: Kotlin 2.2.10 (Compile SDK 36, Min 24, Target 35, AGP 8.12.2)
- **Build System**: Gradle Kotlin DSL, 3 version catalogs (config, dependency, swmp), buildLogic convention plugins, Private Nexus
- **UI Framework**: Jetpack Compose (BOM 2024.09.00, Material 3), Navigation Compose 2.9.3, Avatar Design System, Interphases font
- **Architecture**: MVVM (BaseViewModel, BaseRepository, BaseDataSource), Hilt 2.57.1, KSP
- **State Management**: UiState pattern (StateInitial / Loading / Success / Failed)
- **Networking**: Retrofit 3.0.0 (Gson), OkHttp 5.3.2, Chucker 4.2.0, BaseDto<T> wrapper
- **Local Storage**: Room Database
- **Backend Stack**: Java 21, Spring Boot 3 WebFlux, Spring Data JPA, Oracle DB
- **Testing**: JUnit5, Espresso, Compose UI Test, Mockito/MockK
- **CI/CD**: GitHub Actions
- **Security Scanning**: Detekt, OWASP Dependency Check

## Multi-Agent Swarm
| Agent | Role | Primary Artifact |
|-------|------|-----------------|
| PM Agent | Requirements & Backlog | `what-vision.md` |
| Tech Lead Agent | Architecture & Orchestration | `how-architecture.md` |
| Frontend Agent | Kotlin + Compose UI | `learnAI-Mobile/app/src/main/` |
| Backend Agent | APIs & Database | `backend/`, Room schema |
| DevOps Agent | CI/CD & Containers | `.github/`, `Dockerfile` |
| SDET Agent | Tests & QA | `*Test.kt`, `*InstrumentedTest.kt` |
| Security Agent | Reviews & Hardening | `hooks/`, Security Reports |
