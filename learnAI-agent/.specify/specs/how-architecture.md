# How Architecture — LearnAgenticAI Android App
## Technical Blueprint & System Design

**Document Owner**: Tech Lead Agent (`agent/tech-lead`)
**Last Updated**: 2026-06-30
**Status**: APPROVED — Implementation Ready

---

## 1. Architecture Overview

### Pattern: MVVM with Base Classes + Clean Architecture (3-Layer)
- **Mandatory Base Classes**: All ViewModels must extend `BaseViewModel`. Repositories extend `BaseRepository`. Data sources extend `BaseDataSource`.
- **API Responses**: All API responses must be wrapped in `BaseDto<T>`.
- **Design System**: `AvatarTheme` and `Ava`-prefixed components for UI. `Interphases` font family is mandatory.
- **Injection & Processing**: Hilt 2.57.1 with KSP for annotation processing.

```
┌─────────────────────────────────────────────────────────┐
│                   PRESENTATION LAYER                      │
│  Composables (UI) ◄──── ViewModel ◄──── UiState          │
│  No business logic. Observes StateFlow. Fires events.    │
├─────────────────────────────────────────────────────────┤
│                     DOMAIN LAYER                          │
│  UseCases ◄──── Domain Models (pure Kotlin, no Android)  │
│  Business logic lives here. No framework dependencies.   │
├─────────────────────────────────────────────────────────┤
│                      DATA LAYER                           │
│  Repositories ◄── Room DB (local) + Retrofit (remote)    │
│  Single source of truth. Abstracts data sources.         │
└─────────────────────────────────────────────────────────┘
```

### Unidirectional Data Flow (UDF)

```
User Event → ViewModel.onEvent() → UseCase → Repository
                                                  │
Composable ◄── collectAsStateWithLifecycle ◄── StateFlow<UiState>
```

---

## 2. Module Structure & Build System

### Build System Requirements
- **Gradle**: Kotlin DSL (`*.gradle.kts`), AGP 8.12.2.
- **SDK Versions**: Compile SDK 36, Min 24, Target 35.
- **Version Catalogs**: `config.versions.toml`, `dependency.versions.toml`, `learnai.versions.toml`.
- **Convention Plugins**: Custom plugins located in the `buildLogic/` module (e.g., `learn-ai-android-app-plugin`, `learn-ai-android-feature`).
- **Repositories**: Public Maven Repository.
- **Flavors**: Build properties injected per flavor via `buildProperties/dev.properties`, `uat.properties`, `prod.properties`.

### Module Layout

```
:app                    → Application entry point, DI setup, Navigation
:feature:home           → Home Dashboard screen
:feature:explore        → Module list & search
:feature:reader         → Module detail reader + quiz
:feature:glossary       → Glossary list & detail
:feature:profile        → User profile & settings
:core:ui                → Shared Composables, Theme, Typography
:core:data              → Repository implementations, Room, Retrofit
:core:domain            → UseCases, Domain Models (pure Kotlin)
:core:common            → Extensions, Utils, Constants
```

---

## 3. Data Models

### Domain Models (`:core:domain`)

```kotlin
data class LearningModule(
    val id: String,
    val title: String,
    val difficulty: DifficultyLevel,
    val estimatedMinutes: Int,
    val sections: List<ModuleSection>,
    val quiz: Quiz?,
    val isCompleted: Boolean,
    val progressPercent: Float
)

enum class DifficultyLevel { EASY, MEDIUM, HARD }

data class GlossaryTerm(
    val id: String,
    val term: String,
    val shortDefinition: String,
    val longDescription: String,
    val relatedTermIds: List<String>
)

data class UserProgress(
    val modulesCompleted: Int,
    val currentStreak: Int,
    val totalMinutesLearned: Int,
    val lastAccessedModuleId: String?,
    val badges: List<Badge>
)
```

### UiState Pattern (per Feature)

```kotlin
// Example: HomeUiState
sealed interface HomeUiState {
    object StateInitial : HomeUiState
    object Loading : HomeUiState
    data class Success(
        val greeting: String,
        val progressPercent: Float,
        val featuredModules: List<LearningModule>,
        val continueModule: LearningModule?
    ) : HomeUiState
    data class Failed(val message: String) : HomeUiState
}
```

---

## 4. Navigation Architecture

**Technology**: `androidx.navigation:navigation-compose`

```kotlin
// NavGraph Routes
sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object Home : Screen("home")
    object Explore : Screen("explore")
    object ModuleDetail : Screen("module/{moduleId}") {
        fun createRoute(id: String) = "module/$id"
    }
    object Quiz : Screen("quiz/{moduleId}")
    object Glossary : Screen("glossary")
    object GlossaryDetail : Screen("glossary/{termId}")
    object Profile : Screen("profile")
}
```

---

## 5. Dependency Injection (Hilt)

All DI is managed by Hilt. Module scoping:

| Scope | Usage |
|-------|-------|
| `@Singleton` | Repositories, Room DB, Retrofit instance |
| `@ViewModelScoped` | UseCases injected into ViewModels |
| `@ActivityRetainedScoped` | Shared state between fragments (not used in Compose-only) |

---

## 6. Local Database (Room) — Caching Layer

**Entities**: `ModuleEntity`, `GlossaryTermEntity`, `UserProgressEntity`, `QuizResultEntity`

```sql
-- Module completion and offline caching
CREATE TABLE module_progress (
    module_id TEXT PRIMARY KEY,
    is_completed INTEGER NOT NULL DEFAULT 0,
    progress_percent REAL NOT NULL DEFAULT 0.0,
    cached_content TEXT, -- JSON snapshot of the module from backend
    last_accessed INTEGER  -- Unix timestamp
);
```

---

## 7. Network Layer (Retrofit) — Online Backend API

**Tech Stack**: Retrofit 3.0.0 (Gson Converter), OkHttp 5.3.2 Interceptors, Chucker 4.2.0 (debug builds only).
All responses wrapped in `BaseDto<T>`.
The frontend strictly fetches reading materials and quizzes from the Spring Boot API.

```kotlin
interface LearningApiService {
    // Fetches modules grouped by Easy, Medium, Hard
    @GET("api/v1/modules/level/{level}")
    suspend fun getModulesByLevel(@Path("level") level: String): Response<BaseDto<List<ModuleDto>>>

    // Fetches the specific module reading material and its interactive quiz JSON array
    @GET("api/v1/modules/{id}")
    suspend fun getModuleById(@Path("id") id: String): Response<BaseDto<ModuleDetailDto>>
    
    // Explicitly fetch only the quiz for a module
    @GET("api/v1/modules/{id}/quiz")
    suspend fun getQuizForModule(@Path("id") id: String): Response<BaseDto<List<QuizQuestionDto>>>
}
```

**Base URL**: Defined in `BuildConfig.API_BASE_URL` (injected via Gradle).

---

## 8. Security Architecture

| Concern | Solution |
|---------|----------|
| API Keys | `local.properties` (dev) + GitHub Secrets (CI) + `BuildConfig` injection |
| HTTPS enforcement | `network_security_config.xml` with `cleartextTrafficPermitted="false"` |
| Data at rest | Room DB on internal storage (not world-readable) |
| ProGuard/R8 | Enabled for release builds; rules in `proguard-rules.pro` |
| Root detection | Checked at app launch; user warned (not blocked for MVP) |

---

## 9. Permissions (Justified)

| Permission | Justification | Module |
|-----------|--------------|--------|
| `INTERNET` | Future: fetching updated content | Backend Agent |
| `VIBRATE` | Quiz answer haptic feedback | Frontend Agent |

No location, camera, microphone, or contact permissions are requested.

---

## 10. CI/CD Architecture

```
Push to PR branch
      │
      ▼
[GitHub Actions: ci.yml]
├── Lint (Detekt + Ktlint)
├── Unit Tests (JVM, ./gradlew test)
├── Code Coverage check (≥80%)
└── Build APK (debug)
      │
      ▼ (on merge to main)
[GitHub Actions: release.yml]
├── Build Release APK (signed)
├── Run Instrumented Tests (Firebase Test Lab)
├── OWASP Dependency Check
└── Upload to Play Store (internal track)
```

---

## 11. Backend Service Architecture (Spring Boot)

**Tech Stack**: Java 21, Spring Boot 3.x (WebFlux), Spring Data JPA, Oracle DB, Maven.

### Reactive Layering
- **Controller Layer**: `@RestController` returning `Mono<T>` or `Flux<T>`.
- **Service Layer**: Implements `BaseUseCase` using the Template Method pattern (Validate -> Process -> Format).
- **Data Source (MVP)**: For Sprint 1, to keep things simple, the learning materials and quizzes will be served from **static JSON files** embedded in the Spring Boot `resources/` folder. The Oracle DB integration (JPA) is scaffolded but not strictly required for serving the static reading content yet.
- **Blocking Safety**: All JPA calls (when used) MUST be wrapped in `subscribeOn(Schedulers.boundedElastic())` to prevent blocking the Netty event loop.

### Observability & Standards
- **Logging**: ECS Structured Logging via `CommonLogger` for all major workflow steps.
- **Payloads**: All responses are wrapped in a generic `BaseResponse<T>`.
- **Shared Libraries**: The backend strictly imports common logic (Security, Logging, Base Classes) from an internal Maven BOM to prevent duplication.
