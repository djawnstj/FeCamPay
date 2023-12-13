package com.djawnstj.fecampay.store.dto

import com.djawnstj.fecampay.store.entity.StorePaymentRequest

data class StorePaymentRequestDto(
    val paymentAmount: Int,
    val storeName: String
) {

    companion object {
        fun of(entity: StorePaymentRequest) : StorePaymentRequestDto =
            StorePaymentRequestDto(entity.paymentAmount, entity.store.storeName)
    }

}
