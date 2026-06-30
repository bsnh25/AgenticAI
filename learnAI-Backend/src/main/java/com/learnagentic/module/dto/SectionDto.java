package com.learnagentic.module.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for a section (paragraph/block) within a module's reading material.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SectionDto {
    private String type;      // e.g., "text", "heading", "bullet"
    private String content;
}
