package com.djawnstj.fecampay.pay.dto.request

data class PayRequest(
    val uuid: String,
    val memberId: Long,
    val paymentAmount: Int,
    val storeId: Long,
)