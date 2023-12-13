package com.djawnstj.fecampay.pay.service

import com.djawnstj.fecampay.account.service.AccountService
import com.djawnstj.fecampay.common.exception.ErrorCode
import com.djawnstj.fecampay.common.exception.FeCamPayException
import com.djawnstj.fecampay.pay.dto.Payment
import com.djawnstj.fecampay.pay.dto.request.PayRequest
import com.djawnstj.fecampay.pay.dto.response.PayResponse
import com.djawnstj.fecampay.pay.repository.PaymentRedisRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PayService(
    private val accountService: AccountService,
    private val paymentRedisRepository: PaymentRedisRepository
) {

    @Transactional
    fun pay(request: PayRequest): PayResponse {
        paymentRedisRepository.findByUuid(request.uuid).checkProcessingPayment()

        val payment = Payment(request.uuid, request.memberId, request.paymentAmount, request.storeId)
        paymentRedisRepository.save(payment)

        accountService.deductAmount(request.paymentAmount, request.memberId)

        payment.isSuccess = true
        paymentRedisRepository.save(payment)

        return PayResponse("결제에 성공했습니다.")
    }

    private fun Payment?.checkProcessingPayment() {
        if (this != null) throw FeCamPayException(ErrorCode.PAYMENT_REQUEST_PROCESSING)
    }

}
