package com.learnagentic.quiz.controller;

import com.learnagentic.common.dto.BaseResponse;
import com.learnagentic.quiz.dto.QuizQuestionDto;
import com.learnagentic.quiz.service.GetQuizByModuleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * REST Controller for Quiz endpoints.
 * GET /api/v1/modules/{moduleId}/quiz — returns quiz questions for a module.
 */
@RestController
@RequestMapping("/api/v1/modules")
@RequiredArgsConstructor
public class QuizController {

    private final GetQuizByModuleUseCase getQuizByModuleUseCase;

    /**
     * Fetch all quiz questions for a specific module.
     * The payload includes correct answer index for client-side validation.
     *
     * @param moduleId the module identifier (e.g., "m-01")
     * @return list of quiz questions wrapped in BaseResponse
     */
    @GetMapping("/{moduleId}/quiz")
    public Mono<BaseResponse<List<QuizQuestionDto>>> getQuizForModule(@PathVariable String moduleId) {
        return getQuizByModuleUseCase.execute(moduleId);
    }
}
