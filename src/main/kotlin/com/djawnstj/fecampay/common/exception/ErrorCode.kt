package com.djawnstj.fecampay.common.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val message: String,
) {

    // Account
    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 회원의 페이 정보를 찾을 수 없습니다."),
    INVALID_AMOUNT(HttpStatus.BAD_REQUEST, "잔액이 부족합니다."),

    // Store
    PAYMENT_REQUEST_NOT_FOUND(HttpStatus.NOT_FOUND, "결제 요청을 찾지 못했습니다."),

    // Pay
    PAYMENT_REQUEST_PROCESSING(HttpStatus.CONFLICT, "해당 결제에 대해 이전 멱등 요청이 처리중입니다."),

    // InputValidation
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "입력값이 올바르지 않습니다."),
    NO_CONTENT_HTTP_BODY(HttpStatus.BAD_REQUEST, "정상적인 요청 본문이 아닙니다."),

    // RequestError
    NOT_SUPPORTED_METHOD(HttpStatus.METHOD_NOT_ALLOWED, "정상적인 요청이 아닙니다."),

    // Common
    INTERNAL_SEVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "관리자에게 문의 바랍니다."),
    ;
}