package com.learnagentic.core.data.network.api

import com.learnagentic.core.data.network.dto.BaseDto
import com.learnagentic.core.data.network.dto.ModuleDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LearningApiService {
    @GET("api/v1/modules/level/{level}")
    suspend fun getModulesByLevel(@Path("level") level: String): Response<BaseDto<List<ModuleDto>>>
}
