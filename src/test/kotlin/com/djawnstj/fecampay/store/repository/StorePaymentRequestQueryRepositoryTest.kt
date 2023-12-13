package com.djawnstj.fecampay.store.repository

import com.djawnstj.fecampay.IntegrationTestSupport
import com.djawnstj.fecampay.store.entity.Store
import com.djawnstj.fecampay.store.entity.StorePaymentRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class StorePaymentRequestQueryRepositoryTest: IntegrationTestSupport() {

    @Autowired
    private lateinit var storePaymentRequestQueryRepository: StorePaymentRequestQueryRepository

    @Autowired
    private lateinit var storeRepository: StoreRepository
    @Autowired
    private lateinit var storePaymentRequestRepository: StorePaymentRequestRepository

    @Test
    fun `가맹점 ID 로 결제 요청을 조회한다`() {
        // given
        val store = storeRepository.save(Store("가맹점1"))
        val storePaymentRequest = StorePaymentRequest(10000, store)
        storePaymentRequestRepository.save(storePaymentRequest)

        // when
        val foundStorePaymentRequest = storePaymentRequestQueryRepository.findByStoreId(store.id)

        // then
        assertThat(foundStorePaymentRequest).isNotNull
        assertThat(foundStorePaymentRequest!!.paymentAmount).isEqualTo(storePaymentRequest.paymentAmount)
        assertThat(foundStorePaymentRequest.store.id).isEqualTo(store.id)
    }

}