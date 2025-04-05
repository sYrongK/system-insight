package com.system.core.error

enum class ErrorCode(val code: Int, val message: String) {
    INTERNAL_SERVER_ERROR(90500, "Internal Server Error"),
    UNAUTHORIZED(90401, "인증되지 않은 사용자입니다."),
    FORBIDDEN(90403, "허가되지 않은 접근입니다.")
}