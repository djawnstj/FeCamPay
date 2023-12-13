package com.djawnstj.fecampay.account.repository

import com.djawnstj.fecampay.account.entity.Account
import com.linecorp.kotlinjdsl.support.spring.data.jpa.repository.KotlinJdslJpqlExecutor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<Account, Long>, KotlinJdslJpqlExecutor {
}