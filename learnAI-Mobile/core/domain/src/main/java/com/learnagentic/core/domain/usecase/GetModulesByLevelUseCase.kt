package com.learnagentic.core.domain.usecase

import com.learnagentic.core.domain.model.DifficultyLevel
import com.learnagentic.core.domain.model.LearningModule
import com.learnagentic.core.domain.repository.ModuleRepository
import com.learnagentic.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetModulesByLevelUseCase @Inject constructor(
    private val repository: ModuleRepository
) {
    operator fun invoke(level: DifficultyLevel): Flow<Resource<List<LearningModule>>> {
        return repository.getModulesByLevel(level)
    }
}
