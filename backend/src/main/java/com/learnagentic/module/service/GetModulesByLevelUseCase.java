package com.learnagentic.module.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnagentic.common.dto.BaseResponse;
import com.learnagentic.common.usecase.BaseUseCase;
import com.learnagentic.module.dto.ModuleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

/**
 * Use Case: Fetch learning modules by difficulty level.
 * Serves from static JSON files in resources/data/ for MVP Sprint 1.
 * Blocking file I/O is offloaded to the boundedElastic scheduler.
 */
@Service
@RequiredArgsConstructor
public class GetModulesByLevelUseCase extends BaseUseCase<String, BaseResponse<List<ModuleDto>>> {

    private final ObjectMapper objectMapper;

    @Override
    protected void validate(String level) {
        if (level == null || level.isBlank()) {
            throw new IllegalArgumentException("Level parameter must not be blank");
        }
        if (!List.of("EASY", "MEDIUM", "HARD").contains(level.toUpperCase())) {
            throw new IllegalArgumentException("Invalid level. Must be EASY, MEDIUM, or HARD");
        }
    }

    @Override
    protected Mono<BaseResponse<List<ModuleDto>>> process(String level) {
        return Mono.fromCallable(() -> {
                    var resource = new ClassPathResource("data/modules.json");
                    List<ModuleDto> allModules = objectMapper.readValue(
                            resource.getInputStream(),
                            new TypeReference<>() {}
                    );
                    List<ModuleDto> filtered = allModules.stream()
                            .filter(m -> m.getLevel().equalsIgnoreCase(level))
                            .toList();
                    return BaseResponse.success(filtered);
                })
                .subscribeOn(Schedulers.boundedElastic()); // MANDATORY: file I/O is blocking
    }
}
