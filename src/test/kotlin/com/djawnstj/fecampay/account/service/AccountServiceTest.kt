package com.djawnstj.fecampay.account.service

import com.djawnstj.fecampay.account.entity.Account
import com.djawnstj.fecampay.account.repository.AccountQueryRepository
import com.djawnstj.fecampay.account.repository.AccountRepository
import com.djawnstj.fecampay.common.exception.ErrorCode
import com.djawnstj.fecampay.common.exception.FeCamPayException
import com.djawnstj.fecampay.member.entity.Member
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any

class AccountServiceTest {

    private val accountRepository: AccountRepository = mockk()
    private val accountQueryRepository: AccountQueryRepository = mockk()

    private val accountService = AccountService(accountRepository, accountQueryRepository)

    @Test
    fun `회원 ID 와 차감할 금액을 전달받아 회원 계좌의 금액을 차감시킨다`() {
        // given
        val member = Member("loginId")
        val account = Account(20000, member)

        every { accountQueryRepository.findByMemberId(any<Long>()) } returns account
        every { accountRepository.save(any<Account>()) } answers { it.invocation.args[0] as Account }

        // when then
        accountService.deductAmount(10000, 1L)
    }

    @Test
    fun `회원 ID 와 차감할 금액을 전달받아 회원 계좌의 금액을 차감시킬 때 해당 회원의 페이 계좌를 찾지 못하면 예외를 반환한다`() {
        // given
        val member = Member("loginId")

        every { accountQueryRepository.findByMemberId(any<Long>()) } returns null

        // when then
        assertThatThrownBy { accountService.deductAmount(10000, 1L) }
            .isInstanceOf(FeCamPayException::class.java)
            .hasMessage(ErrorCode.ACCOUNT_NOT_FOUND.message)
    }

    @Test
    fun `회원 ID 와 차감할 금액을 전달받아 회원 계좌의 금액을 차감시킬 때 잔액보다 차감할 금액이 크면 예외를 반환한다`() {
        // given
        val member = Member("loginId")
        val account = Account(9000, member)

        every { accountQueryRepository.findByMemberId(any<Long>()) } returns account

        // when then
        assertThatThrownBy { accountService.deductAmount(10000, 1L) }
            .isInstanceOf(FeCamPayException::class.java)
            .hasMessage(ErrorCode.INVALID_AMOUNT.message)
    }

}