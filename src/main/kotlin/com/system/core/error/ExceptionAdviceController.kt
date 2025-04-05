package com.system.core.error

import com.system.core.error.exception.RankingException
import com.system.core.wrapper.ResultResponse
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionAdviceController {

    private val log = KotlinLogging.logger {}

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RankingException::class)
    fun handleException(e: RankingException): ResultResponse<*> {
        val resultResponse = ResultResponse<Any>(e.httpStatus)
        resultResponse.code = e.code
        resultResponse.message = e.message
        e.printStackTrace()
        return resultResponse
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResultResponse<*> {
        val resultResponse = ResultResponse<Any>(HttpStatus.INTERNAL_SERVER_ERROR)
        resultResponse.message = e.message
        e.stackTrace.forEach { stackTraceElement ->
            log.warn(stackTraceElement.toString())
        }
        e.printStackTrace()
        return resultResponse
    }
}