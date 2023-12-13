package com.djawnstj.fecampay.store.repository

import com.djawnstj.fecampay.store.entity.Store
import com.djawnstj.fecampay.store.entity.StorePaymentRequest
import org.springframework.stereotype.Repository

@Repository
class StorePaymentRequestQueryRepository(
    private val storePaymentRequestRepository: StorePaymentRequestRepository
) {

    fun findByStoreId(storeId: Long): StorePaymentRequest? = storePaymentRequestRepository.findAll {
        select(
            entity(StorePaymentRequest::class)
        ).from(
            entity(StorePaymentRequest::class),
            fetchJoin(Store::class).on(path(Store::id).equal(path(StorePaymentRequest::store)(Store::id)))
        ).where(
            path(StorePaymentRequest::deletedAt).isNull()
                .and(
                    path(Store::deletedAt).isNull()
                )
        )
    }.firstOrNull()

}