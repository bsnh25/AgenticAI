package com.learnagentic.core.data.network.dto

import com.google.gson.annotations.SerializedName
import com.learnagentic.core.domain.model.DifficultyLevel
import com.learnagentic.core.domain.model.LearningModule
import com.learnagentic.core.domain.model.ModuleSection

data class ModuleDto(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("difficulty") val difficulty: String,
    @SerializedName("estimatedMinutes") val estimatedMinutes: Int,
    @SerializedName("description") val description: String,
    @SerializedName("sections") val sections: List<SectionDto>?
)

data class SectionDto(
    @SerializedName("type") val type: String,
    @SerializedName("content") val content: String
)

fun ModuleDto.toDomain(): LearningModule {
    return LearningModule(
        id = this.id,
        title = this.title,
        difficulty = try {
            DifficultyLevel.valueOf(this.difficulty.uppercase())
        } catch (e: Exception) {
            DifficultyLevel.EASY
        },
        estimatedMinutes = this.estimatedMinutes,
        description = this.description,
        sections = this.sections?.map { ModuleSection(it.type, it.content) } ?: emptyList(),
        isCompleted = false,
        progressPercent = 0f
    )
}
