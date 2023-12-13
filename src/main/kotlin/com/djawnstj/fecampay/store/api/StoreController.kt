package com.djawnstj.fecampay.store.api

import com.djawnstj.fecampay.store.dto.StorePaymentRequestDto
import com.djawnstj.fecampay.store.service.StoreService
import com.interrupt.server.common.api.BaseResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class StoreController(
    private val storeService: StoreService
) {

    @GetMapping("/api/v1/stores/{id}/payments")
    fun getStorePaymentRequest(@PathVariable("id") id: Long) : BaseResponse<StorePaymentRequestDto> =
        BaseResponse(data = storeService.getPaymentRequestInfo(id))

}