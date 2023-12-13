package com.djawnstj.fecampay.store.entity

import com.djawnstj.fecampay.common.entity.BaseEntity
import jakarta.persistence.Entity

@Entity
class Store(
    val storeName: String
) : BaseEntity() {
}