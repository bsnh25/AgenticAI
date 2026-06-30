package com.learnagentic.core.data.network.dto

import com.google.gson.annotations.SerializedName

data class BaseDto<T>(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String?,
    @SerializedName("data") val data: T?
)
