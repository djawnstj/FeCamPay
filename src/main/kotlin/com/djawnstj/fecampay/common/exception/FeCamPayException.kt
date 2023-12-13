package com.djawnstj.fecampay.common.exception

class FeCamPayException @JvmOverloads constructor(
    val errorCode: ErrorCode,
    message: String? = null,
    override val cause: Throwable? = null,
): RuntimeException(message, cause) {

    override val message: String = message ?: errorCode.message

}