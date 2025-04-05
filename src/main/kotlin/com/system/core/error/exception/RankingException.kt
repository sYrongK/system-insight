package com.system.core.error.exception

import com.system.core.error.ErrorCode
import org.springframework.http.HttpStatus

class RankingException : RuntimeException {

    var httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR
    var code: Int = 0
    var errorData: Any? = null

    constructor(message: String) : super(message) {
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value()
    }

    constructor(httpStatus: HttpStatus, code: Int, message: String) : super(message) {
        this.httpStatus = httpStatus
        this.code = code
    }

    constructor(httpStatus: HttpStatus, errorCode: ErrorCode, message: String) : super(message) {
        this.httpStatus = httpStatus
        this.code = errorCode.code
    }

    constructor(httpStatus: HttpStatus, errorCode: ErrorCode, message: String, errorData: Any?) : super(message) {
        this.httpStatus = httpStatus
        this.code = errorCode.code
        this.errorData = errorData
    }
}