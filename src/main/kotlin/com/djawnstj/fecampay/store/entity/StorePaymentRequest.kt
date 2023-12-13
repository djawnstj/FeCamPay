package com.djawnstj.fecampay.store.entity

import com.djawnstj.fecampay.common.entity.BaseEntity
import jakarta.persistence.*

@Entity
class StorePaymentRequest(
    val paymentAmount: Int,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    val store: Store
) : BaseEntity() {
}