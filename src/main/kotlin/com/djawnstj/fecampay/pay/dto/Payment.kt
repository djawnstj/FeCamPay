package com.djawnstj.fecampay.pay.dto

import com.djawnstj.fecampay.common.annotation.RedisEntity

@RedisEntity
data class Payment(
    val uuid: String,
    val memberId: Long,
    val paymentAmount: Int,
    val storeId: Long,
    var isSuccess: Boolean = false
) {
}