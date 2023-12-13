package com.djawnstj.fecampay.pay.api

import com.djawnstj.fecampay.ControllerTestSupport
import com.djawnstj.fecampay.pay.dto.request.PayRequest
import com.djawnstj.fecampay.pay.dto.response.PayResponse
import com.djawnstj.fecampay.pay.repository.PaymentRedisRepository
import com.djawnstj.fecampay.pay.service.PayService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class PayControllerTest: ControllerTestSupport() {

    @Test
    fun `요청된 결제를 수행한다`() {
        // given
        val request = PayRequest("uuid", 1L, 10000, 1L)
        val response = PayResponse("결제에 성공했습니다.")

        every { payService.pay(any<PayRequest>()) } returns response

        // when then
        mockMvc.perform(
            post("/api/v1/pay")
                .content(objectMapper.writeValueAsBytes(request))
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .isOkBaseResponse()
            .andExpect((MockMvcResultMatchers.jsonPath("$.data").isNotEmpty))
            .andExpect((MockMvcResultMatchers.jsonPath("$.data.message").value(response.message)))
    }

}