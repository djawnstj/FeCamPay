package com.djawnstj.fecampay.account.entity

import com.djawnstj.fecampay.common.entity.BaseEntity
import com.djawnstj.fecampay.member.entity.Member
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne

@Entity
class Account(
    var amount: Int,
    @OneToOne
    @JoinColumn(name = "member_id")
    val member: Member
) : BaseEntity() {
}