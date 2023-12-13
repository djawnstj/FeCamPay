package com.djawnstj.fecampay.store.service

import com.djawnstj.fecampay.common.exception.ErrorCode
import com.djawnstj.fecampay.common.exception.FeCamPayException
import com.djawnstj.fecampay.store.dto.StorePaymentRequestDto
import com.djawnstj.fecampay.store.entity.StorePaymentRequest
import com.djawnstj.fecampay.store.repository.StorePaymentRequestQueryRepository
import com.djawnstj.fecampay.store.repository.StoreRepository
import org.springframework.stereotype.Service

@Service
class StoreService(
    private val storeRepository: StoreRepository,
    private val storePaymentRequestQueryRepository: StorePaymentRequestQueryRepository
) {

    fun getPaymentRequestInfo(storeId: Long) : StorePaymentRequestDto {
        val paymentRequest = storePaymentRequestQueryRepository.findByStoreId(storeId).isValid()
        return StorePaymentRequestDto.of(paymentRequest)
    }

    private fun StorePaymentRequest?.isValid(): StorePaymentRequest {
        if (this == null) throw FeCamPayException(ErrorCode.PAYMENT_REQUEST_NOT_FOUND)
        return this
    }

}
