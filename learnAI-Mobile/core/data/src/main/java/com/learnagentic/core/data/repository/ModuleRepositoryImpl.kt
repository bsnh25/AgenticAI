package com.learnagentic.core.data.repository

import com.learnagentic.core.data.network.api.LearningApiService
import com.learnagentic.core.data.network.dto.toDomain
import com.learnagentic.core.domain.model.DifficultyLevel
import com.learnagentic.core.domain.model.LearningModule
import com.learnagentic.core.domain.repository.ModuleRepository
import com.learnagentic.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ModuleRepositoryImpl @Inject constructor(
    private val api: LearningApiService
) : ModuleRepository {

    override fun getModulesByLevel(level: DifficultyLevel): Flow<Resource<List<LearningModule>>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getModulesByLevel(level.name)
            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                if (body.status == "SUCCESS") {
                    val modules = body.data?.map { it.toDomain() } ?: emptyList()
                    emit(Resource.Success(modules))
                } else {
                    emit(Resource.Error(body.message ?: "Unknown API error"))
                }
            } else {
                emit(Resource.Error("HTTP Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Network connection failed"))
        }
    }
}
