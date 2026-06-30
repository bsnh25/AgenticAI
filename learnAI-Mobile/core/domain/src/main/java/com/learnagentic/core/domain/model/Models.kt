package com.learnagentic.core.domain.model

/**
 * Domain models — pure Kotlin, no Android dependencies.
 * Per architecture spec: lives in :core:domain
 */

data class LearningModule(
    val id: String,
    val title: String,
    val difficulty: DifficultyLevel,
    val estimatedMinutes: Int,
    val description: String,
    val sections: List<ModuleSection>,
    val isCompleted: Boolean = false,
    val progressPercent: Float = 0f
)

enum class DifficultyLevel { EASY, MEDIUM, HARD }

data class ModuleSection(
    val type: String,  // "heading", "text", "bullet"
    val content: String
)

data class QuizQuestion(
    val id: String,
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val explanation: String
)

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

data class Badge(val id: String, val name: String, val iconRes: String)
