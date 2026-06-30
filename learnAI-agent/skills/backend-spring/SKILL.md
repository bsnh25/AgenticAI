---
name: spring-boot-backend
description: >
  Standards and patterns for writing production-quality Spring Boot backend services.
  Covers: Java 21, WebFlux + JPA integration, ECS Logging, and Template Method patterns.
agents:
  - agent/backend
---

# Skill: Backend Spring Boot Standards

## Tech Stack
- **Language & Framework**: Java 21, Spring Boot 3.x (WebFlux - Reactive)
- **Database**: Spring Data JPA / Hibernate, Oracle Database
- **Tooling**: Lombok 1.18.x, Maven (with Maven Wrapper)
- **Configuration**: Spring Cloud Config (externalized configuration)
- **Observability**: ECS Structured Logging via CommonLogger

## Shared Library
- All backend microservices **must** import the *internal Shared Library* (e.g., `common-framework` via Maven).
- Use the Shared Library for recurring logic (cross-cutting concerns) such as: 
  - `CommonLogger` for the standard ECS log format.
  - Security / JWT Authentication filters.
  - Base classes for DTOs and Responses (like `BaseResponse<T>`).
- Do not duplicate common utilities (e.g., DateUtils, StringUtils). Always check if the method already exists in the Shared Library.

## Service Architecture
Use a **Layered Architecture** approach adapted for the *Reactive WebFlux* system:
1. **Controller Layer (`@RestController`)**: Responsible for receiving HTTP Requests, validating input, and returning `Mono<T>` or `Flux<T>`.
2. **Service Layer (`@Service`)**: Where the *core business logic* resides. 
   - ⚠️ **CRITICAL RULE**: Because WebFlux is *non-blocking* and JPA/Hibernate is *blocking* (using JDBC), all calls to the JPA database **must** be wrapped using specific Schedulers so they do not block the Netty Event Loop.
3. **Repository Layer (`@Repository`)**: Standard Spring Data JPA interfaces (`JpaRepository`).

## Application Bootstrap

### Inheritance Hierarchy
- **`BaseEntity`**: All database Entities must `extend` this class to standardize *auditing* fields (`createdAt`, `updatedAt`, `createdBy`, `updatedBy`).
- **`BaseResponse<T>`**: Standard wrapper for all API response payloads to the client. Must contain `status`, `message`, `timestamp`, and `data` properties.
- **`BaseException`**: Application-specific exception hierarchy that will be caught by the Global Error Handler (ControllerAdvice) to map to the correct HTTP Status Code.

### How the Template Method Works
The application of the **Template Method Pattern** is highly recommended in the Service / UseCase layer to maintain a consistent execution flow (Validation -> Process -> Format):

```java
public abstract class BaseUseCase<REQ, RES> {
    
    // Template Method that locks the execution sequence
    public Mono<RES> execute(REQ request) {
        return Mono.just(request)
            .doOnNext(this::validate)          // 1. Validation
            .flatMap(this::process)            // 2. Logic Execution
            .doOnSuccess(res -> logSuccess(request, res))  // 3. ECS Logging
            .doOnError(err -> logError(request, err));
    }

    // Abstract methods that must be implemented by subclasses
    protected abstract void validate(REQ request);
    protected abstract Mono<RES> process(REQ request);
}
```

## Steps for Creating a New Service
Whenever the Backend agent is asked to build a new API feature / endpoint, follow this standard sequence of steps:

1. **Define Entity & Repository**
   - Create an Entity class (Java 21) annotated with `@Entity` and `@Table(name = "...")`. 
   - Ensure it `extends BaseEntity`.
   - Create a repository interface `extends JpaRepository<Entity, Id>`.

2. **Create DTO (Data Transfer Object)**
   - Create a Request DTO. Utilize Lombok annotations (`@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`, `@Builder`).
   - Apply *Bean Validation* (e.g., `@NotBlank`, `@Size`, `@NotNull`).

3. **Implement Service Class**
   - Create a service class that `extends BaseUseCase<Req, Res>` (if using the Template Method).
   - Implement the business logic inside the `process()` method.
   - **Wrap DB access (JPA) with the Bounded Elastic Scheduler:**
     ```java
     protected Mono<ResponseDto> process(RequestDto req) {
         return Mono.fromCallable(() -> repository.save(entity))
                    .subscribeOn(Schedulers.boundedElastic()) // MANDATORY for JPA in WebFlux
                    .map(savedEntity -> mapToDto(savedEntity));
     }
     ```
   - Use `CommonLogger` to log important activities according to the ECS standard.

4. **Create Controller Endpoint**
   - Create a `@RestController` annotated with `@RequestMapping`.
   - Inject the service class via constructor injection (just use the `@RequiredArgsConstructor` annotation from Lombok).
   - Define API methods with a return type of `Mono<BaseResponse<Res>>`.

5. **Externalize Configuration**
   - If the new service requires parameters (like another service's URL, timeout, etc.), define the key in `application.yml` (Spring Cloud Config), and it is **strictly forbidden** to hardcode configuration values inside Java files.
