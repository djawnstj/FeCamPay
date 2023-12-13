package com.djawnstj.fecampay.pay.repository

import com.djawnstj.fecampay.pay.dto.Payment
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.time.Duration

@Repository
class PaymentRedisRepository(
    private val paymentRedisTemplate: RedisTemplate<String, Payment>
) {

    companion object {
        private const val KEY_PREFIX = "PAYMENT"
        private val PAYMENT_DATA_TTL = Duration.ofMinutes(30)
    }

    fun save(payment: Payment): Payment {
        paymentRedisTemplate.opsForValue().set(generateKey(payment.uuid), payment, PAYMENT_DATA_TTL)

        return payment
    }

    fun findByUuid(uuid: String): Payment? = paymentRedisTemplate.opsForValue().get(generateKey(uuid))

    private fun generateKey(uuid: String): String = "$KEY_PREFIX:$uuid"
}