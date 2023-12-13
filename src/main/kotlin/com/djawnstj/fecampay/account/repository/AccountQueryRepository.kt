package com.djawnstj.fecampay.account.repository

import com.djawnstj.fecampay.account.entity.Account
import com.djawnstj.fecampay.member.entity.Member
import org.springframework.stereotype.Repository

@Repository
class AccountQueryRepository(
    private val accountRepository: AccountRepository
) {
    
    fun findByMemberId(memberId: Long): Account? = accountRepository.findAll {
        select(
            entity(Account::class)
        ).from(
            entity(Account::class),
            join(Member::class).on(path(Member::id).equal(path(Account::member)(Member::id)))
        ).where(
            path(Account::deletedAt).isNull()
                .and(
                    path(Account::member)(Member::id).equal(memberId)
                )
        )
    }.firstOrNull()
    
}