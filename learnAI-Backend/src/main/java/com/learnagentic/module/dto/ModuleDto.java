package com.learnagentic.module.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO representing a Learning Module returned from the API.
 * Matches the GET /api/v1/modules/level/{level} and GET /api/v1/modules/{id} contracts.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModuleDto {
    private String id;
    private String title;
    private String level;         // EASY, MEDIUM, HARD
    private int estimatedMinutes;
    private String description;
    private List<SectionDto> sections;
}

