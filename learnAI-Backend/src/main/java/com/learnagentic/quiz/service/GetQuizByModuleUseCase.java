package com.learnagentic.quiz.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnagentic.common.dto.BaseResponse;
import com.learnagentic.common.usecase.BaseUseCase;
import com.learnagentic.quiz.dto.QuizQuestionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.Map;

/**
 * Use Case: Fetch quiz questions for a specific module ID.
 * Reads from static quizzes.json for MVP Sprint 1.
 * Blocking file I/O is offloaded to the boundedElastic scheduler.
 */
@Service
@RequiredArgsConstructor
public class GetQuizByModuleUseCase extends BaseUseCase<String, BaseResponse<List<QuizQuestionDto>>> {

    private final ObjectMapper objectMapper;

    @Override
    protected void validate(String moduleId) {
        if (moduleId == null || moduleId.isBlank()) {
            throw new IllegalArgumentException("Module ID must not be blank");
        }
    }

    @Override
    protected Mono<BaseResponse<List<QuizQuestionDto>>> process(String moduleId) {
        return Mono.fromCallable(() -> {
                    var resource = new ClassPathResource("data/quizzes.json");
                    Map<String, List<QuizQuestionDto>> allQuizzes = objectMapper.readValue(
                            resource.getInputStream(),
                            new TypeReference<>() {}
                    );
                    List<QuizQuestionDto> questions = allQuizzes.getOrDefault(moduleId, List.of());
                    return BaseResponse.success(questions);
                })
                .subscribeOn(Schedulers.boundedElastic()); // MANDATORY: file I/O is blocking
    }
}
