package com.learnagentic.core.data.di

import com.learnagentic.core.data.network.api.LearningApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // TODO: The API_BASE_URL will be provided by BuildConfig
    // We are temporarily hardcoding it if BuildConfig is not yet available
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        // Will be replaced with BuildConfig.API_BASE_URL in a later step
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/") 
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideLearningApiService(retrofit: Retrofit): LearningApiService {
        return retrofit.create(LearningApiService::class.java)
    }
}
