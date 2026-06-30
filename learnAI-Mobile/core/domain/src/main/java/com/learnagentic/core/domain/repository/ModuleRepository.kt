package com.learnagentic.core.domain.repository

import com.learnagentic.core.domain.util.Resource
import com.learnagentic.core.domain.model.DifficultyLevel
import com.learnagentic.core.domain.model.LearningModule
import kotlinx.coroutines.flow.Flow

interface ModuleRepository {
    fun getModulesByLevel(level: DifficultyLevel): Flow<Resource<List<LearningModule>>>
}
