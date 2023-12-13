package com.djawnstj.fecampay.pay.repository

import com.djawnstj.fecampay.IntegrationTestSupport
import com.djawnstj.fecampay.pay.dto.Payment
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate


class PaymentRedisRepositoryTest: IntegrationTestSupport() {

    @Autowired
    private lateinit var paymentRedisRepository: PaymentRedisRepository

    @Autowired
    private lateinit var paymentRedisTemplate: RedisTemplate<String, Payment>

    @AfterEach
    fun tearDown() {
        paymentRedisTemplate.connectionFactory?.connection?.flushAll()
    }

    @Test
    fun `결제 요청 멱등 객체를 저장한다`() {
        // given
        val payment = Payment("uuid", 1L, 10000, 1L)

        // when then
        paymentRedisRepository.save(payment)
    }

    @Test
    fun `저장된 결제 요청 멱등 객체를 반환한다`() {
        // given
        val payment = Payment("uuid", 1L, 10000, 1L)

        paymentRedisRepository.save(payment)

        // when
        val foundPayment = paymentRedisRepository.findByUuid(payment.uuid)

        // then
        assertThat(foundPayment)
            .extracting("uuid", "memberId", "paymentAmount", "storeId", "isSuccess")
            .contains(payment.uuid, payment.memberId, payment.paymentAmount, payment.storeId, payment.isSuccess)
    }

}