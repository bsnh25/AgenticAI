package com.learnagentic.common.usecase;

import reactor.core.publisher.Mono;

/**
 * Template Method base class for all service use cases.
 * Per the skill mandate: enforce Validate → Process → Format pipeline.
 *
 * @param <REQ> Request type
 * @param <RES> Response type
 */
public abstract class BaseUseCase<REQ, RES> {

    /**
     * Template method that locks the execution sequence.
     * Subclasses implement validate() and process() only.
     */
    public Mono<RES> execute(REQ request) {
        return Mono.just(request)
                .doOnNext(this::validate)           // 1. Validation
                .flatMap(this::process)             // 2. Business Logic
                .doOnSuccess(res -> logSuccess(request, res))   // 3. ECS Logging
                .doOnError(err -> logError(request, err));
    }

    /**
     * Validate the incoming request. Throw an exception to reject.
     */
    protected abstract void validate(REQ request);

    /**
     * Execute the core business logic and return a reactive result.
     */
    protected abstract Mono<RES> process(REQ request);

    protected void logSuccess(REQ request, RES response) {
        // TODO: Replace with CommonLogger ECS format when shared library is available
        System.out.printf("[SUCCESS] UseCase=%s%n", this.getClass().getSimpleName());
    }

    protected void logError(REQ request, Throwable error) {
        // TODO: Replace with CommonLogger ECS format when shared library is available
        System.err.printf("[ERROR] UseCase=%s message=%s%n", this.getClass().getSimpleName(), error.getMessage());
    }
}
