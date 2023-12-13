package com.djawnstj.fecampay.account.repository

import com.djawnstj.fecampay.IntegrationTestSupport
import com.djawnstj.fecampay.account.entity.Account
import com.djawnstj.fecampay.member.entity.Member
import com.djawnstj.fecampay.member.repository.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class AccountQueryRepositoryTest: IntegrationTestSupport() {

    @Autowired
    private lateinit var accountQueryRepository: AccountQueryRepository

    @Autowired
    private lateinit var memberRepository: MemberRepository
    @Autowired
    private lateinit var accountRepository: AccountRepository

    @Test
    fun `회원 ID 로 페캠페이 계좌를 조회한다`() {
        // given
        val member = memberRepository.save(Member("loginId"))

        val account = accountRepository.save(Account(0, member))

        em.flush()
        em.clear()

        // when
        val foundAccount = accountQueryRepository.findByMemberId(member.id)

        // then
        assertThat(foundAccount).isNotNull
        assertThat(foundAccount!!.amount).isEqualTo(account.amount)
        assertThat(foundAccount.member.id).isEqualTo(member.id)
    }

}