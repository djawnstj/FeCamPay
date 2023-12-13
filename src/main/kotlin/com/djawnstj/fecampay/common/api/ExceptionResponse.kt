package com.djawnstj.fecampay.common.api

import com.djawnstj.fecampay.common.exception.ErrorCode
import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpStatus

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ExceptionResponse<T>(
    val statusCode: Int = HttpStatus.OK.value(),
    val errorCode: ErrorCode,
    val message: String,
    val data: T
) {
}