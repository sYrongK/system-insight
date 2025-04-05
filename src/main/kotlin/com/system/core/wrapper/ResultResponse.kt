package com.system.core.wrapper

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpStatus

@JsonInclude(JsonInclude.Include.NON_NULL)
class ResultResponse<T>(
    var status: Int = HttpStatus.OK.value(),
    var code: Int = 0,
    var message: String? = "success",
    override var result: T? = null
) : ResuntInterface<T> {
    constructor(status: HttpStatus) : this(status = status.value())

    constructor(code: Int, message: String) : this(status = HttpStatus.OK.value(), code = code, message = message)

    constructor(result: T) : this(status = HttpStatus.OK.value(), message = "success", result = result)
}