package com.djawnstj.fecampay.store.service

import com.djawnstj.fecampay.common.exception.ErrorCode
import com.djawnstj.fecampay.common.exception.FeCamPayException
import com.djawnstj.fecampay.store.entity.Store
import com.djawnstj.fecampay.store.entity.StorePaymentRequest
import com.djawnstj.fecampay.store.repository.StorePaymentRequestQueryRepository
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StoreServiceTest {

    private val storePaymentRequestQueryRepository: StorePaymentRequestQueryRepository = mockk()

    private val storeService: StoreService = StoreService(storePaymentRequestQueryRepository)

    @Test
    fun `결제 요청 내용을 확인한다`() {
        // given
        val storePaymentRequest = StorePaymentRequest(10000, Store("가맹점1"))
        every { storePaymentRequestQueryRepository.findByStoreId(any<Long>()) } returns storePaymentRequest

        // when
        val response = storeService.getPaymentRequestInfo(1L)

        // then
        assertThat(response)
            .extracting("paymentAmount", "storeName")
            .contains(storePaymentRequest.paymentAmount, storePaymentRequest.store.storeName)
    }

    @Test
    fun `결제 요청 내용을 확인하고자 하는 가맹점의 결제 요청이 없다면 예외를 반환한다`() {
        // given
        every { storePaymentRequestQueryRepository.findByStoreId(any<Long>()) } returns null

        // when then
        assertThatThrownBy { storeService.getPaymentRequestInfo(1L) }
            .isInstanceOf(FeCamPayException::class.java)
            .hasMessage(ErrorCode.PAYMENT_REQUEST_NOT_FOUND.message)

    }

}