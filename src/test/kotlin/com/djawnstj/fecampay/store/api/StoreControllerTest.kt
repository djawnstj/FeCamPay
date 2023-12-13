package com.djawnstj.fecampay.store.api

import com.djawnstj.fecampay.ControllerTestSupport
import com.djawnstj.fecampay.store.dto.StorePaymentRequestDto
import com.djawnstj.fecampay.store.service.StoreService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

class StoreControllerTest: ControllerTestSupport() {

    @Test
    fun `가맹점의 결제 요청을 조회한다`() {
        // given
        every { storeService.getPaymentRequestInfo(1L) } returns StorePaymentRequestDto(10000, "가맹점1")

        // when then
        mockMvc.perform(
            get("/api/v1/stores/{id}/payments", 1L))
            .andDo(print())
            .isOkBaseResponse()
            .andExpect((MockMvcResultMatchers.jsonPath("$.data").isNotEmpty))
            .andExpect((MockMvcResultMatchers.jsonPath("$.data.paymentAmount").value(10000)))
            .andExpect((MockMvcResultMatchers.jsonPath("$.data.storeName").value("가맹점1")))
    }

}