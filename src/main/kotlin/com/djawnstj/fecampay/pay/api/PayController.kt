package com.djawnstj.fecampay.pay.api

import com.djawnstj.fecampay.pay.dto.request.PayRequest
import com.djawnstj.fecampay.pay.dto.response.PayResponse
import com.djawnstj.fecampay.pay.service.PayService
import com.interrupt.server.common.api.BaseResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PayController(
    private val payService: PayService
) {

    @PostMapping("/api/v1/pay")
    fun pay(request: PayRequest): BaseResponse<PayResponse> =
        BaseResponse(data = payService.pay(request))

}