package com.learnagentic.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO representing a single quiz question with multiple choice options.
 * Per the spec: the correct answer is included in the JSON for client-side validation.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizQuestionDto {
    private String id;
    private String question;
    private List<String> options;
    private int correctAnswerIndex;   // 0-based index of the correct option
    private String explanation;       // Explanation shown after the user answers
}
