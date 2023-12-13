package com.djawnstj.fecampay.store.repository

import com.djawnstj.fecampay.store.entity.StorePaymentRequest
import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StorePaymentRequestRepository : JpaRepository<StorePaymentRequest, Long>, KotlinJdslJpqlExecutor {
}