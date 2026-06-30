package com.learnagentic.module.controller;

import com.learnagentic.common.dto.BaseResponse;
import com.learnagentic.module.dto.ModuleDto;
import com.learnagentic.module.service.GetModulesByLevelUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * REST Controller for Learning Module endpoints.
 * GET /api/v1/modules/level/{level} — returns modules filtered by difficulty.
 */
@RestController
@RequestMapping("/api/v1/modules")
@RequiredArgsConstructor
public class ModuleController {

    private final GetModulesByLevelUseCase getModulesByLevelUseCase;

    /**
     * Fetch all modules for a given difficulty level.
     *
     * @param level EASY, MEDIUM, or HARD
     * @return list of modules wrapped in BaseResponse
     */
    @GetMapping("/level/{level}")
    public Mono<BaseResponse<List<ModuleDto>>> getModulesByLevel(@PathVariable String level) {
        return getModulesByLevelUseCase.execute(level.toUpperCase());
    }
}
