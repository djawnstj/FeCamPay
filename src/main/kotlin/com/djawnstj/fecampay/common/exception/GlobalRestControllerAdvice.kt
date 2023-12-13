package com.djawnstj.fecampay.common.exception

import com.djawnstj.fecampay.common.api.ExceptionResponse
import jakarta.validation.ConstraintViolationException
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalRestControllerAdvice {

    @ExceptionHandler(FeCamPayException::class)
    fun globalServerExceptionHandler(e: FeCamPayException): ResponseEntity<ExceptionResponse<*>> =
        ResponseEntity(ExceptionResponse(e.errorCode.status.value(), e.errorCode, e.message, null), e.errorCode.status)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidExceptionHandler(e: MethodArgumentNotValidException): ResponseEntity<ExceptionResponse<*>> =
        FeCamPayException(ErrorCode.INVALID_INPUT_VALUE, ErrorCode.INVALID_INPUT_VALUE.message, e)
            .let { ex ->
                ResponseEntity(
                    ExceptionResponse(
                        ex.errorCode.status.value(),
                        ex.errorCode,
                        ex.message,
                        e.bindingResult.fieldErrors.associate { it.field to it.defaultMessage }
                    ),
                    ex.errorCode.status)
            }

    @ExceptionHandler(ConstraintViolationException::class)
    fun constraintViolationException(e: ConstraintViolationException): ResponseEntity<ExceptionResponse<*>> =
        FeCamPayException(ErrorCode.INVALID_INPUT_VALUE, ErrorCode.INVALID_INPUT_VALUE.message, e)
            .let { ex ->
                ResponseEntity(
                    ExceptionResponse(
                    ex.errorCode.status.value(),
                    ex.errorCode,
                    ex.message,
                    e.constraintViolations.associate { (it.propertyPath.last()) to it.message }
                ),
                ex.errorCode.status)
            }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadableExceptionHandler(e: HttpMessageNotReadableException): ResponseEntity<ExceptionResponse<*>> =
        FeCamPayException(ErrorCode.NO_CONTENT_HTTP_BODY, ErrorCode.NO_CONTENT_HTTP_BODY.message, e)
            .let { ResponseEntity(ExceptionResponse(it.errorCode.status.value(), it.errorCode, it.message, null), it.errorCode.status) }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun httpRequestMethodNotSupportedExceptionHandler(e: HttpRequestMethodNotSupportedException): ResponseEntity<ExceptionResponse<*>> =
        FeCamPayException(ErrorCode.NOT_SUPPORTED_METHOD, ErrorCode.NOT_SUPPORTED_METHOD.message, e)
            .let { ResponseEntity(ExceptionResponse(it.errorCode.status.value(), it.errorCode, it.message, null), it.errorCode.status) }

    @ExceptionHandler(Throwable::class)
    fun throwableHandler(t: Throwable): ResponseEntity<ExceptionResponse<*>> =
        FeCamPayException(ErrorCode.INTERNAL_SEVER_ERROR, cause = t)
            .let { ResponseEntity(ExceptionResponse(it.errorCode.status.value(), it.errorCode, it.message, null), it.errorCode.status) }

}