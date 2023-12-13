package com.djawnstj.fecampay.account.service

import com.djawnstj.fecampay.account.entity.Account
import com.djawnstj.fecampay.account.repository.AccountQueryRepository
import com.djawnstj.fecampay.account.repository.AccountRepository
import com.djawnstj.fecampay.common.exception.ErrorCode
import com.djawnstj.fecampay.common.exception.FeCamPayException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AccountService(
    private val accountRepository: AccountRepository,
    private val accountQueryRepository: AccountQueryRepository
) {

    @Transactional
    fun deductAmount(paymentAmount: Int, memberId: Long) {
        val foundAccount = accountQueryRepository.findByMemberId(memberId).isValid()

        val result = foundAccount.amount - paymentAmount
        validateAmount(result)

        foundAccount.amount = result

        accountRepository.save(foundAccount)
    }

    private fun Account?.isValid(): Account {
        if (this == null) throw FeCamPayException(ErrorCode.ACCOUNT_NOT_FOUND)
        return this
    }

    private fun validateAmount(amount: Int) {
        if (amount < 0) throw FeCamPayException(ErrorCode.INVALID_AMOUNT)
    }

}