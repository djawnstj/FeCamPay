package com.djawnstj.fecampay.store.repository

import com.djawnstj.fecampay.store.entity.Store
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StoreRepository : JpaRepository<Store, Long> {
}