package com.djawnstj.fecampay.member.entity

import com.djawnstj.fecampay.common.entity.BaseEntity
import jakarta.persistence.Entity

@Entity
class Member(
    val loginId: String,
) : BaseEntity() {
}