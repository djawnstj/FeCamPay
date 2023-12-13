package com.djawnstj.fecampay

import com.djawnstj.fecampay.common.exception.ErrorCode
import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(controllers = [
])
@MockkBean(JpaMetamodelMappingContext::class)
abstract class ControllerTestSupport {

    @Autowired
    protected lateinit var mockMvc: MockMvc

    @Autowired
    protected lateinit var objectMapper: ObjectMapper

    protected fun ResultActions.baseResponse(status: HttpStatus): ResultActions =
        this.andExpect(jsonPath("$.statusCode").value(status.value()))
            .andExpect(jsonPath("$.message").value("success"))


    protected fun ResultActions.isOkBaseResponse(): ResultActions =
        this.andExpect(status().isOk)
            .baseResponse(HttpStatus.OK)


    protected fun ResultActions.isCreatedBaseResponse(): ResultActions =
        this.andExpect(status().isCreated)
            .baseResponse(HttpStatus.CREATED)

    protected fun ResultActions.isInvalidInputValueResponse(invalidParam: String, message: String): ResultActions =
        this.andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.statusCode").value(ErrorCode.INVALID_INPUT_VALUE.status.value()))
            .andExpect(jsonPath("$.errorCode").value(ErrorCode.INVALID_INPUT_VALUE.name))
            .andExpect(jsonPath("$.message").value(ErrorCode.INVALID_INPUT_VALUE.message))
            .andExpect(jsonPath("$.data").isNotEmpty)
            .andExpect(jsonPath("$.data.$invalidParam").value(message))

}