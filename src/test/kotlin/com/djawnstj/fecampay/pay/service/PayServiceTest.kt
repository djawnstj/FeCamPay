package com.djawnstj.fecampay.pay.service

import com.djawnstj.fecampay.account.service.AccountService
import com.djawnstj.fecampay.common.exception.ErrorCode
import com.djawnstj.fecampay.common.exception.FeCamPayException
import com.djawnstj.fecampay.pay.dto.Payment
import com.djawnstj.fecampay.pay.dto.request.PayRequest
import com.djawnstj.fecampay.pay.repository.PaymentRedisRepository
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PayServiceTest {

    private val accountService: AccountService = mockk()
    private val paymentRedisRepository: PaymentRedisRepository = mockk()

    private val payService: PayService = PayService(accountService, paymentRedisRepository)

    @Test
    fun `결제 요청을 받아 결제를 진행한다`() {
        // given
        val request = PayRequest("uuid", 1L, 10000, 1L)

        every { paymentRedisRepository.findByUuid(any<String>()) } returns null
        every { paymentRedisRepository.save(any<Payment>()) } answers { it.invocation.args[0] as Payment }
        justRun { accountService.deductAmount(any<Int>(), any<Long>()) }

        // when
        val response = payService.pay(request)

        // then
        assertThat(response.message).isEqualTo("결제에 성공했습니다.")
    }

    @Test
    fun `결제 요청을 받았을 때 멱등한 요청이 존재하면 예외를 반환한다`() {
        // given
        val request = PayRequest("uuid", 1L, 10000, 1L)

        every { paymentRedisRepository.findByUuid(any<String>()) } returns Payment(request.uuid, request.memberId, request.paymentAmount, request.storeId)

        // when then
        assertThatThrownBy { payService.pay(request) }
            .isInstanceOf(FeCamPayException::class.java)
            .hasMessage(ErrorCode.PAYMENT_REQUEST_PROCESSING.message)
    }

}