package com.learnagentic.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Standard API response wrapper for all endpoints.
 * Per the architecture spec: all responses must be wrapped in BaseResponse<T>.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {

    private String status;
    private String message;
    private Instant timestamp;
    private T data;

    public static <T> BaseResponse<T> success(T data) {
        return BaseResponse.<T>builder()
                .status("SUCCESS")
                .message("Request processed successfully")
                .timestamp(Instant.now())
                .data(data)
                .build();
    }

    public static <T> BaseResponse<T> error(String message) {
        return BaseResponse.<T>builder()
                .status("ERROR")
                .message(message)
                .timestamp(Instant.now())
                .build();
    }
}
